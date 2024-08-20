package me.a8kj.configuration.impl.annotations.containers;

import lombok.Getter;
import me.a8kj.configuration.parent.entity.Container;

public class SettingsContainer extends Container {

    @Getter
    protected final WhereContainer whereContainer;

    public SettingsContainer() {
        super("Settings Annotation container");
        this.whereContainer = new WhereContainer();
    }

}
