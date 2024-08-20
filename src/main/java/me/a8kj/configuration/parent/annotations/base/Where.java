package me.a8kj.configuration.parent.annotations.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define where a configuration file is located and how backups
 * should be handled.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Where {

    /**
     * Specifies the path to the configuration file.
     * 
     * @return the path where the configuration file should be located.
     *         Defaults to "null" string, meaning no specific path is set.
     */
    String path() default "null";

    /**
     * Indicates whether the path is custom. If {@code false}, the path is based on
     * the default configuration directory (e.g., {@code obj#getDataFolder()}).
     * 
     * @return {@code true} if a custom path is used; {@code false} if the default
     *         path is used.
     */
    boolean custom() default false;

    /**
     * Defines the backup configuration for the file.
     * This element is optional, and by default, a basic {@link Backup}
     * configuration is provided.
     * 
     * @return a {@link Backup} annotation instance specifying backup details.
     */
    Backup backup() default @Backup;

    /**
     * Annotation to define the backup configuration for a file.
     */
    @interface Backup {
        /**
         * Specifies the path where the backup file should be stored.
         * 
         * @return the path for the backup file. Defaults to "null" string, meaning no
         *         specific path is set.
         */
        String path() default "null";

        /**
         * Indicates whether the backup path is custom. If {@code false}, the path is
         * based on
         * the default configuration directory (e.g., {@code obj#getDataFolder()}).
         * 
         * @return {@code true} if a custom backup path is used; {@code false} if the
         *         default path is used.
         */
        boolean custom() default false;

        /**
         * Specifies the file extension for the backup file.
         * 
         * @return the file extension for the backup. Defaults to {@code ".backup"}.
         */
        String extension() default ".backup";
    }

}
