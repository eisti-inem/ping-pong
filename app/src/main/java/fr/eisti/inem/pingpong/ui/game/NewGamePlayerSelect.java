package fr.eisti.inem.pingpong.ui.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserNotFoundException;

public class NewGamePlayerSelect extends AppCompatActivity {

    private EngineManager engineManager;
    private Map<Integer,User> playerList;
    private Game currentGame;
    private List<User> tablePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_player_selection);
        this.engineManager = EngineManager.get();
        this.playerList = new HashMap<>();

        this.currentGame = (Game) getIntent().getExtras().get("game");

        // TODO: use currentGame.initialize() once the method has been implemented

        // Display players in the table
        displayPlayersInTable();
    }

    // When a player is clicked
    // Update the method signature if needed
    public void onPlayerClicked(User user) {
        try {
            currentGame.markAsLoser(user).removePlayerFromTable(user).getReplacementPlayer();
            displayPlayersInTable();
        } catch (UserNotFoundException e) {
            // TODO: Do something, log an error or make a toast
        }
    }

    private void displayPlayersInTable() {
        User playerAtLeftTopPosition = currentGame
                .getPlayerAtPosition(Game.PlayerPosition.LEFT_TOP);
        User playerAtLeftBottomPosition = currentGame
                .getPlayerAtPosition(Game.PlayerPosition.LEFT_BOTTOM);
        // Same for RIGHT_TOP and RIGHT_BOTTOM
        User playerAtRightTopPosition = currentGame
                .getPlayerAtPosition(Game.PlayerPosition.RIGHT_TOP);
        User playerAtRightBottomPosition = currentGame
                .getPlayerAtPosition(Game.PlayerPosition.RIGHT_BOTTOM);

    }



}
