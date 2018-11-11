package fr.eisti.inem.pingpong.engine.game;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserNotFoundException;

/**
 * Represents an instance of a ping pong game. A game instance is self-standing and contains
 * every information needed about the game itself.
 * A game can have several players and will run in rounds. The game itself should be initialized
 * by the {@link GameManager}.
 */
public class Game {

    public enum PlayerPosition {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    private static final String USER_NOT_FOUND_IN_GAME_ERROR =
            "The given player is not part of the game.";

    private static final String USER_NOT_FOUND_IN_TABLE_ERROR =
            "The given player is not part of the table.";

    private List<User> players;
    private Queue<User> playerQueue;
    private Map<PlayerPosition, User> playerPositions;
    private PlayerPosition lastEmptyPosition;

    /**
     * Create a new {@link Game}. For now, this implementation of a {@link Game} does not support
     * less than 5 players.
     *
     * @param players the initial list of players in the game
     */
    public Game(List<User> players) {
        this.players = players;

        randomizePlayersOrder();
        this.playerQueue = new ArrayDeque<>();
        this.playerQueue.addAll(players);

        this.playerPositions = new HashMap<>();
    }

    /**
     * Add a player to the currently working game.
     *
     * @param user the player to add
     */
    public Game addPlayerToGame(User user) {
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
    public Game removePlayerFromGame(User user) throws UserNotFoundException {
        if (!players.contains(user)) {
            throw new UserNotFoundException(USER_NOT_FOUND_IN_GAME_ERROR);
        }

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
    public Game markAsLoser(User player) throws UserNotFoundException {
        if (!playerPositions.containsValue(player)) {
            throw new UserNotFoundException(USER_NOT_FOUND_IN_TABLE_ERROR);
        }

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
        if (!playerPositions.containsValue(player)) {
            throw new UserNotFoundException(USER_NOT_FOUND_IN_TABLE_ERROR);
        }

        PlayerPosition playerPosition = getPositionForPlayer(player);
        // Remove the player from the table
        playerPositions.remove(playerPosition);
        // Mark the position as free
        lastEmptyPosition = playerPosition;
        // Add the player to the queue
        playerQueue.add(player);

        return this;
    }

    /**
     * Once {@link #removePlayerFromTable(User)} has been called, a new player has to take place
     * in the game. This player is the one at the beginning of the queue.
     * When called, this method also updates the player positions.
     *
     * @return the game itself
     */
    public Game getReplacementPlayer() {
        PlayerPosition freePosition = lastEmptyPosition;


        if (freePosition != null) {
            User newPlayer = playerQueue.poll();

            if (newPlayer != null) {
                playerPositions.put(freePosition, newPlayer);
                // Make sure that the last empty position is not the one we just filled in
                lastEmptyPosition = null;
            } else {
                // TODO: Log an error
            }
        } else {
            // TODO: Log an error
        }

        return this;
    }

    public User getPlayerAtPosition(PlayerPosition position) {
        return playerPositions.get(position);
    }

    /**
     * Start the game, put the players in the queue on the table.
     *
     * @return the game itself
     * @throws InvalidGameStateException if the queue has less than 2 players
     */
    public Game startGame() throws InvalidGameStateException {
        if (playerQueue.size() < 2) {
            throw new InvalidGameStateException("The current game has less than 2 players.");
        } else {
            // We are sure to have at least 2 players
            playerPositions.put(PlayerPosition.LEFT_TOP, playerQueue.poll());
            playerPositions.put(PlayerPosition.RIGHT_TOP, playerQueue.poll());

            // Those two puts might be null, if we don't have 4 players in the game
            playerPositions.put(PlayerPosition.LEFT_BOTTOM, playerQueue.poll());
            playerPositions.put(PlayerPosition.RIGHT_BOTTOM, playerQueue.poll());
        }

        return this;
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

    private void randomizePlayersOrder() {
        Random random = new Random();

        // start from end of the list
        for (int i = players.size() - 1; i >= 1; i--)
        {
            // get a random index j such that 0 <= j <= i
            int j = random.nextInt(i + 1);

            // swap element at i'th position in the list with element at
            // randomly generated index j
            User player = players.get(i);
            players.set(i, players.get(j));
            players.set(j, player);
        }
    }
}
