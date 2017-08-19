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
    private List<TrafficReportSingleData> carReportSingleDataList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.traffic_sigle_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

      /*  holder.userName.setText(carReportSingleDataList.get(position).getUsername());*/
        holder.time.setText(carReportSingleDataList.get(position).getTime());

        int condition = carReportSingleDataList.get(position).getCondition();

        if (condition == 1){

        }
        else if (condition == 2){


        }else {


        }
    }

    @Override
    public int getItemCount() {
        return 7;
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
