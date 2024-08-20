package me.a8kj.configuration.impl.operations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.var;
import me.a8kj.configuration.parent.entity.ManagerAccessor;
import me.a8kj.configuration.parent.utils.java.resovler.FilesUtils;
import me.a8kj.configuration.parent.entity.ConfigurationOperation;

public class SaveOperation extends ConfigurationOperation {

    public SaveOperation(File file, FileConfiguration fileConfiguration, ManagerAccessor managerAccessor,
            JavaPlugin plugin, String fileName) {
        super(file, fileConfiguration, managerAccessor, plugin, fileName);
    }

    @Override
    public void execute() {
        if (getFileConfiguration() == null || getFile() == null) {
            throw new IllegalStateException("config file cannot be null please restart server !");
        }

        try {

            var settingsManager = this.getManagerAccessor().getSettingsManager();

            boolean backupOnSave = (boolean) settingsManager.getValue("backupOnSave");

            if (!backupOnSave)
                return;
            getFileConfiguration().save(getFile());

            File dataFolder = this.getPlugin().getDataFolder();

            var backupManager = this.getManagerAccessor().getBackupManager();

            String path = (String) backupManager.getValue("path");
            boolean custom = (boolean) backupManager.getValue("custom");

            String folderOfCopy = null;

            if ((path.equalsIgnoreCase("null") && !custom) ||
                    path.equalsIgnoreCase("null") && custom) {
                folderOfCopy = new File(dataFolder, "backup").toString();
            } else if (!path.equalsIgnoreCase("null") && custom) {
                folderOfCopy = new File(path, "backup").toString();
            }

            FilesUtils.copyFileWithNewExtension(this.getFile().toPath(), Paths.get(folderOfCopy), ".backup");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
