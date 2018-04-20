package fr.eni.lokacar;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;

import fr.eni.lokacar.BLL.PhotoVehiculeManager;
import fr.eni.lokacar.BO.Location;
import fr.eni.lokacar.BO.PhotoVehicule;

public class DetailEDLActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton floatBtnAddPhoto;
    private FloatingActionButton floatBtnSave;
    private ImageView image;
    private EditText editTextDescription;
    private Intent intent;

    private Location location;
    private static PhotoVehicule photoVehicule;
    private static PhotoVehicule oldPhotoVehicule;
    private Uri file;
    private static String immatLocV;
    private static String namePhoto;

    private final String PATH="/storage/emulated/0/Pictures/photo_vehicule/";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edl);

        floatBtnAddPhoto = findViewById(R.id.floatBtnAddPhoto);
        floatBtnSave = findViewById(R.id.floatBtnSave);
        image = findViewById(R.id.photo);
        editTextDescription = findViewById(R.id.description);

        floatBtnAddPhoto.setTag(0);
        floatBtnSave.setTag(1);

        floatBtnAddPhoto.setOnClickListener(this);
        floatBtnSave.setOnClickListener(this);

        intent = getIntent();
        location = intent.getParcelableExtra("location");


        photoVehicule = intent.getParcelableExtra("photoVehicule");
        photoVehicule.setLocation(location);

        PhotoVehiculeManager photoVehiculeManager = new PhotoVehiculeManager();

        oldPhotoVehicule = photoVehiculeManager.selectByImmatLocDerniere(this,photoVehicule);
        oldPhotoVehicule.setLocation(location);

        if (oldPhotoVehicule.getId() != 0){

            File file = new File(oldPhotoVehicule.getPath());
            System.out.println(file.getAbsolutePath());

            String imagePath = file.getAbsolutePath();             // photoFile is a File class.
            Bitmap myBitmap  = BitmapFactory.decodeFile(imagePath);


            try {
                ExifInterface exif = new ExifInterface(file.getAbsolutePath());
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
                Log.d("EXIF", "Exif: " + orientation);
                Matrix matrix = new Matrix();
                if (orientation == 6) {
                    matrix.postRotate(90);
                }
                else if (orientation == 3) {
                    matrix.postRotate(180);
                }
                else if (orientation == 8) {
                    matrix.postRotate(270);
                }
                myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true); // rotating bitmap
            }
            catch (Exception e) {

            }
            ImageView img = (ImageView) findViewById(R.id.photo);
            img.setImageBitmap(myBitmap);



            /*image.setImageURI(Uri.fromFile(file));*/
            editTextDescription.setText(oldPhotoVehicule.getDescription());
        }


        immatLocV = location.getVehicule().getImmatriculation() + "_" + photoVehicule.getLocalisationVehiculeEnum();



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }

    @Override
    public void onClick(View v) {

        int btnClicked = (int) v.getTag();

        switch (btnClicked) {
            case 0:
                takePicture(v);

                break;
            case 1:
                savePhotoAndDesc();
                break;
        }

    }


    private void savePhotoAndDesc() {

        //Save to DB
        photoVehicule.setDescription(editTextDescription.getText().toString());
        photoVehicule.setLocation(location);
        System.out.println("nom photo dans savePhotoDesc"+namePhoto);
        photoVehicule.setPath(namePhoto);
        photoVehicule.setDerniere(true);

        PhotoVehiculeManager photoVehiculeManager = new PhotoVehiculeManager();

        if(oldPhotoVehicule.getId() != 0){
            photoVehiculeManager.update(this,oldPhotoVehicule);
        }

        photoVehiculeManager.insert(this,photoVehicule);

        this.finish();

    }


    public void takePicture(View view) {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                image.setImageURI(file);
            }
        }
    }


    private static File getOutputMediaFile() {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "photo_vehicule");


        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;

            }
        }

        String timeStamp = String.valueOf(new Date().getTime());

        namePhoto =mediaStorageDir.getPath() + File.separator+ immatLocV+"_" + timeStamp + ".jpg";

        System.out.println("nom photo dans getOutputmedia"+namePhoto);
        return new File(mediaStorageDir.getPath() + File.separator+immatLocV+ "_" + timeStamp + ".jpg");

    }



}
