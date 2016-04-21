package com.example.administrator.myopenear.utils;

import com.example.administrator.myopenear.bean.Fenglei;
import com.example.administrator.myopenear.bean.Find_List_Bean;
import com.example.administrator.myopenear.bean.RankListBean;
import com.google.gson.Gson;


/**
 * Created by Administrator on 2016/3/22.
 * 解析数据工具类
 */
public class ParseUtils {

    private static Gson gson = new Gson();

    public static Fenglei parsecate(String json) {
        Fenglei match = gson.fromJson(json, Fenglei.class);
        return match;
    }
    public static RankListBean parseran(String json) {
        RankListBean match = gson.fromJson(json, RankListBean.class);
        return match;
    }
    public static Find_List_Bean parsefindlist(String json) {
        Find_List_Bean match = gson.fromJson(json, Find_List_Bean.class);
        return match;
    }



}
