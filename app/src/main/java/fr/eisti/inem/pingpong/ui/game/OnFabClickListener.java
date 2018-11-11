package fr.eisti.inem.pingpong.ui.game;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import fr.eisti.inem.pingpong.R;

/**
 * This listener will be linked to the fab button
 * Show a pop up with to button to add players from scratch or from database
 */

public class OnFabClickListener implements View.OnClickListener {

    private NewGamePlayerSelect newGamePlayerSelect;
    private List<Integer> whereFrom = new ArrayList<Integer>();

    public OnFabClickListener(NewGamePlayerSelect newGamePlayerSelect){
        this.newGamePlayerSelect = newGamePlayerSelect;
        whereFrom.add(R.string.fromScratch);
        whereFrom.add(R.string.fromDataBase);
    }

    @Override
    public void onClick(View v) {
        //LinearLayout inside the popUp, wich gives access to the ad player methods
        LinearLayout buttonLayout = new LinearLayout(this.newGamePlayerSelect);
        buttonLayout.setOrientation(LinearLayout.VERTICAL);
        //Button to create a new player and add it
        Button fromScratchAdd = new Button(this.newGamePlayerSelect);
        fromScratchAdd.setText(R.string.addPlayerFromScratch);
        //Button to add a player from the database
        Button fromDataBase = new Button(this.newGamePlayerSelect);
        fromDataBase.setText(R.string.addPlayerFromDataBase);
        //Put view inside each other
        buttonLayout.addView(fromScratchAdd);
        buttonLayout.addView(fromScratchAdd);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.newGamePlayerSelect);
        builder.setCancelable(false)
                .setTitle(R.string.choosePlayers)
                .setView(buttonLayout)
                .setPositiveButton(R.string.validatePlayerAdd, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //It just closes the window
                    }
                })
                .show();
                //TODO check that
                //.setItems(, new DialogInterface.OnClickListener() {
                  //  public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    //}
                //});

    }
}
