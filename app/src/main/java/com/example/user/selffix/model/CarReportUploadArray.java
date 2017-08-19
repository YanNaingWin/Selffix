package com.example.user.selffix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kyawthetwin on 8/19/17.
 */

public class CarReportUploadArray {

    @SerializedName("data")
    @Expose
    private List<CarReportUpload> data = null;

    public List<CarReportUpload> getData() {
        return data;
    }

    public void setData(List<CarReportUpload> data) {
        this.data = data;
    }

}