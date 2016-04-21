package com.example.administrator.myopenear.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.DetailPagerAdapter;
import com.example.administrator.myopenear.bean.DailyBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class DetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageView img_buf_one;
    private ImageView img_buf_two;
    private ImageView img_buf_three;
    private ImageView img_buf_four;
    private ImageView img_buf_five;

    private TextView text_title_one;
    private TextView text_title_two;
    private TextView text_title_three;
    private TextView text_title_four;
    private TextView text_title_five;

    private TextView text_summary_one;
    private TextView text_summary_two;
    private TextView text_summary_three;
    private TextView text_summary_four;
    private TextView text_summary_five;

    private TextView text_detail_one;
    private TextView text_detail_two;
    private TextView text_detail_three;
    private TextView text_detail_four;
    private TextView text_detail_five;

    private View detail_view_one;
    private View detail_view_two;
    private View detail_view_three;
    private View detail_view_four;
    private View detail_view_five;

    private List<View> list_img = new ArrayList<View>();
    private DailyBean.IssueListEntity issueListEntity;

    private DetailPagerAdapter adapter;

    private int defaultIndex;
    private int parentIndex = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_activity);

        Intent intent = getIntent();

        issueListEntity = intent.getParcelableExtra("issueList");
        defaultIndex = intent.getExtras().getInt("index");
        if(issueListEntity == null){
            Log.e("tag","666666666");
        }

        if(issueListEntity.getItemList().get(0).getData().getCover() == null){
            Log.e("tag","777777777");
        }

        if(!issueListEntity.getItemList().get(0).getType().equals("video")){
            parentIndex = 1;
            defaultIndex = defaultIndex -1;
            for(int i = 1;i<6;i++){
                View view =  LayoutInflater.from(this).inflate(R.layout.layout_detail_iamgeview,null);
                ImageView imageView = (ImageView) view.findViewById(R.id.detail_imageview);

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(i).getData().getCover().getDetail()))
                        .into(imageView);
                final int finalI = i;

               view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("tag",12345678+"");
                        Intent intent = new Intent(DetailActivity.this, PlayActivitytwo.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("playUrl",issueListEntity.getItemList().get(finalI).getData().getPlayUrl());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                list_img.add(view);
            }

        }else{

            for(int i = 0;i<5;i++){
                View view =  LayoutInflater.from(this).inflate(R.layout.layout_detail_iamgeview,null);
                ImageView imageView = (ImageView) view.findViewById(R.id.detail_imageview);

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(i).getData().getCover().getDetail()))
                        .into(imageView);

                final int finalI = i;
               view.setOnClickListener(new View.OnClickListener() {
                    @Override
                   public void onClick(View v) {
                        Log.e("tag",12345678+"");
                        Intent intent = new Intent(DetailActivity.this, PlayActivitytwo.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("url",issueListEntity.getItemList().get(finalI).getData().getPlayUrl());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                list_img.add(view);
            }
        }



        /*for(DailyBean.IssueListEntity.ItemListEntity item: issueListEntity.getItemList()){
            View view =  LayoutInflater.from(this).inflate(R.layout.layout_detail_iamgeview,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.detail_imageview);
            if(item.getData().getProvider()== null){
                Log.e("tag","55555555");
            }
            Picasso.with(this)
                    .load(Uri.parse(item.getData().getCover().getDetail()))
                    .into(imageView);
            list_img.add(imageView);
        }*/



        viewPager = (ViewPager) findViewById(R.id.viewpager_detail);
        img_buf_one = (ImageView) findViewById(R.id.imageview_buffer_one);
        img_buf_two = (ImageView) findViewById(R.id.imageview_buffer_two);
        img_buf_three = (ImageView) findViewById(R.id.imageview_buffer_three);
        img_buf_four = (ImageView) findViewById(R.id.imageview_buffer_four);
        img_buf_five = (ImageView) findViewById(R.id.imageview_buffer_five);

        text_title_one = (TextView) findViewById(R.id.detail_title_one);
        text_title_two = (TextView) findViewById(R.id.detail_title_two);
        text_title_three = (TextView) findViewById(R.id.detail_title_three);
        text_title_four = (TextView) findViewById(R.id.detail_title_four);
        text_title_five = (TextView) findViewById(R.id.detail_title_five);

        text_summary_one = (TextView) findViewById(R.id.detail_summary_one);
        text_summary_two = (TextView) findViewById(R.id.detail_summary_two);
        text_summary_three = (TextView) findViewById(R.id.detail_summary_three);
        text_summary_four = (TextView) findViewById(R.id.detail_summary_four);
        text_summary_five = (TextView) findViewById(R.id.detail_summary_five);

        text_detail_one = (TextView) findViewById(R.id.detail_detail_one);
        text_detail_two = (TextView) findViewById(R.id.detail_detail_two);
        text_detail_three = (TextView) findViewById(R.id.detail_detail_three);
        text_detail_four = (TextView) findViewById(R.id.detail_detail_four);
        text_detail_five = (TextView) findViewById(R.id.detail_detail_five);

        detail_view_one = findViewById(R.id.detail_one);
        detail_view_two = findViewById(R.id.detail_two);
        detail_view_three = findViewById(R.id.detail_three);
        detail_view_four = findViewById(R.id.detail_four);
        detail_view_five = findViewById(R.id.detail_five);

        bindDetail(parentIndex);



        adapter = new DetailPagerAdapter(list_img,this);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(defaultIndex);
        switch (defaultIndex){
            case 0:
                detail_view_one.setAlpha(1f);
                break;
            case 1:
                detail_view_two.setAlpha(1f);
                break;
            case 2:
                detail_view_three.setAlpha(1f);
                break;
            case 3:
                detail_view_four.setAlpha(1f);
                break;
            case 4:
                detail_view_five.setAlpha(1f);
                break;
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(positionOffset > 0){
                    Log.e("tag",positionOffset+"");
                    switch (position){
                        case 0:
                            detail_view_one.setAlpha(1-positionOffset);
                            detail_view_two.setAlpha(positionOffset);
                            break;
                        case 1:
                            detail_view_two.setAlpha(1-positionOffset);
                            detail_view_three.setAlpha(positionOffset);
                            break;
                        case 2:
                            detail_view_three.setAlpha(1-positionOffset);
                            detail_view_four.setAlpha(positionOffset);
                            break;
                        case 3:
                            detail_view_four.setAlpha(1-positionOffset);
                            detail_view_five.setAlpha(positionOffset);
                            break;
                    }

                }else{
                    /*list_img.get(position).setAlpha(1-positionOffset);
                    list_img.get(position-1).setAlpha(position);*/
                }
            }

            @Override
            public void onPageSelected(int position) {
                list_img.get(position).setFocusable(true);
                list_img.get(position).setFocusableInTouchMode(true);
                list_img.get(position).requestFocus();
                list_img.get(position).requestFocusFromTouch();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void bindDetail(int position) {

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(position).getData().getCover().getBlurred()))
                        .into(img_buf_one);
                text_title_one.setText(issueListEntity.getItemList().get(position).getData().getTitle());
                text_summary_one.setText(issueListEntity.getItemList().get(position).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(issueListEntity.getItemList().get(position).getData().getDuration()));
                text_detail_one.setText(issueListEntity.getItemList().get(position).getData().getDescription());

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(position+1).getData().getCover().getBlurred()))
                        .into(img_buf_two);
                text_title_two.setText(issueListEntity.getItemList().get(position+1).getData().getTitle());
                text_summary_two.setText(issueListEntity.getItemList().get(position+1).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(issueListEntity.getItemList().get(position+1).getData().getDuration()));
                text_detail_two.setText(issueListEntity.getItemList().get(position+1).getData().getDescription());

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(position+2).getData().getCover().getBlurred()))
                        .into(img_buf_three);
                text_title_three.setText(issueListEntity.getItemList().get(position+2).getData().getTitle());
                text_summary_three.setText(issueListEntity.getItemList().get(position+2).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(issueListEntity.getItemList().get(position+2).getData().getDuration()));
                text_detail_three.setText(issueListEntity.getItemList().get(position+2).getData().getDescription());

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(position+3).getData().getCover().getBlurred()))
                        .into(img_buf_four);
                text_title_four.setText(issueListEntity.getItemList().get(position+3).getData().getTitle());
                text_summary_four.setText(issueListEntity.getItemList().get(position+3).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(issueListEntity.getItemList().get(position+3).getData().getDuration()));
                text_detail_four.setText(issueListEntity.getItemList().get(position+3).getData().getDescription());

                Picasso.with(this)
                        .load(Uri.parse(issueListEntity.getItemList().get(position+4).getData().getCover().getBlurred()))
                        .into(img_buf_five);
                text_title_five.setText(issueListEntity.getItemList().get(position+4).getData().getTitle());
                text_summary_five.setText(issueListEntity.getItemList().get(position+4).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(issueListEntity.getItemList().get(position+4).getData().getDuration()));
                text_detail_five.setText(issueListEntity.getItemList().get(position+4).getData().getDescription());

    }

}
