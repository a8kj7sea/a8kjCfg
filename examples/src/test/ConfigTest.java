package me.a8kj.test;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import me.a8kj.configuration.impl.A8kjCfgImpl;
import me.a8kj.configuration.parent.annotations.base.Settings;

@Settings(backupOnSave = true)
public class ConfigTest extends A8kjCfgImpl {

    public ConfigTest(@NonNull String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        this.create();
    }


    public void setString(String text) {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguation null !");
        }

        this.getFileConfiguration().set("Test", text);
        this.save();
    }
    

}
