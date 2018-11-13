package fr.eisti.inem.pingpong.engine.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;
import fr.eisti.inem.pingpong.engine.user.User;

public class UserGameStatistic extends Statistic {

    public static UserGameStatistic createNew(StatisticType statisticType, User user, Game game, Integer value) {
        return new UserGameStatistic(statisticType, user, game, value);
    }

    private User user;
    private Game game;

    public UserGameStatistic(StatisticType statisticType, User user, Game game)
            throws StatisticNotFoundException {
        this.statisticScope = StatisticScope.GAME;
        this.statisticType = statisticType;
        this.user = user;
        this.game = game;

        // Hydrate
        loadStatContent();
    }

    private UserGameStatistic(StatisticType statisticType, User user, Game game, Integer value) {
        this.statisticScope = StatisticScope.GAME;
        this.statisticType = statisticType;
        this.user = user;
        this.game = game;
        this.value = value;
    }

    protected void loadStatContent() throws StatisticNotFoundException {
        SQLiteDatabase database = EngineManager.get().getDatabaseHelper().getReadableDatabase();

        Cursor result = database.query(
                PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_NAME,
                new String[] {
                        PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[0],
                        PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[4] },
                String.format("userId = ? AND gameId = ? AND statTypeId = ?"),
                new String[] {
                        Integer.toString(this.user.getId()),
                        Integer.toString(this.game.getId()),
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

        returnValues.put(PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[0], this.id);
        returnValues.put(PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[1],
                this.user.getId());
        returnValues.put(PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[2],
                this.game.getId());
        returnValues.put(PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[3],
                this.statisticType.getId());
        returnValues.put(PingPongSQLHelper.USER_GAME_STATISTICS_TABLE_COLUMNS[4], this.value);

        return returnValues;
    }
}
