package com.example.administrator.myopenear.utils;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 * 网络通信工具类
 */
public class HttpUtils2 {

    private static final String TAG = "lol";
    private static OkHttpClient client;

    //下载json字符串
    public static String download(String url) {
        client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            Log.w("tag", "错误");
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] downloadImg(String url) {
        client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().bytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.w("tag", "错误");
        }

        return null;
    }

}
