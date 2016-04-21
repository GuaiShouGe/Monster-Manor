package com.example.administrator.myopenear.appapction;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/3/28 0028.
 */
public class Myapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ActiveAndroid.initialize(this);

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();

    }
}
