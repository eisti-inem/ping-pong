package fr.eisti.inem.pingpong.engine;

import android.content.Context;

import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;
import fr.eisti.inem.pingpong.engine.user.UserManager;

/**
 * This class is a facade to help accessing the different components of the engine package.
 *
 * @version $Id$
 * @since 1.0
 */
public class EngineManager {

    private static EngineManager singleton;

    /**
     * Initialize the EngineManager with the given context. The engine manager will only be
     * initialized if an instance does not exists yet.
     *
     * @param context the current application context
     * @return the initialized engine manager
     */
    public static EngineManager initialize(Context context) {
        if (EngineManager.singleton != null) {
            EngineManager.singleton = new EngineManager(context);
        }

        return EngineManager.singleton;
    }

    /**
     * Get the current engine manager.
     *
     * @return the engine manager. Will return null if the engine manager isn't initialized yet.
     */
    public static EngineManager get() {
        return EngineManager.singleton;
    }

    private UserManager userManager;
    private GameManager gameManager;
    private StatisticsManager statisticsManager;
    private PingPongSQLHelper databaseHelper;

    /**
     * Builds a new instance of the {@link EngineManager}.
     * This will instantiate every necessary component of the engine package.
     *
     * @param context the current application context needed for the initialization.
     */
    public EngineManager(Context context) {
        userManager = new UserManager(context);
        gameManager = new GameManager(context);
        statisticsManager = new StatisticsManager(context);
        databaseHelper = new PingPongSQLHelper(context);
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

    public PingPongSQLHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
