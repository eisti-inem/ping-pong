package fr.eisti.inem.pingpong.engine.statistics;

/**
 * Exception thrown when the statistic type could not be initialized.
 */
public class StatisticTypeNotFoundException extends Exception {

    /**
     * Constructs a new {@link StatisticTypeNotFoundException}.
     *
     * @param message the detail message
     */
    public StatisticTypeNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@link StatisticTypeNotFoundException}.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public StatisticTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
