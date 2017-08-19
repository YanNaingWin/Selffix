package com.example.user.selffix.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kyawthetwin on 8/19/17.
 */

public class CarReportUpload {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("date")
    private String date;
    @SerializedName("problem")
    @Expose
    private String problem;

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("condition")
    @Expose
    private Integer condition;


    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

}
