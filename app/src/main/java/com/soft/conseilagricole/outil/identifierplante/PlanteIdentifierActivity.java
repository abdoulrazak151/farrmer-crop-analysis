package com.soft.conseilagricole.outil.identifierplante;

//import static com.soft.conseilagricole.ML.TraitementImageKt.runObjectDetector;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//import com.soft.conseilagricole.ML.TraitementImage;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;
import com.soft.conseilagricole.R;
//import com.soft.conseilagricole.TraitementImage;
//import com.soft.conseilagricole.TraitementImage;

import java.io.IOException;
import java.util.List;

public class PlanteIdentifierActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    private static final int PERMISSION_CODE=1000;
    private static final int PERMISSION_CODE_1=1003;
    private static final int IMAGE_CAPTURE_CODE=1001;
    private static final int IMAGE_PICKER_CODE=1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plante_identifier);
        floatingActionButton=findViewById(R.id.new_plante_identifier);
        floatingActionButton.setOnClickListener(click->{
            bottomDialog();
        });
    }

    private void bottomDialog(){
        MaterialCardView prendrePhoto, galleriePhoto;
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_take_photo);
        prendrePhoto=bottomSheetDialog.findViewById(R.id.prendre_photo);
        galleriePhoto=bottomSheetDialog.findViewById(R.id.gallerie_photo);
        prendrePhoto.setOnClickListener(v->{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                    String [] permission={Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, PERMISSION_CODE);
                }else{
                    openCamera();
                }
            }
        });
        galleriePhoto.setOnClickListener(v->{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                openGallery();
//                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
//                    String[] permission=  {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE};
//                    requestPermissions(permission, PERMISSION_CODE_1);
//                }else{
//                    openGallery();
//                }
            }
            });

        bottomSheetDialog.show();

    }
    private void openCamera(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, IMAGE_CAPTURE_CODE);
    }

    private void openGallery(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, new String()), IMAGE_PICKER_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE_CAPTURE_CODE){
            Uri uri =data.getData();
            Toast.makeText(this, "image bon", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                TraitementImage traitementImage=new TraitementImage();

//                        String a= traitementImage.runObjectDetector(bitmap);
                Toast.makeText(this, "machine en cours", Toast.LENGTH_SHORT).show();
                InputImage image=InputImage.fromBitmap(bitmap,0);
//                ObjectDetectorOptions objectDetectorOptions=new ObjectDetectorOptions();
                ObjectDetectorOptionsBase options= new ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableClassification()
                        .enableMultipleObjects()
                        .build();
                        ObjectDetector objectDetector=ObjectDetection.getClient(options);
                        objectDetector.process(image)
                                .addOnSuccessListener(new OnSuccessListener<List<DetectedObject>>() {
                                    @RequiresApi(api = Build.VERSION_CODES.N)
                                    @Override
                                    public void onSuccess(List<DetectedObject> detectedObjects) {
                                       detectedObjects.stream().forEach(e->{
                                           Toast.makeText(PlanteIdentifierActivity.this, String.valueOf(e.getTrackingId()), Toast.LENGTH_SHORT).show();
                                       });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICKER_CODE){
            Uri uri=data.getData();
            Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                TraitementImage traitementImage=new TraitementImage();

//                        String a= traitementImage.runObjectDetector(bitmap);
                Toast.makeText(this, "machine en cours", Toast.LENGTH_SHORT).show();
                InputImage image=InputImage.fromBitmap(bitmap,0);
//                ObjectDetectorOptions objectDetectorOptions=new ObjectDetectorOptions();
                ObjectDetectorOptionsBase options= new ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableClassification()
                        .enableMultipleObjects()
                        .build();
                ObjectDetector objectDetector=ObjectDetection.getClient(options);
                objectDetector.process(image)
                        .addOnSuccessListener(new OnSuccessListener<List<DetectedObject>>() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onSuccess(List<DetectedObject> detectedObjects) {
                                detectedObjects.stream().forEach(e->{
                                    Toast.makeText(PlanteIdentifierActivity.this, String.valueOf(e.getBoundingBox().toString()), Toast.LENGTH_SHORT).show();
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                else {
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                break;
            }
            case PERMISSION_CODE_1:{
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openGallery();
                }else{
                    requestPermissions(permissions, PERMISSION_CODE_1);
                }
                break;
            }

        }
    }
}