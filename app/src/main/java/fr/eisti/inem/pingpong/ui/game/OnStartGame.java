package fr.eisti.inem.pingpong.ui.game;

import android.view.View;

public class OnStartGame implements View.OnClickListener{

    private NewGamePlayerSelect ngps;

    public OnStartGame(NewGamePlayerSelect newGamePlayerSelect){
        this.ngps = newGamePlayerSelect;
    }

    @Override
    public void onClick(View v) {
        this.ngps.startGame();
    }
}
