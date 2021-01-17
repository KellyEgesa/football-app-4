package com.moringaschool.football_app.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.moringaschool.football_app.Constants.FOOTBALL_AUTH_TOKEN;
import static com.moringaschool.football_app.Constants.FOOTBALL_BASE_URL;

public class FootBallClient {
    public static Retrofit retrofit = null;
    public static FootBallApi urlRequest(){
        if(retrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("X-Auth-Token", FOOTBALL_AUTH_TOKEN).build();
                    return chain.proceed(request);
                }
            }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(FOOTBALL_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(FootBallApi.class);
    }
}
