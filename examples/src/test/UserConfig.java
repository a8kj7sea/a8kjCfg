package me.a8kj.test;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import me.a8kj.configuration.impl.A8kjCfgImpl;
import me.a8kj.configuration.parent.annotations.base.Settings;

@Settings(backupOnSave = false, defaultSave = true)
public class UserConfig extends A8kjCfgImpl {

    public UserConfig(@NonNull String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        this.create();
    }

    public void setUserRole(String role) {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguration is null!");
        }

        this.getFileConfiguration().set("User.Role", role);
        this.save();
    }

    public String getUserRole() {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguration is null!");
        }

        return this.getFileConfiguration().getString("User.Role", "DefaultRole");
    }

    public void removeUserRole() {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguration is null!");
        }

        this.getFileConfiguration().set("User.Role", null);
        this.save();
    }
}
