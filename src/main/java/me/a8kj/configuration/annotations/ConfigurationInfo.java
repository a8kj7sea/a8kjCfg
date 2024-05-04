package me.a8kj.configuration.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to provide configuration information for a class.
 * Indicates whether default data and new additions should be saved in the
 * configuration file,
 * and allows specifying a custom path for the configuration file.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigurationInfo {

    /**
     * Specifies whether default data and new additions should be saved in the
     * configuration file.
     * 
     * @return The save state.
     */
    boolean defaultSave() default true;

    /**
     * Specifies the path for the configuration file.
     * The path is relative to the plugins folder.
     * For example, to designate configuration files for the arenas of a game,
     * set the path as follows:
     * {@code@ConfigurationInfo(defaultSave = true, path = "files/arenas")}
     * Default option is to use the plugin's data folder.
     * 
     * @return The specified path for the configuration file.
     */
    String path() default "";

}
