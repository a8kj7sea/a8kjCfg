package me.a8kj.configuration.impl.operations;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.configuration.parent.entity.ManagerAccessor;
import me.a8kj.configuration.parent.entity.ConfigurationOperation;

public class LoadOperation extends ConfigurationOperation {

    public LoadOperation(File file, FileConfiguration fileConfiguration, ManagerAccessor managerAccessor,
            JavaPlugin plugin, String fileName) {
        super(file, fileConfiguration, managerAccessor, plugin, fileName);
    }

    @Override
    public void execute() {
        if (this.file == null) {
            throw new IllegalStateException("config file cannot be null please restart server !");
        }

        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.getFile());
    }

}
