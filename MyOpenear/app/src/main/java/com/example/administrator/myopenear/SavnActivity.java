package com.example.administrator.myopenear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.activeandroid.query.Select;
import com.example.administrator.myopenear.adapter.SavnRecyAdapter;
import com.example.administrator.myopenear.bean.SexNews;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class SavnActivity extends AppCompatActivity {
    private Context context = this;
    private SavnRecyAdapter adapter;
    private  RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savn_layout);

        recyclerView = (RecyclerView) findViewById(R.id.savn_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Select select = new Select();
        List<SexNews>list = select.from(SexNews.class).execute();


        adapter = new SavnRecyAdapter(context,list);
        recyclerView.setAdapter(adapter);
//     Intent intent = getIntent();
//        if ("info".equals(intent.get))

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Select select = new Select();
        List<SexNews>list = select.from(SexNews.class).execute();


        adapter = new SavnRecyAdapter(context,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Select select = new Select();
        List<SexNews>list = select.from(SexNews.class).execute();


        adapter = new SavnRecyAdapter(context,list);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Select select = new Select();
        List<SexNews>list = select.from(SexNews.class).execute();


        adapter = new SavnRecyAdapter(context,list);
        recyclerView.setAdapter(adapter);
    }
}
