package com.example.user.selffix;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TrafficReportActivity extends AppCompatActivity {

    Button post_traffic_report_btn;
    Geocoder geocoder;

    double LATITUDE = 37.42233;
    double LONGITUDE = -122.083;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_report);
        geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
        findViewById();

        post_traffic_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    getGeoCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void findViewById() {

        post_traffic_report_btn = (Button) findViewById(R.id.post_traffic_report_btn);
    }

    public void getGeoCode() throws IOException {

        List<Address> addresses;

        addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

        Log.i("address",address);



    }


}
