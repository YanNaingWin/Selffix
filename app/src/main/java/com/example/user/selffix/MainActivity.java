package com.example.user.selffix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BoomMenuButton bmb;
    ArrayList<Integer> namelist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        setBmb();

    }

    public void setBmb() {

        namelist.add(R.string.name1);
        namelist.add(R.string.name2);
        namelist.add(R.string.name3);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);

        assert bmb != null;


        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_2);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_2);


        for (int i = 0; i < 3; i++) {
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
                    intent = new Intent(MainActivity.this, RuleBreakerReportActivity.class);
                } else if (index == 1) {
                    intent = new Intent(MainActivity.this, TrafficReportActivity.class);
                } else if (index == 2) {
                    intent = new Intent(MainActivity.this, RoadReportActivity.class);
                }
                MainActivity.this.startActivity(intent);
            }
        })
                .normalTextRes(name);
    }

}
