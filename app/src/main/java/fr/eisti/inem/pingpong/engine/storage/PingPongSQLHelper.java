package fr.eisti.inem.pingpong.engine.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PingPongSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PingPong";

    public static final String USER_TABLE_NAME = "users";

    public static final String[] USER_TABLE_COLUMNS =
            {"id", "userName", "profilePicturePath", "firstName", "lastName"};

    public static final String GAME_TABLE_NAME = "game";

    public static final String[] GAME_TABLE_COLUMNS =
            {"id"};

    public static final String STATISTIC_TYPE_TABLE_NAME = "statisticType";

    public static final String[] STATISTIC_TYPE_TABLE_COLUMNS =
            {"id", "statisticTypeName"};

    public static final String USER_STATISTICS_TABLE_NAME = "userStatistics";

    public static final String[] USER_STATISTICS_TABLE_COLUMNS =
            {"id", "userId", "statTypeId", "statValue"};

    public static final String GAME_STATISTICS_TABLE_NAME = "gameStatistics";

    public static final String[] GAME_STATISTICS_TABLE_COLUMNS =
            {"id", "gameId", "statTypeId", "statValue"};

    public static final String USER_GAME_STATISTICS_TABLE_NAME = "userGameStatistics";

    public static final String[] USER_GAME_STATISTICS_TABLE_COLUMNS =
            {"id", "userId", "gameId", "statTypeId", "statValue"};

    // Relation tables
    public static final String GAME_PLAYERS_REL_TABLE_NAME = "gamePayers";

    public static final String[] GAME_PLAYERS_REL_TABLE_COLUMNS =
            {"id", "userId", "gameId"};

    private static final int DATABASE_VERSION = 1;

    public PingPongSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Build the Users table
        db.execSQL(String.format("CREATE TABLE %s " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName TEXT," +
                "profilePicturePath TEXT," +
                "firstName TEXT," +
                "lastName TEXT)", USER_TABLE_NAME));

        // Build the Game table
        db.execSQL(String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT)", GAME_TABLE_NAME));
        // Build the GamePlayers table
        db.execSQL(String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," +
                "gameId INTEGER)", GAME_PLAYERS_REL_TABLE_NAME));

        // Build the StatisticType table
        db.execSQL(String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "statisticTypeName TEXT)", STATISTIC_TYPE_TABLE_NAME));
        // Build the UserStatistics table
        db.execSQL(String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," +
                "statTypeId INTEGER," +
                "statValue INTEGER)", USER_STATISTICS_TABLE_NAME));
        // Build the GameStatistics table
        db.execSQL(String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "gameId INTEGER," +
                "statTypeId INTEGER," +
                "statValue INTEGER)", GAME_STATISTICS_TABLE_NAME));
        // Build the UserGameStatistics table
        db.execSQL(String.format("CREATE TABLE %s (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," +
                "gameId INTEGER," +
                "statTypeId INTEGER," +
                "statValue INTEGER)", USER_GAME_STATISTICS_TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing for now, we are in the version 1 of the database.
    }
}
