package com.example.user.selffix;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

import static com.nightonke.boommenu.ButtonEnum.Ham;

public class MainActivityFake extends AppCompatActivity {

    BoomMenuButton bmb;

    ArrayList<Integer> namelist = new ArrayList<>();

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fake);

        bmb = (BoomMenuButton) findViewById(R.id.bmb_fake);
        setBmb();

        recyclerView = (RecyclerView) findViewById(R.id.traffic_recyclerView_fake);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getApplicationContext());
        linearlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        TrafficReportAdapterFake carReportListAdpter = new TrafficReportAdapterFake(getApplicationContext());
        carReportListAdpter.notifyDataSetChanged();
        recyclerView.setAdapter(carReportListAdpter);
        recyclerView.setLayoutManager(linearlayoutmanager);
        recyclerView.setHasFixedSize(true);

    }

    public void setBmb() {



        namelist.add(R.string.name1);
        namelist.add(R.string.name2);

        bmb = (BoomMenuButton) findViewById(R.id.bmb_fake);

        assert bmb != null;


        bmb.setButtonEnum(Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_2);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_2_2);
        bmb.setNormalColor(Color.parseColor("#16578e"));
        bmb.setDimColor(Color.parseColor("#16578e"));


        for (int i = 0; i < 2; i++) {
            bmb.addBuilder(getHamButtonBuilderWithDifferentPieceColor(namelist.get(i)));
        }

    }

    HamButton.Builder getHamButtonBuilderWithDifferentPieceColor(int name) {
        return new HamButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {

                Intent intent = null;
                Log.i("index", index + "");
                if (index == 0) {
                    intent = new Intent(MainActivityFake.this, RuleBreakerReportActivity.class);
                } else if (index == 1) {
                    intent = new Intent(MainActivityFake.this, TrafficReportActivity.class);
                }
                MainActivityFake.this.startActivity(intent);
            }
        })
                .normalColor(Color.WHITE)
                .normalTextRes(name)
                .normalTextColor(Color.parseColor("#16578e"));
    }
/*
    public void getTrafficReport() {
*//*
        apiInterface = ApiClient.getClient(Constant.BASE_URL).create(ApiClient.class);
        recyclerView = (RecyclerView) findViewById(R.id.traffic_recyclerView);
        Call<TrafficReportData> call ;
        call = apiInterface.getClient(Constant.BASE_URL);
        call.enqueue(new Callback<CarReportData>() {
            @Override
            public void onResponse(Call<CarReportData> call, Response<CarReportData> response) {

                carReportLists = response.body().getData();
                LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getApplicationContext());
                linearlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
                CarReportListAdpter carReportListAdpter = new CarReportListAdpter(getApplicationContext(),carReportLists);
                carReportListAdpter.notifyDataSetChanged();
                recyclerView.setAdapter(carReportListAdpter);
                recyclerView.setLayoutManager(linearlayoutmanager);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<CarReportData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    *//*

    }*/

}