package fr.eisti.inem.pingpong.ui.game;

import android.view.View;

import fr.eisti.inem.pingpong.engine.user.User;

public class OnPlayerOutListener implements View.OnClickListener {
    private NewGamePlayerSelect newGamePlayerSelect;
    private User user;

    public OnPlayerOutListener(NewGamePlayerSelect ngps, User player){
        this.newGamePlayerSelect = ngps;
        this.user = player;
    }

    @Override
    public void onClick(View v) {
        this.newGamePlayerSelect.rotatePlayer(this.user);
    }
}
