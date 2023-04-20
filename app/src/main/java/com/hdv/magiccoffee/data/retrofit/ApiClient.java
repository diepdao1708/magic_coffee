package com.hdv.magiccoffee.data.retrofit;

import com.hdv.magiccoffee.BuildConfig;
import com.hdv.magiccoffee.data.models.SaveAccount;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit;

    public static Retrofit retrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(chain -> {
                Request newRequest;
                if (SaveAccount.accessToken.isEmpty()) {
                    return chain.proceed(chain.request());
                }
                newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", String.format("Bearer %s", SaveAccount.accessToken))
                        .build();
                return chain.proceed(newRequest);
            }).build();

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
