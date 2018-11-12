package fr.eisti.inem.pingpong.engine.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;
import fr.eisti.inem.pingpong.engine.user.User;

public class UserStatistic extends Statistic {

    public static UserStatistic createNew(StatisticType statisticType, User user, Integer value) {
        return new UserStatistic(statisticType, user, value);
    }

    private User user;

    public UserStatistic(StatisticType statisticType, User user)
            throws StatisticNotFoundException {
        this.statisticScope = StatisticScope.GAME;
        this.statisticType = statisticType;
        this.user = user;

        // Hydrate
        loadStatContent();
    }

    private UserStatistic(StatisticType statisticType, User user, Integer value) {
        this.statisticScope = StatisticScope.GAME;
        this.statisticType = statisticType;
        this.user = user;
        this.value = value;
    }

    protected void loadStatContent() throws StatisticNotFoundException {
        SQLiteDatabase database = EngineManager.get().getDatabaseHelper().getReadableDatabase();

        Cursor result = database.query(
                PingPongSQLHelper.USER_STATISTICS_TABLE_NAME,
                new String[] {
                        PingPongSQLHelper.USER_STATISTICS_TABLE_COLUMNS[0],
                        PingPongSQLHelper.USER_STATISTICS_TABLE_COLUMNS[3] },
                String.format("userId = ?s AND statTypeId = ?s"),
                new String[] {Integer.toString(this.user.getId()),
                        Integer.toString(statisticType.getId())},
                null, null, null, "1");

        if (result.getCount() != 1) {
            throw new StatisticNotFoundException(STATISTIC_NOT_FOUND_ERROR);
        }

        result.moveToFirst();
        this.id = result.getInt(0);
        this.value = result.getInt(1);
    }

    public ContentValues getValues() {
        ContentValues returnValues = new ContentValues();

        returnValues.put(PingPongSQLHelper.USER_STATISTICS_TABLE_COLUMNS[0], this.id);
        returnValues.put(PingPongSQLHelper.USER_STATISTICS_TABLE_COLUMNS[1], this.user.getId());
        returnValues.put(PingPongSQLHelper.USER_STATISTICS_TABLE_COLUMNS[2],
                this.statisticType.getId());
        returnValues.put(PingPongSQLHelper.USER_STATISTICS_TABLE_COLUMNS[3], this.value);

        return returnValues;
    }
}
