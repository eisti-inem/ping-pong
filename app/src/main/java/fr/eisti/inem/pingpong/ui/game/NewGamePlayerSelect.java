package fr.eisti.inem.pingpong.ui.game;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.user.User;

public class NewGamePlayerSelect extends AppCompatActivity {

    private EngineManager engineManager;
    private Map<Integer,User> playerList;
    private Button addFromScratch;
    private Button addFromDatabase;
    private Button startGameButton;
    private LinearLayout playerListDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_player_selection);
        this.engineManager = EngineManager.get();
        this.playerList = new HashMap<>();
        this.addFromDatabase = findViewById(R.id.addFromDataBase);
        this.addFromScratch = findViewById(R.id.addFromScratch);
        this.startGameButton = findViewById(R.id.startNewGame);
        this.playerListDisplay = findViewById(R.id.playerList);
        this.addFromScratch.setOnClickListener(new OnAddFromScratchListener(this));
        this.addFromDatabase.setOnClickListener(new OnAddFromDataBase(this));
    }

    public void addUser(User user){
        this.playerList.put(user.getId(),user);
        TextView player = new TextView(this);

        if (!user.getFirstName().isEmpty() && !user.getLastName().isEmpty()) {
            player.setText(String.format("%s - %s %s",
                    user.getUserName(),
                    user.getFirstName(),
                    user.getLastName().toUpperCase()));
        } else {
            player.setText(user.getUserName());
        }

        this.playerListDisplay.addView(player);
        if(this.playerList.size() == 2){
            this.startGameButton.setOnClickListener(new OnStartGame(this));
        }
    }

    public void startGame() {
        Intent i = new Intent(NewGamePlayerSelect.this,MainGameActivity.class);
        //Create the list of users
        List<User> playerListliste = new ArrayList<>();
        for(Map.Entry entry : this.playerList.entrySet()){
            playerListliste.add((User) entry.getValue());
        }



        i.putExtra("game", this.engineManager.getGameManager().newGame(playerListliste));
        NewGamePlayerSelect.this.startActivity(i);
    }
}
