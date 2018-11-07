package fr.eisti.inem.pingpong.engine.user;

/**
 * Exception thrown when the user given to a method has invalid properties.
 */
public class InvalidUserException extends Exception {

    /**
     * Constructs a new {@link InvalidUserException}.
     *
     * @param message the detail message
     */
    public InvalidUserException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link InvalidUserException}.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
