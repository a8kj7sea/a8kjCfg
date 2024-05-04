package me.a8kj.configuration.entity;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.NonNull;

/**
 * Interface representing a configurable entity with methods for managing
 * configuration files.
 */
public interface Configurable {

    /**
     * Gets the JavaPlugin instance associated with this configurable entity.
     * 
     * @return The JavaPlugin instance
     */
    @NonNull
    JavaPlugin getJavaPlugin();

    /**
     * Gets the name of the configuration file associated with this configurable
     * entity.
     * 
     * @return The name of the configuration file
     */
    @NonNull
    String getName();

    /**
     * Gets the file object representing the configuration file associated with this
     * configurable entity.
     * 
     * @return The file object representing the configuration file
     */
    @NonNull
    File getFile();

    /**
     * Gets the FileConfiguration object representing the configuration file
     * associated with this configurable entity.
     * 
     * @return The FileConfiguration object representing the configuration file
     */
    @NonNull
    FileConfiguration getFileConfiguration();

    /**
     * Initializes the configurable entity.
     * This method should be called to initialize and load the configuration file.
     */
    void init();

    /**
     * Saves the configuration file associated with this configurable entity.
     * This method should be called to save any changes made to the configuration.
     */
    void save();

    /**
     * Loads the configuration file associated with this configurable entity.
     * This method should be called to load the configuration file into memory.
     */
    void load();

    /**
     * Deletes the configuration file associated with this configurable entity.
     * 
     * @return True if the file was successfully deleted, false otherwise
     */
    boolean delete();
}
