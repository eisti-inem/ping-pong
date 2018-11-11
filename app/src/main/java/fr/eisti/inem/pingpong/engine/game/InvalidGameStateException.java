package fr.eisti.inem.pingpong.engine.game;

/**
 * Exception thrown when the game has invalid parmeters for the called state.
 */
public class InvalidGameStateException extends Exception {

    /**
     * Constructs a new {@link InvalidGameStateException}.
     *
     * @param message the detail message
     */
    public InvalidGameStateException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link InvalidGameStateException}.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidGameStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
