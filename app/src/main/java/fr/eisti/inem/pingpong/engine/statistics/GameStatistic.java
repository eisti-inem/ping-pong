package fr.eisti.inem.pingpong.engine.statistics;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;

public class GameStatistic extends Statistic {

    public static GameStatistic createNew(StatisticType statisticType, Game game, Integer value) {
        return new GameStatistic(statisticType, game, value);
    }

    private Game game;

    public GameStatistic(StatisticType statisticType, Game game)
            throws StatisticNotFoundException {
        this.statisticScope = StatisticScope.GAME;
        this.statisticType = statisticType;
        this.game = game;

        // Hydrate
        loadStatContent();
    }

    private GameStatistic(StatisticType statisticType, Game game, Integer value) {
        this.statisticScope = StatisticScope.GAME;
        this.statisticType = statisticType;
        this.game = game;
        this.value = value;
    }

    protected void loadStatContent() throws StatisticNotFoundException {
        SQLiteDatabase database = EngineManager.get().getDatabaseHelper().getReadableDatabase();

        Cursor result = database.query(
                PingPongSQLHelper.GAME_STATISTICS_TABLE_NAME,
                new String[] {
                        PingPongSQLHelper.GAME_STATISTICS_TABLE_COLUMNS[0],
                        PingPongSQLHelper.GAME_STATISTICS_TABLE_COLUMNS[3] },
                String.format("gameId = ?s AND statTypeId = ?s"),
                new String[] {Integer.toString(this.game.getId()),
                        Integer.toString(statisticType.getId())},
                null, null, null, "1");

        if (result.getCount() != 1) {
            throw new StatisticNotFoundException(
                    "The given statistic could not be found in the database.");
        }

        result.moveToFirst();
        this.id = result.getInt(0);
        this.value = result.getInt(1);
    }

    public ContentValues getValues() {
        ContentValues returnValues = new ContentValues();

        returnValues.put(PingPongSQLHelper.GAME_STATISTICS_TABLE_COLUMNS[0], this.id);
        returnValues.put(PingPongSQLHelper.GAME_STATISTICS_TABLE_COLUMNS[1], this.game.getId());
        returnValues.put(PingPongSQLHelper.GAME_STATISTICS_TABLE_COLUMNS[2],
                this.statisticType.getId());
        returnValues.put(PingPongSQLHelper.GAME_STATISTICS_TABLE_COLUMNS[3], this.value);

        return returnValues;
    }
}
