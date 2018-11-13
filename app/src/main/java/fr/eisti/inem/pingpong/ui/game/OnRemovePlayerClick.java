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
        TextView tv = new TextView(this.mga);
        String popUpText = R.string.removeBegin + this.user.getUserName() + R.string.removeEnd;
        tv.setText(popUpText);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mga);
        builder.setCancelable(true)
                .setView(tv)
                .setPositiveButton(R.string.removeTitle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            mga.getCurrentGame().removePlayerFromGame(user);
                        } catch (UserNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .show();
        return true;
    }
}
