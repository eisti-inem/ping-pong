package fr.eisti.inem.pingpong.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * if (checkSelfPermission(Manifest.permission.CAMERA)
 != PackageManager.PERMISSION_GRANTED) {
 requestPermissions(new String[]{Manifest.permission.CAMERA},
 MY_CAMERA_PERMISSION_CODE);
 } else {
 Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
 startActivityForResult(cameraIntent, CAMERA_REQUEST);
 }
 */

public class TakeProfilePicture extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
