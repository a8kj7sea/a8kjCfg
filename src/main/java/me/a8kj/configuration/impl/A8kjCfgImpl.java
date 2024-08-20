package me.a8kj.configuration.impl;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import lombok.NonNull;
import me.a8kj.configuration.impl.annotations.AnnotationReader;
import me.a8kj.configuration.impl.annotations.containers.SettingsContainer;
import me.a8kj.configuration.impl.operations.CreateOperation;
import me.a8kj.configuration.impl.operations.LoadOperation;
import me.a8kj.configuration.impl.operations.SaveOperation;
import me.a8kj.configuration.parent.entity.Configurable;
import me.a8kj.configuration.parent.entity.ConfigurationOperations;

@Getter
public abstract class A8kjCfgImpl implements Configurable, ConfigurationOperations {

    private File file;
    private FileConfiguration fileConfiguration;

    private final String fileName;
    private final JavaPlugin plugin;

    private SettingsContainer settingsContainer;

    public A8kjCfgImpl(@NonNull String fileName, JavaPlugin plugin) {
        this.fileName = fileName.endsWith(".yaml") || fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        this.plugin = plugin;

        AnnotationReader annotationReader = new AnnotationReader();
        annotationReader.read(this.getClass());
        settingsContainer = annotationReader.getSettingsContainer();

        if (settingsContainer == null) {
            throw new IllegalStateException(
                    "Failed to initialize settings container. Ensure the class is properly annotated.");
        }

    }

    @Override
    public void create() {
        new CreateOperation(file, fileConfiguration, settingsContainer, plugin, fileName).execute();
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
