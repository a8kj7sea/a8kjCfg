package me.a8kj.configuration.parent.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class Method {

    private final Object type;

    public abstract void execute();
}
