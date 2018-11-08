package fr.eisti.inem.pingpong;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import fr.eisti.inem.pingpong.engine.EngineManager;
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
}
