package fr.eisti.inem.pingpong.ui.game;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.user.InvalidUserException;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserManager;

public class OnAddFromScratchListener implements View.OnClickListener {

    private final EngineManager engineManager;
    private UserManager userManager;
    private NewGamePlayerSelect ngps;


    public OnAddFromScratchListener(NewGamePlayerSelect newGamePlayerSelect){
        this.ngps = newGamePlayerSelect;
        this.engineManager = EngineManager.get();
        this.userManager = this.engineManager.getUserManager();
    }

    @Override
    public void onClick(View v) {
        final TextView titlePseudo  = new TextView(this.ngps);
        final EditText inputPseudo  = new EditText(this.ngps);
        final TextView titleName    = new TextView(this.ngps);
        final EditText inputName    = new EditText(this.ngps);
        final TextView titleSurname = new TextView(this.ngps);
        final EditText inputSurname = new EditText(this.ngps);
        final LinearLayout ll       = new LinearLayout(this.ngps);


        inputPseudo.setInputType(InputType.TYPE_CLASS_TEXT);
        titlePseudo.setText(R.string.titlePseudo);
        inputName.setInputType(InputType.TYPE_CLASS_TEXT);
        titleName.setText(R.string.titleName);
        inputSurname.setInputType(InputType.TYPE_CLASS_TEXT);
        titleSurname.setText(R.string.titleSurname);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(titlePseudo);
        ll.addView(inputPseudo);
        ll.addView(titleName);
        ll.addView(inputName);
        ll.addView(titleSurname);
        ll.addView(inputSurname);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ngps);
        builder.setCancelable(true)
                .setTitle(R.string.newUser)
                .setView(ll)
                .setPositiveButton(R.string.ValidateUserAdd, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = new User(inputPseudo.getText().toString(),
                                new String(),
                                inputName.getText().toString(),
                                inputSurname.getText().toString());
                        try {
                            userManager.add(user);
                            ngps.addUser(user);
                        } catch (InvalidUserException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .show();

    }
}
