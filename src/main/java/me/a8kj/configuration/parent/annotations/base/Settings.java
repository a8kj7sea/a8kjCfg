package me.a8kj.configuration.parent.annotations.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to configure settings related to file management for a specific
 * class.
 * This annotation can be applied to types (classes, interfaces, etc.).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Settings {

    /**
     * Determines whether a backup should be created automatically when the file is
     * saved.
     * 
     * @return {@code true} if a backup should be created on save;
     *         {@code false} otherwise. Defaults to {@code false}.
     */
    boolean backupOnSave() default false;

    /**
     * Indicates whether the file should be saved with default settings.
     * 
     * @return {@code true} if the file should be saved using default settings;
     *         {@code false} otherwise. Defaults to {@code true}.
     */
    boolean defaultSave() default true;

    /**
     * Specifies the location and backup configuration for the file.
     * 
     * @return a {@link Where} annotation instance that defines the file path and
     *         backup settings.
     *         Defaults to a basic {@link Where} configuration.
     */
    Where where() default @Where;

}
