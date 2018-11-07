package fr.eisti.inem.pingpong.engine.game;

import android.content.Context;

import java.util.List;

import fr.eisti.inem.pingpong.engine.AbstractManager;
import fr.eisti.inem.pingpong.engine.user.User;

/**
 * The game manager aims to create and manage game instances.
 */
public class GameManager extends AbstractManager {
    public GameManager(Context context) {
        super(context);
    }

    public Game newGame(List<User> players) {
        return new Game(players);
    }
}
