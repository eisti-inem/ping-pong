package fr.eisti.inem.pingpong.ui.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.EngineManager;

public class OnAddFromDataBase implements View.OnClickListener {
    private NewGamePlayerSelect ngps;
    private List<User> availablePlayers;
    private Map<Integer,User> fromRbToUser = new HashMap<>();

    public OnAddFromDataBase(NewGamePlayerSelect ngps){
        this.ngps = ngps;
        this.availablePlayers = EngineManager.get().getUserManager().getAll();
    }

    @Override
    public void onClick(View v) {
        //Create a radio group to choose a player
        final RadioGroup listPlayers = new RadioGroup(this.ngps);

        for(User player : this.availablePlayers){
            RadioButton rb = new RadioButton(this.ngps);

            if (!player.getFirstName().isEmpty() && !player.getLastName().isEmpty()) {
                rb.setText(String.format("%s - %s %s",
                        player.getUserName(),
                        player.getFirstName(),
                        player.getLastName().toUpperCase()));
            } else {
                rb.setText(player.getUserName());
            }

            listPlayers.addView(rb);
            fromRbToUser.put(rb.getId(),player);

        }
        //Display this radioGroup in a popUp
        AlertDialog.Builder builder = new AlertDialog.Builder(this.ngps);
        builder.setCancelable(true)
                .setView(listPlayers)
                .setTitle(R.string.chooseFromDatabase)
                .setPositiveButton(R.string.addPlayer, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer addingPlayer = listPlayers.getCheckedRadioButtonId();
                        ngps.addUser(fromRbToUser.get(addingPlayer));
                    }
                })
                .show();
    }
}
