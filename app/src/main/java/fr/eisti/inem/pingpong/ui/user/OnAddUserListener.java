package fr.eisti.inem.pingpong.ui.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        final TextView titlePseudo  = new TextView(this.ma);
        final EditText inputPseudo  = new EditText(this.ma);
        final TextView titleName  = new TextView(this.ma);
        final EditText inputName    = new EditText(this.ma);
        final TextView titleSurname  = new TextView(this.ma);
        final EditText inputSurname = new EditText(this.ma);
        final LinearLayout ll       = new LinearLayout(this.ma);


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

        AlertDialog.Builder builder = new AlertDialog.Builder(this.ma);
        builder.setCancelable(false)
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
                        } catch (InvalidUserException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .show();

        //TODO import picture
    }
}
