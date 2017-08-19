package com.example.user.selffix.RestfullApi;

import com.example.user.selffix.model.CarReportResponse;
import com.example.user.selffix.model.TrafficReportReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by kyawthetwin on 8/19/17.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("upload/report")
    Call<CarReportResponse> uploadCarReport(@Field("param_data")String paramData);


    @FormUrlEncoded
    @POST("upload/traffic")
    Call<TrafficReportReponse> uploadTrafficReport(@Field("param_data")String paramData);
}
