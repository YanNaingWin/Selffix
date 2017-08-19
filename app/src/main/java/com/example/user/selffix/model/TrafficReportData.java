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
    private List<TrafficReportSingleData> carReportSingleDataList = null;

    public List<TrafficReportSingleData> getData() {
        return carReportSingleDataList;
    }

    public void setData(List<TrafficReportSingleData> carReportSingleDataList) {
        this.carReportSingleDataList = carReportSingleDataList;
    }

}
