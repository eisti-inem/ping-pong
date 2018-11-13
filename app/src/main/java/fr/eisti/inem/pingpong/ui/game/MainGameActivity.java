package fr.eisti.inem.pingpong.ui.game;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.game.Game;
import fr.eisti.inem.pingpong.engine.game.InvalidGameStateException;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserNotFoundException;

public class MainGameActivity extends AppCompatActivity  implements PingPongTable.OnFragmentInteractionListener,
        GameQueue.OnFragmentInteractionListener{

    private EngineManager engineManager;
    private Game currentGame;
    private List<User> playerQueue;
    private Map<Game.PlayerPosition,TextView> tablePosition;
    private LinearLayout queueDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        this.engineManager = EngineManager.get();
        this.playerQueue = new ArrayList<>();
        this.tablePosition = new HashMap<>();
        this.currentGame = (Game) getIntent().getExtras().get("game");
        try {
            this.currentGame.startGame();
        } catch (InvalidGameStateException e) {
            e.printStackTrace();
        }
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

        this.queueDisplay = findViewById(R.id.queueLayout);
        // Display players in the table
        displayPlayersInTable();
        displayQueue();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    // When a player is clicked
    // Update the method signature if needed
    public void onPlayerClicked(User user) {
        try {
            currentGame.markAsLoser(user).removePlayerFromTable(user).getReplacementPlayer();
            displayPlayersInTable();
            displayQueue();
        } catch (UserNotFoundException e) {
            // TODO: Do something, log an error or make a toast
        }
    }

    private void displayPlayersInTable() {
        for(Map.Entry<Game.PlayerPosition, TextView> entry : this.tablePosition.entrySet()){
            if (this.currentGame.getPlayerAtPosition(entry.getKey()) != null){
                TextView tv = entry.getValue();
                tv.setText(this.currentGame.getPlayerAtPosition(entry.getKey()).getUserName());
                tv.setOnClickListener(new OnPlayerOutListener(this,this.currentGame.getPlayerAtPosition(entry.getKey())));
            }
        }
    }

    private void displayQueue(){
        Queue<User> queueList = currentGame.getPlayerQueue();
        this.queueDisplay.removeAllViews();
        while(!(queueList.isEmpty())){
            TextView tv = new TextView(this);
            User player = queueList.poll();
            tv.setText(player.getUserName());
            this.queueDisplay.addView(tv);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public Game getCurrentGame() {
        return currentGame;
    }

}
