package fr.eisti.inem.pingpong.engine.statistics;

/**
 * Exception thrown when the statistic could not be found in the database.
 */
public class StatisticNotFoundException extends Exception {

    /**
     * Constructs a new {@link StatisticNotFoundException}.
     *
     * @param message the detail message
     */
    public StatisticNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link StatisticNotFoundException}.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public StatisticNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
