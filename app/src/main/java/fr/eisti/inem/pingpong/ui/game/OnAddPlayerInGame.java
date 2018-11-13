package fr.eisti.inem.pingpong.ui.game;

import android.view.View;

class OnAddPlayerInGame implements View.OnClickListener {
    private MainGameActivity mga;

    public OnAddPlayerInGame(MainGameActivity mainGameActivity) {
        this.mga = mainGameActivity;
    }

    @Override
    public void onClick(View v) {
        this.mga.addPlayerInGame();
    }
}
