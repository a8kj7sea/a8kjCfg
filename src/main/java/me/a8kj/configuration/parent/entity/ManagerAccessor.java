package me.a8kj.configuration.parent.entity;

import me.a8kj.configuration.parent.utils.java.SimpleMapManager;

public interface ManagerAccessor {

    SimpleMapManager<String, Object> getSettingsManager();

    SimpleMapManager<String, Object> getWhereManager();

    SimpleMapManager<String, Object> getBackupManager();
}
