package me.a8kj.configuration.parent.entity;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class ConfigurationOperation {

    protected File file;
    protected FileConfiguration fileConfiguration;
    protected ManagerAccessor managerAccessor;

    protected final JavaPlugin plugin;
    protected final String fileName;

    public abstract void execute();
}
