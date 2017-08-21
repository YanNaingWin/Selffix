package com.example.user.selffix;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.selffix.RestfullApi.ApiService;
import com.example.user.selffix.model.CarReportResponse;
import com.example.user.selffix.model.CarReportUpload;
import com.example.user.selffix.model.CarReportUploadArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuleBreakerReportActivity extends AppCompatActivity {

    TextView date_text;
    ImageView imgV;
    String userImageString;
    TextView time_for_carreport;
    EditText problem;
    String problemText;
    EditText Ac_Adress;
    String Ac_AdressText;
    String currentTime;
    Calendar c;
    RadioGroup problem_rbtn;
    int problem_btn_condition;
    RadioButton radioSexButton;
    int selectedId;
    String Problem_condition;
    String current_Date;
    String param_Data;
    Toolbar rule_report_toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_breaker_report);
        rule_report_toolbar = (Toolbar) findViewById(R.id.rule_report_toolbar);
        setUpDefaultToolBar(rule_report_toolbar);
        problem = (EditText) findViewById(R.id.problem);
        problem.setVisibility(View.GONE);
        problem_rbtn = (RadioGroup) findViewById(R.id.problem_rbtn);
        date_text = (TextView) findViewById(R.id.date_text);



        Ac_Adress = (EditText) findViewById(R.id.address);

        time_for_carreport = (TextView) findViewById(R.id.time_for_carreport);
        problem = (EditText) findViewById(R.id.problem);
        problemText = String.valueOf(problem.getText());
        c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        currentTime = format.format(c.getTime());


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        current_Date = df.format(c.getTime());
        date_text.setText(current_Date);

        time_for_carreport.setText(currentTime + "");

        imgV = (ImageView) findViewById(R.id.imageView);
        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCamera();
            }
        });


        findViewById(R.id.report_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Ac_AdressText = String.valueOf(Ac_Adress.getText());
                addRadioButton();
                uploadCarReport();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RuleBreakerReportActivity.this);

                // set title
                alertDialogBuilder.setTitle("တိုင္ၾကားမွဳေအာင္ျမင္ပါသည္");

                // set dialog message
                alertDialogBuilder
                        .setMessage(R.string.thankyou)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                RuleBreakerReportActivity.this.finish();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
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
                userImageString = BitmapUtility.newInstance().convetStringFromBitmap(photos.get(0));
                imageView.setImageBitmap(BitmapUtility.newInstance().convertBitmapFromString(userImageString));
                // Bitmap bm = BitmapUtility.newInstance().convertBitmapFromString(userImageString);

            }
        }
    }

    public void addRadioButton() {

        if (problem_rbtn.getCheckedRadioButtonId() != -1) {
            int id = problem_rbtn.getCheckedRadioButtonId();
            View radioButton = problem_rbtn.findViewById(id);
            int radioId = problem_rbtn.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) problem_rbtn.getChildAt(radioId);
            Problem_condition = String.valueOf(btn.getText());
        }

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

    public void uploadCarReport() {

        CarReportUpload carReportUpload = new CarReportUpload();
        List<CarReportUpload> carReportUploadList = new ArrayList<>();
        carReportUpload.setImg(userImageString);
        carReportUpload.setAddress(Ac_AdressText);
        carReportUpload.setTime(String.valueOf(currentTime));
        carReportUpload.setUserId(1);
        carReportUpload.setCondition(problem_btn_condition);
        carReportUpload.setProblem(Problem_condition);
        carReportUpload.setDate(current_Date);
        carReportUploadList.add(carReportUpload);

        CarReportUploadArray carReportUploadArray = new CarReportUploadArray();

        carReportUploadArray.setData(carReportUploadList);
        param_Data = getJsonFromObject(carReportUploadArray);

        ApiService apiService = RetrofitServiceFactory.createService(ApiService.class);
        Call<CarReportResponse> call = apiService.uploadCarReport(param_Data);

        call.enqueue(new Callback<CarReportResponse>() {
            @Override
            public void onResponse(Call<CarReportResponse> call, Response<CarReportResponse> response) {
                if (response.code() == 200) {

                    if (response.body().getStatusCode() == 200) {

                        String toast = response.body().getStatusMessage();
                        Toast.makeText(RuleBreakerReportActivity.this, toast, Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<CarReportResponse> call, Throwable t) {

            }
        });

    }

    private String getJsonFromObject(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = gson.toJson(object);
        return jsonString;
    }


}
