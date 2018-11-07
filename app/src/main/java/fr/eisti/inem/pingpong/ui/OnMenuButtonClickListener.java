package fr.eisti.inem.pingpong.ui;

import android.content.Intent;
import android.view.View;

import java.util.logging.Handler;

import fr.eisti.inem.pingpong.MainActivity;
import fr.eisti.inem.pingpong.NewGamePlayerSelect;
import fr.eisti.inem.pingpong.R;

public class OnMenuButtonClickListener implements View.OnClickListener {

    private MainActivity ma;
    private int buttonId;

    public OnMenuButtonClickListener(MainActivity ma, int id){
        this.ma = ma;
        this.buttonId = id;
    }

    @Override
    public void onClick(View v) {
        switch (this.buttonId){
            case R.id.newGameMenuButton:
                this.ma.startNewGame();
                break;
            default:
                break;
        }
    }
}
