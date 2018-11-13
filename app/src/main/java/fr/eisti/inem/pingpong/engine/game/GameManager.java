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

    /**
     * Create a new instance of the game. Once the {@link Game} instance is up, it is fully
     * independent.
     *
     * @param players the base players in the game. At least 2 players should be given, else
     *                {@link Game#startGame()} will throw a {@link InvalidGameStateException}.
     * @return the newly created game
     */
    public Game newGame(List<User> players) {
        return new Game(players);
    }
}
