package fr.eisti.inem.pingpong.engine.game;

import java.util.List;
import java.util.Queue;

import fr.eisti.inem.pingpong.engine.user.User;

/**
 * Represents an instance of a ping pong game.
 * A game can have several players and will run in rounds. The game itself should be initialized
 * by the {@link GameManager}.
 */
public class Game {

    private List<User> players;
    private Queue<User> playerQueue;

    public Game(List<User> players) {
        this.players = players;
    }

    public remove
}
