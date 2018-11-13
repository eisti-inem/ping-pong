package fr.eisti.inem.pingpong.engine.user;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.eisti.inem.pingpong.engine.AbstractManager;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;

/**
 * The user manager aims to provide top-level interfaces to the user management mechanism.
 * Its first aim is to provide a simple CRUD for accessing users in the application.
 */
public class UserManager extends AbstractManager {


    /**
     * Builds a new {@link UserManager}.
     *
     * @param context the curruent applicative context.
     */
    public UserManager(Context context) {
        super(context);
    }

    /**
     * Get a list of all users currently registered in the database.
     *
     * @return a list of {@link User}
     */
    public List<User> getAll() {
        Cursor c = EngineManager.get().getDatabaseHelper().getReadableDatabase().query(
                PingPongSQLHelper.USER_TABLE_NAME, PingPongSQLHelper.USER_TABLE_COLUMNS,
                null, null, null, null ,null);

        List<User> resultList = new ArrayList<>();

        while (c.moveToNext()) {
            resultList.add(new User(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4)));
        }

        return resultList;
    }

    /**
     * Get the user instance in the database having the corresponding user ID.
     *
     * @param userId the ID of the user
     * @return the corresponding user
     * @throws UserNotFoundException if no user could be found
     */
    public User get(Integer userId) throws UserNotFoundException {
        Cursor c = EngineManager.get().getDatabaseHelper().getReadableDatabase().query(
                PingPongSQLHelper.USER_TABLE_NAME, PingPongSQLHelper.USER_TABLE_COLUMNS,
                "id = ?", new String[] { Integer.toString(userId) }, null, null ,null, "1");

        if (!c.moveToNext()) {
            throw new UserNotFoundException("No user could be found in the database.");
        } else {
            return new User(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4));
        }
    }

    /**
     * Update the given user information.
     *
     * @param user the user to update
     * @throws UserNotFoundException if the corresponding user could not be found in the database
     */
    public void update(User user) throws UserNotFoundException {
        int result = EngineManager.get().getDatabaseHelper().getWritableDatabase().update(
                PingPongSQLHelper.USER_TABLE_NAME, user.getValues(),
                "id = ?", new String[] { Integer.toString(user.getId()) });

        if (result == 0) {
            throw new UserNotFoundException("No user could be found in the database.");
        }
    }

    /**
     * Delete the given user.
     *
     * @param user the user to delete
     * @throws UserNotFoundException if the corresponding user could not be found in the database
     */
    public void delete(User user) throws UserNotFoundException {
        int result = EngineManager.get().getDatabaseHelper().getWritableDatabase().delete(
                PingPongSQLHelper.USER_TABLE_NAME,
                "id = ?", new String[] { Integer.toString(user.getId()) });

        if (result == 0) {
            throw new UserNotFoundException("Unable to delete the user in the database.");
        }
    }

    /**
     * Adds a new user to the user database. In order to be added to the database, the user should
     * have a null ID.
     *
     * @param user the user to add
     * @return the created user. The user ID will be set according to its value in the database.
     * @throws InvalidUserException if the user ID is not null
     */
    public User add(User user) throws InvalidUserException {
        if (user.getId() != null) {
            throw new InvalidUserException("The user already has an ID defined.");
        }

        long id = EngineManager.get().getDatabaseHelper().getWritableDatabase().insert(
                PingPongSQLHelper.USER_TABLE_NAME, null, user.getValues());

        if (id != -1) {
            user.setId((int) id);
        }

        return user;
    }

    /**
     * This method is only dedicated to ease testing phases on the application. This will fill the
     * database with some users.
     */
    public void prefillUserDatabase() {
        User pinkiePie = new User("pinkiePie", "", "Pinkie", "Pie");
        User rarity = new User("rarity", "", "Ra", "Rity");
        User flutterShy = new User("flutterShy", "", "Flutter", "Shy");
        User rainbowDash = new User("rainbowDash", "", "Rainbow", "Dash");
        User twilightSparkle = new User("twilightSparkle", "", "Twilight", "Sparkle");
        User discord = new User("dIsC0rD", "", "Dis", "Cord");

        try {
            add(pinkiePie);
            add(rarity);
            add(flutterShy);
            add(rainbowDash);
            add(twilightSparkle);
            add(discord);
        } catch (InvalidUserException e) {
            // TODO : Log an error, this should not happen
        }
    }
}
