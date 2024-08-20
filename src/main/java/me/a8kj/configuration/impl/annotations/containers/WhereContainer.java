package me.a8kj.configuration.impl.annotations.containers;

import lombok.Getter;
import me.a8kj.configuration.parent.entity.Container;

public class WhereContainer extends Container {

    @Getter
    protected final BackupContainer backupContainer;

    public WhereContainer() {
        super("Backup Annotation container");
        this.backupContainer = new BackupContainer();
    }

}
