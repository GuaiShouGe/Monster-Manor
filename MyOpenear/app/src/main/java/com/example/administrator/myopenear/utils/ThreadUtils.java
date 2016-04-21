package com.example.administrator.myopenear.utils;

import android.os.Handler;

import com.example.administrator.myopenear.adapter.BaseRankListAdapter;
import com.example.administrator.myopenear.adapter.Find_List_Adapter;
import com.example.administrator.myopenear.adapter.SavnRecyAdapter;
import com.example.administrator.myopenear.bean.SexNews;

/**
 * Created by Administrator on 2016/3/22.
 */
public class ThreadUtils {

    //唤醒适配器更新数据
    public static void notifyAdapter(Handler handler, final Find_List_Adapter adapter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
    public static void notifyAdapter(Handler handler, final BaseRankListAdapter adapter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
    public static void notifyAdapter(Handler handler, final SavnRecyAdapter adapter) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }








}
