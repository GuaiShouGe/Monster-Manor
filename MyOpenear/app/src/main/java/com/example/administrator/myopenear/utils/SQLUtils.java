package com.example.administrator.myopenear.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/31 0031.
 */
public class SQLUtils extends SQLiteOpenHelper {
    private static final String DBNAME="news.db";
    private static final int VERSION=3;  //设置版本号
    private static final String TBL_DETAILNEWS="news";   //创建表名为news的表
    private static final String TBL_DETAILNEWS_COLUMN_TITLE="_title";
        private static final String TBL_DETAILNEWS_COLUMN_URL="_url";
      private static final String TBL_DETAILNEWS_COLUMN_DOCID="_docid";
      private static final String TBL_DETAILNEWS_COLUMN_STATE="_state";



    public SQLUtils(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
