package fr.eisti.inem.pingpong.engine.statistics;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;

public class StatisticType implements Serializable {

    private Integer id;
    private String statisticTypeName;

    public StatisticType(String statisticTypeName) throws StatisticTypeNotFoundException {
        this.statisticTypeName = statisticTypeName;

        SQLiteDatabase database = EngineManager.get().getDatabaseHelper().getReadableDatabase();

        Cursor result = database.query(
                PingPongSQLHelper.STATISTIC_TYPE_TABLE_NAME,
                PingPongSQLHelper.STATISTIC_TYPE_TABLE_COLUMNS,
                "statisticTypeName = ?",
                new String[] {statisticTypeName},
                null, null, null, "1");

        if (result.getCount() != 1) {
            throw new StatisticTypeNotFoundException("Invalid statistic name.");
        }

        result.moveToFirst();
        this.id = result.getInt(0);
    }

    public Integer getId() {
        return id;
    }

    public String getStatisticTypeName() {
        return statisticTypeName;
    }
}
