package me.a8kj.configuration.impl.annotations;

import lombok.Getter;
import me.a8kj.configuration.impl.annotations.containers.BackupContainer;
import me.a8kj.configuration.impl.annotations.containers.SettingsContainer;
import me.a8kj.configuration.impl.annotations.containers.WhereContainer;
import me.a8kj.configuration.parent.annotations.Reader;
import me.a8kj.configuration.parent.annotations.base.Settings;
import me.a8kj.configuration.parent.annotations.base.Where;
import me.a8kj.configuration.parent.entity.Configurable;
import me.a8kj.configuration.parent.exceptions.MissingAnnotationException;
import me.a8kj.configuration.parent.utils.java.SimpleMapManager;

@Getter
public class AnnotationReader implements Reader {

    private final SettingsContainer settingsContainer = new SettingsContainer();

    @Override
    public void read(Class<? extends Configurable> configClass) {
        if (!configClass.isAnnotationPresent(Settings.class))
            throw new MissingAnnotationException();

        Settings settings = configClass.getDeclaredAnnotation(Settings.class);
        SimpleMapManager<String, Object> settingsManager = this.settingsContainer.getManager();

        settingsManager.add("backupOnSave", settings.backupOnSave());
        settingsManager.add("defaultSave", settings.defaultSave());

        Where where = settings.where();

        WhereContainer whereContainer = this.settingsContainer.getWhereContainer();
        SimpleMapManager<String, Object> whereManager = whereContainer.getManager();

        BackupContainer backupContainer = whereContainer.getBackupContainer();
        SimpleMapManager<String, Object> backupmManager = backupContainer.getManager();

        if (where == null) {
            whereManager.add("path", (String) "null"); // default folder (DataFolder)
            whereManager.add("custom", false);

            if (settings.backupOnSave()) {
                backupmManager.add("path", (String) "null"); // "datafolder/backups"
                backupmManager.add("custom", false);
                backupmManager.add("extension", ".backup");
            }

        } else {
            whereManager.add("path", where.path());
            whereManager.add("custom", where.custom());

            Where.Backup backup = where.backup();

            if (backup == null) {
                if (settings.backupOnSave()) {
                    backupmManager.add("path", (String) "null"); // "datafolder/backups"
                    backupmManager.add("custom", false);
                    backupmManager.add("extension", ".backup");
                }
            } else {
                if (!settings.backupOnSave())
                    return;

                backupmManager.add("path", (String) backup.path());
                backupmManager.add("custom", backup.custom());
                backupmManager.add("extension", backup.extension());

            }

        }

    }

}
