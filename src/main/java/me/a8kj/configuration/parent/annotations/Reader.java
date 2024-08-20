package me.a8kj.configuration.parent.annotations;

import me.a8kj.configuration.parent.entity.Configurable;

public interface Reader {

    public void read(Class<? extends Configurable> configClass);
}
