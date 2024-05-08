package me.a8kj.configuration.entity;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.NonNull;
import me.a8kj.configuration.annotations.ConfigurationInfo;

/**
 * An abstract class representing a configuration file for a Bukkit plugin.
 * Provides common methods for configuration file management.
 */
public abstract class AbstractConfiguration implements Configurable {

  private @NonNull final String name; // Name of the configuration file
  private @NonNull final JavaPlugin javaPlugin; // Reference to the JavaPlugin instance

  private @NonNull final ConfigurationInfo configurationInfo; // ConfigurationInfo annotation for the subclass

  private @NonNull File file; // File object representing the configuration file
  private @NonNull FileConfiguration fileConfiguration; // FileConfiguration object for the configuration file

  private File path; // File object representing the path to the configuration file

  /**
   * Constructor for AbstractConfiguration class.
   * Initializes the configuration file name, JavaPlugin instance, and
   * ConfigurationInfo annotation.
   * 
   * @param name       Name of the configuration file
   * @param javaPlugin Reference to the JavaPlugin instance
   */
  public AbstractConfiguration(@NonNull String name, @NonNull JavaPlugin javaPlugin) {
    // Ensure the configuration file name ends with ".yml"
    this.name = name.endsWith(".yml") ? name : name + ".yml";
    this.javaPlugin = javaPlugin;
    // Get the ConfigurationInfo annotation declared in the subclass
    this.configurationInfo = this.getClass().getDeclaredAnnotation(ConfigurationInfo.class);
  }

  /**
   * Initializes the configuration file.
   * Creates the file if it doesn't exist and loads its contents.
   */
  @Override
  public void init() {
    // Set the default path to the data folder of the JavaPlugin
    this.path = this.getJavaPlugin().getDataFolder();
    // Check if ConfigurationInfo annotation is present
    if (configurationInfo == null) {
      create(path, true); // Create the configuration file with default settings
    } else {
      String ourPath = configurationInfo.path(); // Get the path specified in the annotation
      // Check if the path is specified and not empty
      if (ourPath != "" && !ourPath.isEmpty()) {
        // Set the path to the specified location
        this.path = new File(this.getJavaPlugin().getDataFolder(), ourPath);
        create(path, configurationInfo.defaultSave()); // Create the configuration file
      } else {
        create(path, configurationInfo.defaultSave()); // Create the configuration file with default settings
      }
    }
    load(); // Load the configuration file
  }

  /**
   * Creates the configuration file.
   * If the file doesn't exist, creates it and saves the default configuration.
   * 
   * @param path        File path to save the configuration file
   * @param defaultSave Flag indicating whether to save the default configuration
   */
   private void create(File path, boolean defaultSave) {
    // Create the file object
    this.file = new File(path, name);
    // Create directories for the file path if they don't exist
    if (!this.file.getParentFile().exists()) {
      this.file.getParentFile().mkdirs();
    }
    // Check if the file exists
    if (!this.file.exists()) {
      // Check if defaultSave flag is false
      if (!defaultSave) {
        this.javaPlugin.saveResource(name, defaultSave);
      } else {
        try {
          file.createNewFile();
        } catch (IOException ioexception) {
          ioexception.printStackTrace();
        }
      }
    } else {
      // Save the default configuration provided by the plugin
      this.javaPlugin.saveResource(name, true);
    }

  }

  @Override
  public void save() {
    try {
      this.fileConfiguration.save(this.file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void load() {
    this.fileConfiguration = YamlConfiguration.loadConfiguration(this.getFile());
  }

  @Override
  public boolean delete() {
    return this.file.delete();
  }

  @Override
  public JavaPlugin getJavaPlugin() {
    return javaPlugin;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public FileConfiguration getFileConfiguration() {
    return fileConfiguration;
  }

}
