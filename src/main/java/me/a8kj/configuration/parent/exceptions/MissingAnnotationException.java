package me.a8kj.configuration.parent.exceptions;

/**
 * Exception thrown when a required annotation is missing from a class.
 * This exception indicates that the class being processed does not have
 * the required annotation, which is essential for proper configuration or
 * functionality.
 */
public class MissingAnnotationException extends RuntimeException {

    /**
     * Constructs a new MissingAnnotationException with a default detailed message.
     * The message informs the user that a required annotation is missing and
     * suggests
     * ensuring that the class is annotated with the appropriate "Settings"
     * annotation.
     */
    public MissingAnnotationException() {
        super("The required annotation is missing. Please ensure that the class is annotated with the appropriate \"Settings\" annotation.");
    }

    /**
     * Constructs a new MissingAnnotationException with a specified detail message.
     * The message can be customized to provide more specific information about the
     * missing annotation.
     *
     * @param message the detail message, which is saved for later retrieval by the
     *                {@link #getMessage()} method.
     */
    public MissingAnnotationException(String message) {
        super(message);
    }
}
