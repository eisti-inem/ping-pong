package fr.eisti.inem.pingpong.engine.user;

import android.content.ContentValues;

import java.io.Serializable;

import fr.eisti.inem.pingpong.engine.storage.PingPongSQLHelper;

/**
 * Represents an instance of a user.
 */
public class User implements Serializable {

    private Integer id;
    private String userName;
    private String profilePicturePath;
    private String firstName;
    private String lastName;

    /**
     * Builds a new instance of a User.
     *
     * @param id
     * @param userName
     * @param profilePicturePath
     * @param firstName
     * @param lastName
     */
    public User(Integer id, String userName, String profilePicturePath,
                String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.profilePicturePath = profilePicturePath;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Builds a new instance of a User without specifying a user ID.
     *
     * @param userName
     * @param profilePicturePath
     * @param firstName
     * @param lastName
     */
    public User(String userName, String profilePicturePath, String firstName, String lastName) {
        this.userName = userName;
        this.profilePicturePath = profilePicturePath;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the user unique identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the user ID.
     *
     * @see #getId()
     * @param id the user unique identifier
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the user name.
     *
     * @see #getUserName()
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return a path to the user profile picture
     */
    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    /**
     * Set the user profile picture path.
     *
     * @see #getProfilePicturePath()
     * @param profilePicturePath
     */
    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    /**
     * @return the user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user first name.
     *
     * @see #getFirstName()
     * @param firstName the user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user last name.
     *
     * @see #getLastName()
     * @param lastName the user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user2 = (User) obj;

            return this.id == user2.id;
        }

        return false;
    }

    public ContentValues getValues() {
        ContentValues returnValues = new ContentValues();

        returnValues.put(PingPongSQLHelper.USER_TABLE_COLUMNS[0], this.id);
        returnValues.put(PingPongSQLHelper.USER_TABLE_COLUMNS[1], this.userName);
        returnValues.put(PingPongSQLHelper.USER_TABLE_COLUMNS[2], this.profilePicturePath);
        returnValues.put(PingPongSQLHelper.USER_TABLE_COLUMNS[3], this.firstName);
        returnValues.put(PingPongSQLHelper.USER_TABLE_COLUMNS[4], this.lastName);

        return returnValues;
    }
}
