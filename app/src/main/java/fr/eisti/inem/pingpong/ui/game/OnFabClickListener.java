package fr.eisti.inem.pingpong.ui.game;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this.newGamePlayerSelect);
        builder.setTitle(R.string.choosePlayers);
                //TODO check that
                //.setItems(, new DialogInterface.OnClickListener() {
                  //  public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    //}
                //});



    }
}
