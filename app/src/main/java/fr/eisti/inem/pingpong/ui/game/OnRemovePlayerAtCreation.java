package fr.eisti.inem.pingpong.ui.game;

import android.view.View;

import fr.eisti.inem.pingpong.engine.user.User;

class OnRemovePlayerAtCreation implements View.OnClickListener {
    private final int viewId;
    private NewGamePlayerSelect ngps;
    private User user;

    public OnRemovePlayerAtCreation(NewGamePlayerSelect newGamePlayerSelect, User user, int id) {
        this.ngps = newGamePlayerSelect;
        this.user = user;
        this.viewId = id;
    }

    @Override
    public void onClick(View v) {
        ngps.removeUser(user,viewId);
    }
}
