package com.example.user.selffix;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.selffix.RestfullApi.ApiService;
import com.example.user.selffix.model.TrafficReportData;
import com.example.user.selffix.model.TrafficReportReponse;
import com.example.user.selffix.model.TrafficReportSingleData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrafficReportActivity extends AppCompatActivity {

    Button post_traffic_report_btn;
    Geocoder geocoder;

    double LATITUDE = 37.42233;
    double LONGITUDE = -122.083;
    String param_Data;
    String current_Date;
    String currentTime;
    Calendar c;
    String Ac_adressText;
    ImageView red_btn;
    Toolbar traffic_report_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_report);
        geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
        findViewById();


        setUpDefaultToolBar(traffic_report_toolbar);

        c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        currentTime = format.format( c.getTime());


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        current_Date = df.format(c.getTime());


        red_btn = (ImageView) findViewById(R.id.red_btn);
        red_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                red_btn.setImageResource(R.drawable.selected);
            }
        });
        post_traffic_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    getGeoCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                uploadTrafficReport();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TrafficReportActivity.this);

                // set title
                alertDialogBuilder.setTitle("တိုင္ၾကားမွဳေအာင္ျမင္ပါသည္");

                // set dialog message
                alertDialogBuilder
                        .setMessage(R.string.thankyou)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(TrafficReportActivity.this,MainActivityFake.class);
                                startActivity(i);
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }

        });
    }

    private void findViewById() {

        post_traffic_report_btn = (Button) findViewById(R.id.post_traffic_report_btn);
        traffic_report_toolbar = (Toolbar) findViewById(R.id.traffic_report_toolbar);
    }
    protected void setUpDefaultToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolBar.setNavigationIcon(R.drawable.left_arrow_white);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void getGeoCode() throws IOException {

        List<Address> addresses;

        addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        Ac_adressText = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()




    }

    public void uploadTrafficReport() {

        TrafficReportSingleData TrafficReportUpload = new TrafficReportSingleData();
        List<TrafficReportSingleData> trafficReportSingleDataList=new ArrayList<>();
        TrafficReportUpload.setDate(current_Date);
        TrafficReportUpload.setUserId(1);
        TrafficReportUpload.setTime(currentTime);
        TrafficReportUpload.setAddress(Ac_adressText);
        TrafficReportUpload.setCondition(1);

        trafficReportSingleDataList.add(TrafficReportUpload);


        TrafficReportData trafficReportData = new TrafficReportData();

        trafficReportData.setData(trafficReportSingleDataList);
        param_Data = getJsonFromObject(trafficReportData);

        ApiService apiService=RetrofitServiceFactory.createService(ApiService.class);
        Call<TrafficReportReponse> call=apiService.uploadTrafficReport(param_Data);

        call.enqueue(new Callback<TrafficReportReponse>() {
            @Override
            public void onResponse(Call<TrafficReportReponse> call, Response<TrafficReportReponse> response) {
                if (response.code()==200){

                    if (response.body().getStatusCode()==200){

//                        String toast=response.body().getStatusMessage();
//                        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<TrafficReportReponse> call, Throwable t) {

            }
        });

    }
    private String getJsonFromObject(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = gson.toJson(object);
        return jsonString;
    }


}
