package me.a8kj.configuration.parent.entity;

import lombok.NonNull;

public interface Processor {

    public void process(@NonNull ManagerAccessor managerAccessor);
}
