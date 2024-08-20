package me.a8kj.configuration.impl.operations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import lombok.var;
import me.a8kj.configuration.parent.entity.ManagerAccessor;
import me.a8kj.configuration.parent.entity.ConfigurationOperation;

public class CreateOperation extends ConfigurationOperation {

    

   

    public CreateOperation(File file, FileConfiguration fileConfiguration, ManagerAccessor managerAccessor,
            JavaPlugin plugin, String fileName) {
        super(file, fileConfiguration, managerAccessor, plugin, fileName);
    }

    @Override
    public void execute() {
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
                    if (defaultSave)
                        this.getPlugin().saveResource(fileName, true);
                    else
                        Files.createFile(configFilePath);
                }

            } else if (!path.equalsIgnoreCase("null") && custom) {
                configFilePath = Paths.get(path, fileName);
                if (Files.notExists(configFilePath)) {
                    Files.createDirectories(configFilePath.getParent());
                    if (defaultSave)
                        this.getPlugin().saveResource(fileName, true);
                    else
                        Files.createFile(configFilePath);
                }
            }

            if (configFilePath == null) {
                throw new IllegalStateException("config file path cannot be null");
            }

            this.file = configFilePath.toFile();
            this.fileConfiguration = YamlConfiguration.loadConfiguration(configFilePath.toFile());

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

}
