package me.a8kj.configuration.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import lombok.NonNull;
import lombok.var;
import me.a8kj.configuration.impl.annotations.AnnotationReader;
import me.a8kj.configuration.impl.annotations.containers.SettingsContainer;
import me.a8kj.configuration.impl.operations.LoadOperation;
import me.a8kj.configuration.impl.operations.SaveOperation;
import me.a8kj.configuration.parent.entity.Configurable;
import me.a8kj.configuration.parent.entity.ConfigurationOperations;
import me.a8kj.configuration.parent.entity.ManagerAccessor;

@Getter
public abstract class A8kjCfgImpl implements Configurable, ConfigurationOperations {

    private File file;
    private FileConfiguration fileConfiguration;

    private final String fileName;
    private final JavaPlugin plugin;

    private SettingsContainer settingsContainer;
    private ManagerAccessor managerAccessor;

    public A8kjCfgImpl(@NonNull String fileName, JavaPlugin plugin) {
        this.fileName = fileName.endsWith(".yaml") || fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        this.plugin = plugin;

        AnnotationReader annotationReader = new AnnotationReader();
        annotationReader.read(this.getClass());
        settingsContainer = annotationReader.getSettingsContainer();
        managerAccessor = settingsContainer;

        if (settingsContainer == null) {
            throw new IllegalStateException(
                    "Failed to initialize settings container. Ensure the class is properly annotated.");
        }

    }

    @Override
    public void create() {
        var settingsManager = this.getManagerAccessor().getSettingsManager();

        boolean backupOnSave = (boolean) settingsManager.getValue("backupOnSave");
        boolean defaultSave = (boolean) settingsManager.getValue("backupOnSave");

        File dataFolder = this.getPlugin().getDataFolder();

        if (backupOnSave) {
            var backupManager = this.getManagerAccessor().getBackupManager();

            String path = (String) backupManager.getValue("path");
            boolean custom = (boolean) backupManager.getValue("custom");

            if ((path.equalsIgnoreCase("null") && !custom) ||
                    path.equalsIgnoreCase("null") && custom) {
                createBackupDirectories(dataFolder.toString(), "backup", true);
            } else if (!path.equalsIgnoreCase("null") && custom) {
                createBackupDirectories(path, "backup", true);
            }
        }

        var whereManager = this.getManagerAccessor().getWhereManager();

        String path = (String) whereManager.getValue("path");
        boolean custom = (boolean) whereManager.getValue("custom");

        Path configFilePath = null;

        try {
            if ((path.equalsIgnoreCase("null") && !custom) ||
                    path.equalsIgnoreCase("null") && custom) {
                configFilePath = Paths.get(dataFolder.toString(), fileName);

                if (Files.notExists(configFilePath)) {
                    Files.createDirectories(configFilePath.getParent());
                    if (defaultSave) {
                        if (getPlugin().getResource(fileName) == null) {
                            // should i added message here to notify user
                            Files.createFile(configFilePath);
                        } else {
                            this.getPlugin().saveResource(fileName, true);
                        }
                    } else {
                        Files.createFile(configFilePath);
                    }
                }

            } else if (!path.equalsIgnoreCase("null") && custom) {
                configFilePath = Paths.get(path, fileName);
                if (Files.notExists(configFilePath)) {
                    Files.createDirectories(configFilePath.getParent());
                    if (defaultSave) {
                        if (getPlugin().getResource(fileName) == null) {
                            Files.createFile(configFilePath);
                        } else {
                            this.getPlugin().saveResource(fileName, true);
                        }
                    } else {
                        Files.createFile(configFilePath);
                    }
                }

            }

            this.file = configFilePath.toFile();

            if (this.file.exists()) {
                this.fileConfiguration = (FileConfiguration) YamlConfiguration.loadConfiguration(this.file);
                System.err.println("sex with hores");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createBackupDirectories(@NonNull String parentDir, String childDir, boolean hasChild) {
        try {
            Path dirPath;
            if (hasChild)
                dirPath = Paths.get(parentDir, childDir);
            else
                dirPath = Paths.get(parentDir);

            if (Files.notExists(dirPath)) {
                Files.createDirectories(dirPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        new SaveOperation(file, fileConfiguration, settingsContainer, plugin, fileName).execute();
    }

    @Override
    public void load() {
        new LoadOperation(file, fileConfiguration, settingsContainer, plugin, fileName).execute();
    }

}
