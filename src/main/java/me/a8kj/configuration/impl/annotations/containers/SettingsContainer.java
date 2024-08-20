package me.a8kj.configuration.impl.annotations.containers;

import lombok.Getter;
import me.a8kj.configuration.parent.entity.Container;
import me.a8kj.configuration.parent.entity.ManagerAccessor;
import me.a8kj.configuration.parent.utils.java.SimpleMapManager;

public class SettingsContainer extends Container implements ManagerAccessor {

    @Getter
    protected final WhereContainer whereContainer;

    public SettingsContainer() {
        super("Settings Annotation container");
        this.whereContainer = new WhereContainer();
    }

    @Override
    public SimpleMapManager<String, Object> getSettingsManager() {
        return this.getSettingsManager();
    }

    @Override
    public SimpleMapManager<String, Object> getWhereManager() {
        return this.getWhereManager();
    }

    @Override
    public SimpleMapManager<String, Object> getBackupManager() {
        return this.getBackupManager();
    }

}
