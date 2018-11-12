package fr.eisti.inem.pingpong.ui;

import android.view.View;

import fr.eisti.inem.pingpong.MainActivity;

public class OnGetPlayerListListener implements View.OnClickListener {
    private MainActivity mainActivity;

    public OnGetPlayerListListener(MainActivity ma){
        this.mainActivity = ma;
    }

    @Override
    public void onClick(View v) {
        this.mainActivity.displayPlayerList();
    }
}
