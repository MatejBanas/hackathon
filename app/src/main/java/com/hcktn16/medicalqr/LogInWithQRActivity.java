package com.hcktn16.medicalqr;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import com.camerakit.CameraKitView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import android.net.Uri;
import android.widget.ImageView;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LogInWithQRActivity extends AppCompatActivity {
    private CameraKitView cameraKitView;
    private Button photoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwithqr);
        cameraKitView = findViewById(R.id.camera);

        photoButton = findViewById(R.id.photoButton);
        photoButton.setOnClickListener(photoOnClickListener);

    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    // From button OnClickListener
    private View.OnClickListener photoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                    File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
                    FirebaseVisionImage image;
                    try {
                        Uri uri = Uri.parse(savedPhoto.getAbsolutePath());

                        image = FirebaseVisionImage.fromFilePath( getApplicationContext(), uri);
                        Log.d("PIZDA", image.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                            .getVisionBarcodeDetector();

                    Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                            .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                                @Override
                                public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                                    // Task completed successfully
                                    // [START_EXCLUDE]
                                    // [START get_barcodes]
                                    for (FirebaseVisionBarcode barcode: barcodes) {
                                        Rect bounds = barcode.getBoundingBox();
                                        Point[] corners = barcode.getCornerPoints();

                                        String rawValue = barcode.getRawValue();
                                        int valueType = barcode.getValueType();
                                        Log.d("KOKOTPICA", rawValue);
                                        // See API reference for complete list of supported types
                                        switch (valueType) {
                                            case FirebaseVisionBarcode.TYPE_WIFI:
                                                String ssid = barcode.getWifi().getSsid();
                                                String password = barcode.getWifi().getPassword();
                                                int type = barcode.getWifi().getEncryptionType();
                                                break;
                                            case FirebaseVisionBarcode.TYPE_TEXT:
                                                String title = barcode.getUrl().getTitle();
                                                String url = barcode.getUrl().getUrl();
                                                break;
                                        }
                                    }
                                    // [END get_barcodes]
                                    // [END_EXCLUDE]
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Task failed with an exception
                                    // ...
                                }
                            });
                    // [END run_detector]



                    /*
                    try {
                        FileOutputStream outputStream = new FileOutputStream(savedPhoto.getPath());
                        outputStream.write(capturedImage);
                        outputStream.close();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                        Log.e("medicalqr", "KODSAKDOAS");
                    }
                    */


                    //Log.d("KOKOTPICA", );
                }

            });
        }

    };


}
