package fr.eisti.inem.pingpong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.ui.user.OnAddUserListener;
import fr.eisti.inem.pingpong.ui.OnMenuButtonClickListener;
import fr.eisti.inem.pingpong.ui.game.NewGamePlayerSelect;

public class MainActivity extends AppCompatActivity {

    private EngineManager engineManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EngineManager.initialize(this.getApplicationContext());
        this.engineManager = EngineManager.get();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnAddUserListener(this));
        Button newGame = findViewById(R.id.newGameMenuButton);
        newGame.setOnClickListener(new OnMenuButtonClickListener(this,newGame.getId()));
        Button displayPlayers = findViewById(R.id.menuDiplayPlayers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void startNewGame(){
        Intent i = new Intent(MainActivity.this,NewGamePlayerSelect.class);
        MainActivity.this.startActivity(i);

    }

    public void displayPlayerList() {
        List<User> listPlayer = this.engineManager.getUserManager().getAll();
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        for(User user : listPlayer){
            TextView tv = new TextView(this);
            tv.setText(user.getUserName());
            ll.addView(tv);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true)
                .setView(ll)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
