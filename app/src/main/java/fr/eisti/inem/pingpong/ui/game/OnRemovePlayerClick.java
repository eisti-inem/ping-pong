package fr.eisti.inem.pingpong.ui.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserNotFoundException;

public class OnRemovePlayerClick implements View.OnLongClickListener {

    private User user;
    private MainGameActivity mga;

    public OnRemovePlayerClick(MainGameActivity mainGameActivity,User user){
        this.user = user;
        this.mga = mainGameActivity;
    }

    @Override
    public boolean onLongClick(View v) {
        this.mga.removePlayer(user);
        return true;
    }
}
