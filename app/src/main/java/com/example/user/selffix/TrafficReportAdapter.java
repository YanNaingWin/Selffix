package com.example.user.selffix;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.selffix.model.TrafficReportSingleData;

import java.util.List;

/**
 * Created by user on 8/19/17.
 */

public class TrafficReportAdapter extends RecyclerView.Adapter<TrafficReportAdapter.MyViewHolder> {

    Context context;

    public TrafficReportAdapter(Context context) {
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.traffic_sigle_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        if (position == 1){

            holder.address.setText("Hletan");
            holder.userName.setText("U Myo Myint");
            holder.conditionImgView.setImageResource(R.drawable.red_circle_shape);
            holder.time.setText("12:00");

        }
        else if (position == 2){


            holder.address.setText("San Chaung");
            holder.userName.setText("U Htoo Htoo");
            holder.conditionImgView.setImageResource(R.drawable.green_circle_shape);
            holder.time.setText("1:30");



        }else {


            holder.address.setText("Latha");
            holder.userName.setText("Daw Hla Hla");
            holder.conditionImgView.setImageResource(R.drawable.yellow_circle_shape);
            holder.time.setText("3:00");


        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView address,userName,time;
        ImageView conditionImgView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            address = (TextView) itemView.findViewById(R.id.address_singleItemView);
            userName = (TextView) itemView.findViewById(R.id.userName_singleItemView);
            time = (TextView) itemView.findViewById(R.id.time_singleItemView);
            conditionImgView = (ImageView) itemView.findViewById(R.id.imageView_singleItemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
