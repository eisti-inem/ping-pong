package fr.eisti.inem.pingpong.engine.user;

/**
 * Exception thrown when no user has been found with the given criterias.
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructs a new {@link UserNotFoundException}.
     *
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link UserNotFoundException}.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
