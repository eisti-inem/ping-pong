package fr.eisti.inem.pingpong.engine.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PingPongSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PingPong";

    public static final String USER_TABLE_NAME = "users";

    public static final String[] USER_TABLE_COLUMNS =
            {"id", "userName", "profilePicturePath", "firstName", "lastName"};

    public static final String STATISTICS_TABLE_NAME = "statistics";

    private static final int DATABASE_VERSION = 1;

    public PingPongSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Build the Users database
        db.execSQL(String.format("CREATE TABLE %s " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "userName TEXT," +
                "profilePicturePath TEXT," +
                "firstName TEXT," +
                "lastName TEXT)", USER_TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing for now, we are in the version 1 of the database.
    }
}
