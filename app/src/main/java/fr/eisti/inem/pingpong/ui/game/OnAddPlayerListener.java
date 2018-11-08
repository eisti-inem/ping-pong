package fr.eisti.inem.pingpong.ui.game;

import android.view.View;

/**
 * This listener will be bind to each player on the list.
 */

public class OnAddPlayerListener implements View.OnClickListener {

    private int playerId;
    private int viewId;

    public OnAddPlayerListener(int playerId, int viewId){
        this.playerId = playerId;
        this.viewId = viewId;
    }

    @Override
    public void onClick(View v) {

    }
}
