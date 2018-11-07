package fr.eisti.inem.pingpong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.user.User;

public class NewGamePlayerSelect extends AppCompatActivity {

    private EngineManager engineManager;
    private List<User> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_player_selection);
        this.engineManager = EngineManager.get();
        this.playerList = new ArrayList<>();
    }



}
