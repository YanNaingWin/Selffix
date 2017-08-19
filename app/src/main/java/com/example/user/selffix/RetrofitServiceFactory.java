package com.example.user.selffix;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by macmininew on 10/4/16.
 */
public class RetrofitServiceFactory {

    public static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <T> T createService(Class<T> serviceClass) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(loggingInterceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request origial = chain.request();
                Request.Builder requstbuilder = origial.newBuilder();

                Request request = requstbuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

}
