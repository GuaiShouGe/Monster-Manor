package com.example.administrator.myopenear.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.SavnRecyAdapter;
import com.example.administrator.myopenear.bean.SexNews;
import com.fmsirvent.ParallaxEverywhere.PEWImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class SavnActivityTwo extends AppCompatActivity {

    private String url;
    String img_1;
    String img_2;
    String shuoming;
    String tiltl;
    private PEWImageView img_one;
    private PEWImageView img_two;
    private TextView text_one;
    private TextView text_two;
    private TextView text_three;
    private Context context = this;
    private boolean isclick;
    private SavnRecyAdapter adapter;
    private Handler handler;


    private String summer;


    private ImageButton imgbutton;

    private ImageButton love;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savn_layout_two);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String[] info = bundle.getStringArray("info");


        img_1 = info[0];
        img_2 = info[1];
        shuoming = info[2];
        url = info[3];
        tiltl = info[4];
        summer = info[5];


        img_one = ((PEWImageView) findViewById(R.id.savn_img_1));
        img_two = ((PEWImageView) findViewById(R.id.savn_img_2));
        text_one = ((TextView) findViewById(R.id.savn_text_1));
        text_two = ((TextView) findViewById(R.id.savn_text_2));
        text_three = ((TextView) findViewById(R.id.savn_text_3));
        imgbutton = ((ImageButton) findViewById(R.id.savn_play));

        love = ((ImageButton) findViewById(R.id.savn_love));


        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclick) {
                    love.setImageResource(R.drawable.love1);
                    isclick = true;
                    new Delete().from(SexNews.class).where("title = ?", tiltl).execute();
                    Select select = new Select();
                    List<SexNews>list = select.from(SexNews.class).execute();





                } else {
                    love.setImageResource(R.drawable.love2);
                    isclick = false;
                    SexNews sexNews = new SexNews();
                    sexNews.setFeed(img_1);
                    sexNews.setBlurred(img_2);
                    sexNews.setTitle(tiltl);
                    sexNews.setDescription(shuoming);
                    sexNews.setCategory(summer);
                    sexNews.setPlayUrl(url);
                    sexNews.save();
                }
            }
        });


        Picasso.with(context)
                .load(img_1)
                .config(Bitmap.Config.RGB_565)
                .into(img_one);

        Picasso.with(context)
                .load(img_2)
                .config(Bitmap.Config.RGB_565)
                .into(img_two);
        text_one.setText(tiltl);
        text_two.setText(summer);
        text_three.setText(shuoming);


        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context, PlayActivitytwo.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("url", url);
                intent1.putExtras(bundle1);
                context.startActivity(intent1);
                Log.e("TAG", "点了" + url);

            }
        });


    }

    public static SexNews getRandom(String tilte) {
        return new Select()
                .from(SexNews.class)
                .where("title = ?", tilte)
                .executeSingle();
    }


}
