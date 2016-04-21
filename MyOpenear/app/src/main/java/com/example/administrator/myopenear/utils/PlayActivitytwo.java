package com.example.administrator.myopenear.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.administrator.myopenear.R;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class PlayActivitytwo extends AppCompatActivity {

    private VideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout_two);

        Intent intent = getIntent();
        Bundle bundle =  intent.getExtras();

        String url = bundle.getString("url");

        videoview = ((VideoView) findViewById(R.id.videoview2));

        videoview.setVideoURI(Uri.parse(url));
        videoview.setMediaController(new MediaController(this));

        videoview.requestFocus();
        videoview.start();
    }
}
