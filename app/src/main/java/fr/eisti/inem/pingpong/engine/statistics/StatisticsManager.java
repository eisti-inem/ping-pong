package fr.eisti.inem.pingpong.engine.statistics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.eisti.inem.pingpong.engine.AbstractManager;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;

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
}
