package com.example.user.selffix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 8/19/17.
 */
public class TrafficReportData {

    @SerializedName("data")
    @Expose
    private List<TrafficReportSingleData> data = null;

    public List<TrafficReportSingleData> getData() {
        return data;
    }

    public void setData(List<TrafficReportSingleData> data) {
        this.data = data;

    }



}
