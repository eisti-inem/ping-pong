package fr.eisti.inem.pingpong.ui.game;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_player_selection);
        this.engineManager = EngineManager.get();
        this.playerList = new HashMap<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.initialAddPlayer);
        fab.setOnClickListener(new OnFabClickListener(this));
    }



}
