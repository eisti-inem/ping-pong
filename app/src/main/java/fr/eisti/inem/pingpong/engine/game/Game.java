package fr.eisti.inem.pingpong.engine.game;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserNotFoundException;

/**
 * Represents an instance of a ping pong game. A game instance is self-standing and contains
 * every information needed about the game itself.
 * A game can have several players and will run in rounds. The game itself should be initialized
 * by the {@link GameManager}.
 */
public class Game {

    enum PlayerPosition {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    private List<User> players;
    private Queue<User> playerQueue;
    private Map<PlayerPosition, User> playerPositions;

    /**
     * Create a new {@link Game}. For now, this implementation of a {@link Game} does not support
     * less than 5 players.
     *
     * @param players the initial list of players in the game
     */
    public Game(List<User> players) {
        this.players = players;

        this.playerQueue = new ArrayDeque<>();
        this.playerQueue.addAll(players);

        this.playerPositions = new HashMap<>();
    }

    /**
     * Add a player to the currently working game.
     *
     * @param user the player to add
     */
    public Game addPlayer(User user) {
        this.players.add(user);
        this.playerQueue.add(user);

        return this;
    }

    /**
     * Remove a player form the currently working game.
     *
     * @param user the player to remove
     * @return the game itself
     * @throws UserNotFoundException if the player does not exist in the game
     */
    public Game removePlayer(User user) throws UserNotFoundException {
        // TODO
        return this;
    }

    /**
     * Marks the given player as having lost. This will update the player statistics
     * accordingly but will not remove the player from the table.
     * To do so, {@link #removePlayerFromTable(User)}.
     *
     * @param player the player that has lost
     * @return the game itself
     * @throws UserNotFoundException if the player does not exist in the game
     */
    public Game hasLost(User player) throws UserNotFoundException {
        // TODO
        return this;
    }

    /**
     * Remove a player from the current table.
     *
     * @param player the player to remove
     * @return the game itself
     * @throws UserNotFoundException if the player is not part of the game or not part of the table
     */
    public Game removePlayerFromTable(User player) throws UserNotFoundException {
        if (!players.contains(player)) {
            throw new UserNotFoundException("The user is not part of the current game.");
        }

        PlayerPosition playerPosition = getPositionForPlayer(player);
        if (playerPosition == null) {
            throw new UserNotFoundException("The current user is not part of the table.");
        }

        // Remove the player from the table
        playerPositions.remove(playerPosition);

        // Add the player to the queue
        playerQueue.add(player);

        return this;
    }

    /**
     * Once {@link #removePlayerFromTable(User)} has been called, a new player has to take place
     * in the game. This player is the one at the beginning of the queue.
     * When called, this method also updates the player positions.
     *
     * @return the user to use as a replacement.
     */
    public User getReplacementPlayer() {
        // TODO
        return null;
    }

    public User getPlayerAtPosition(PlayerPosition position) {
        return playerPositions.get(position);
    }

    /**
     * Saves the game resources and end the game.
     */
    public void endGame() {

    }

    /**
     * Get the position corresponding to the given user, or null if this user isn't positioned
     * in the table.
     *
     * @param player the player to get the position from
     * @return the position or null
     */
    private PlayerPosition getPositionForPlayer(User player) {
        if (player.equals(playerPositions.get(PlayerPosition.LEFT_TOP))) {
            return PlayerPosition.LEFT_TOP;
        } else if (player.equals(playerPositions.get(PlayerPosition.LEFT_BOTTOM))) {
            return PlayerPosition.LEFT_BOTTOM;
        } else if (player.equals(playerPositions.get(PlayerPosition.RIGHT_TOP))) {
            return PlayerPosition.RIGHT_TOP;
        } else if (player.equals(playerPositions.get(PlayerPosition.RIGHT_BOTTOM))) {
            return PlayerPosition.RIGHT_BOTTOM;
        } else {
            return null;
        }
    }

    private void initializePositions() {

    }
}
