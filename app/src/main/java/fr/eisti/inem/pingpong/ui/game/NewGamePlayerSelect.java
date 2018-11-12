package fr.eisti.inem.pingpong.ui.game;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.game.InvalidGameStateException;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserNotFoundException;

public class NewGamePlayerSelect extends AppCompatActivity implements PingPongTable.OnFragmentInteractionListener,
    GameQueue.OnFragmentInteractionListener{

    private EngineManager engineManager;
    private Map<Game.PlayerPosition, User> tableContent;
    private Game currentGame;
    private List<User> playerQueue;
    private Map<Game.PlayerPosition,TextView> tablePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_player_selection);
        this.engineManager = EngineManager.get();
        this.tableContent = new HashMap<Game.PlayerPosition, User>();
        this.playerQueue = new ArrayList<>();
        this.currentGame = (Game) getIntent().getExtras().get("game");
        try {
            this.currentGame.startGame();
        } catch (InvalidGameStateException e) {
            e.printStackTrace();
        }
        //Fill the map of players
        this.tableContent.put(Game.PlayerPosition.LEFT_TOP,
                currentGame.getPlayerAtPosition(Game.PlayerPosition.LEFT_TOP));
        this.tableContent.put(Game.PlayerPosition.LEFT_BOTTOM,
                currentGame.getPlayerAtPosition(Game.PlayerPosition.LEFT_BOTTOM));
        this.tableContent.put(Game.PlayerPosition.RIGHT_TOP,
                currentGame.getPlayerAtPosition(Game.PlayerPosition.RIGHT_TOP));
        this.tableContent.put(Game.PlayerPosition.RIGHT_BOTTOM,
                currentGame.getPlayerAtPosition(Game.PlayerPosition.RIGHT_BOTTOM));

        //Fill the map of position in fragments
        this.tablePosition.put(Game.PlayerPosition.LEFT_TOP,
                (TextView) findViewById(R.id.topLeft));
        this.tablePosition.put(Game.PlayerPosition.LEFT_BOTTOM,
                (TextView) findViewById(R.id.bottomLetf));
        this.tablePosition.put(Game.PlayerPosition.RIGHT_TOP,
                (TextView) findViewById(R.id.topRight));
        this.tablePosition.put(Game.PlayerPosition.RIGHT_BOTTOM,
                (TextView) findViewById(R.id.bottomRight));
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
        for(Map.Entry entry : this.tablePosition.entrySet()){
            if(!this.tableContent.get(entry.getKey()).equals(null)){
                TextView tv = (TextView)entry.getValue();
                tv.setText(this.tableContent.get(entry.getKey()).getUserName());
                tv.setOnClickListener(new OnPlayerOutListener(this,this.tableContent.get(entry.getKey())));
            }
        }
    }


    public void rotatePlayer(User user){
        try {
            this.currentGame.markAsLoser(user).removePlayerFromTable(user).getReplacementPlayer();
            displayPlayersInTable();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
