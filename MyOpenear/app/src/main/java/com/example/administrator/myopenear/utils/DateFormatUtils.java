package com.example.administrator.myopenear.utils;

/**
 * Created by Administrator on 2016/3/28.
 */
public class DateFormatUtils {
    /**
     *将 int 秒数 转换为 string 分'秒''
     */
    public static String formatDuration(int duration){
        if(duration/60 <10){
            return "0"+duration/60+"'"+duration%60+"''";
        }else{
            return duration/60+"'"+duration%60+"''";
        }
    }
}
