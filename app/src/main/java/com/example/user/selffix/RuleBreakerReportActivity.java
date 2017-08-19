package com.example.user.selffix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

public class RuleBreakerReportActivity extends AppCompatActivity {

    ImageView imgV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_breaker_report);

        imgV = (ImageView) findViewById(R.id.imageView);
        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCamera();
            }
        });
    }

    private void OpenCamera() {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setPreviewEnabled(false)
                .setShowGif(true)
                .start(this, PhotoPicker.REQUEST_CODE);
        Log.i("ok", "Ok");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            Toast.makeText(this, "REQUEST_CODE", Toast.LENGTH_SHORT).show();
            if (data != null) {
                Toast.makeText(this, "data", Toast.LENGTH_SHORT).show();
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                String userImageString = BitmapUtility.newInstance().convetStringFromBitmap(photos.get(0));
                imageView.setImageBitmap(BitmapUtility.newInstance().convertBitmapFromString(userImageString));
                // Bitmap bm = BitmapUtility.newInstance().convertBitmapFromString(userImageString);

            }
        }
    }
}
