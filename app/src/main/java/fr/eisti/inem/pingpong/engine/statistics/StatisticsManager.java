package fr.eisti.inem.pingpong.engine.statistics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.eisti.inem.pingpong.engine.AbstractManager;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;
import fr.eisti.inem.pingpong.engine.user.User;

public class StatisticsManager extends AbstractManager {
    public StatisticsManager(Context context) {
        super(context);
    }

    public void persist(Statistic statistic) {
        SQLiteDatabase database = EngineManager.get().getDatabaseHelper().getWritableDatabase();

        String tableName;
        switch (statistic.getStatisticScope()) {
            case GAME:
                tableName = PingPongSQLHelper.GAME_STATISTICS_TABLE_NAME;
                break;
            case USER:
                tableName = PingPongSQLHelper.USER_STATISTICS_TABLE_NAME;
                break;
            case USER_GAME:
                tableName = PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_NAME;
                break;
            default:
                tableName = "";
        }

        database.insert(tableName, null, statistic.getValues());
    }

    public UserStatistic get(StatisticType statisticType, User user)
            throws StatisticNotFoundException {
        return new UserStatistic(statisticType, user);
    }

    public UserStatistic getOrCreate(StatisticType statisticType, User user) {
        try {
            return get(statisticType, user);
        } catch (StatisticNotFoundException e) {
            return UserStatistic.createNew(statisticType, user, 0);
        }
    }

    public GameStatistic get(StatisticType statisticType, Game game)
            throws StatisticNotFoundException {
        return new GameStatistic(statisticType, game);
    }

    public GameStatistic getOrCreate(StatisticType statisticType, Game game) {
        try {
            return get(statisticType, game);
        } catch (StatisticNotFoundException e) {
            return GameStatistic.createNew(statisticType, game, 0);
        }
    }

    public UserGameStatistic get(StatisticType statisticType, User user, Game game)
            throws StatisticNotFoundException {
        return new UserGameStatistic(statisticType, user, game);
    }

    public UserGameStatistic getOrCreate(StatisticType statisticType, User user, Game game) {
        try {
            return get(statisticType, user, game);
        } catch (StatisticNotFoundException e) {
            return UserGameStatistic.createNew(statisticType, user, game, 0);
        }
    }
}
