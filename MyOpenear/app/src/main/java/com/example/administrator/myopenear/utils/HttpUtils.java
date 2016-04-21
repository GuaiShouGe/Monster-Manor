package com.example.administrator.myopenear.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2016/3/22.
 */
public class HttpUtils {
    private static final OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private static  OkHttpClient okHttpClient =null;
    public static boolean isWIFI = true;
    static {
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30,TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }

    //不开启异步线程
    public static Response execute(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    //开启异步线程
    public static void enqueue(Request request, Callback responseCallback){
        okHttpClient.newCall(request).enqueue(responseCallback);
    }

    //异步访问网络，不在意返回结果
    public static void enqueue(Request request){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
