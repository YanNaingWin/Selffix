package com.example.user.selffix;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_report);
        geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
        findViewById();

        c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        currentTime = format.format( c.getTime());


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        current_Date = df.format(c.getTime());


        post_traffic_report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    getGeoCode();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                uploadTrafficReport();

            }

        });
    }

    private void findViewById() {

        post_traffic_report_btn = (Button) findViewById(R.id.post_traffic_report_btn);
    }

    public void getGeoCode() throws IOException {

        List<Address> addresses;

        addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        Ac_adressText = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        Toast.makeText(getApplicationContext(),Ac_adressText,Toast.LENGTH_SHORT).show();



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

                        String toast=response.body().getStatusMessage();
                        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<TrafficReportReponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String getJsonFromObject(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = gson.toJson(object);
        return jsonString;
    }


}
