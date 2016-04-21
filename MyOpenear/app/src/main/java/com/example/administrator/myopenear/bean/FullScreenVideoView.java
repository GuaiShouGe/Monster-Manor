package com.example.administrator.myopenear.bean;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;



/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class FullScreenVideoView extends VideoView {
    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context,AttributeSet attrs){
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0,widthMeasureSpec);
        int heigth = getDefaultSize(0,heightMeasureSpec);
        setMeasuredDimension(width,heigth);
    }
}
