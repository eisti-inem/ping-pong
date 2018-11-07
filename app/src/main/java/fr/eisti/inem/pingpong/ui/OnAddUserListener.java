package fr.eisti.inem.pingpong.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import fr.eisti.inem.pingpong.MainActivity;
import fr.eisti.inem.pingpong.R;
import fr.eisti.inem.pingpong.engine.EngineManager;
import fr.eisti.inem.pingpong.engine.user.InvalidUserException;
import fr.eisti.inem.pingpong.engine.user.User;
import fr.eisti.inem.pingpong.engine.user.UserManager;

public class OnAddUserListener implements View.OnClickListener {

    private MainActivity ma;
    private EngineManager engineManager;
    private UserManager userManager;

    public OnAddUserListener(MainActivity m){
        this.ma = m;
        this.engineManager = EngineManager.get();
        this.userManager = this.engineManager.getUserManager();
    }

    @Override
    public void onClick(View v) {
        final EditText inputPseudo  = new EditText(this.ma);
        final EditText inputName    = new EditText(this.ma);
        final EditText inputSurname = new EditText(this.ma);
        final LinearLayout ll       = new LinearLayout(this.ma);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(inputPseudo);
        ll.addView(inputName);
        ll.addView(inputSurname);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ma);
        builder.setCancelable(false)
                .setTitle(R.string.newUser)
                .setView(ll)
                .setPositiveButton(R.string.ValidateUserAdd, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = new User(inputPseudo.getText().toString(),
                                null,
                                inputName.getText().toString(),
                                inputSurname.getText().toString());
                        try {
                            userManager.add(user);
                        } catch (InvalidUserException e) {
                            e.printStackTrace();
                        }
                    }
                });

        //TODO import picture
    }
}
