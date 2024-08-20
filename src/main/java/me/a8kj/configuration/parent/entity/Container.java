package me.a8kj.configuration.parent.entity;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.a8kj.configuration.parent.utils.java.SimpleMapManager;

/**
 * Abstract class representing a container that holds a name and a set of
 * key-value pairs.
 * Provides basic functionality for managing and accessing stored values using a
 * map manager.
 */
@RequiredArgsConstructor
public abstract class Container {

    /**
     * The name associated with this container.
     * 
     * @return the name of the container.
     */
    @Getter
    private final String name;

    /**
     * A map holding key-value pairs of stored values.
     * This map is initialized as an empty hash map.
     */
    private final Map<String, Object> values = Maps.newHashMap();

    /**
     * A manager that handles operations on the values map.
     * Initialized with the current values map.
     * 
     * @return the map manager for handling the values.
     */
    @Getter
    private final SimpleMapManager<String, Object> manager = new SimpleMapManager<>(values);

}
