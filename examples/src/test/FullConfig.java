package me.a8kj.test;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import me.a8kj.configuration.impl.A8kjCfgImpl;
import me.a8kj.configuration.parent.annotations.base.Settings;
import me.a8kj.configuration.parent.annotations.base.Where;

@Settings(
    backupOnSave = true, 
    where = @Where(
        path = "configs/test",
        custom = true,
        backup = @Where.Backup(
            path = "backups",
            custom = true,
            extension = ".bak"
        )
    )
)
public class FullConfig extends A8kjCfgImpl {

    public ConfigTest(@NonNull String fileName, JavaPlugin plugin) {
        super(fileName, plugin);
        this.create();
    }

    public void setString(String text) {
        if (getFileConfiguration() == null) {
            throw new IllegalStateException("FileConfiguration is null!");
        }

        this.getFileConfiguration().set("Test", text);
        this.save();
    }
}
