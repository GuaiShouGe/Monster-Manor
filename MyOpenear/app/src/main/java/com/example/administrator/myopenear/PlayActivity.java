package com.example.administrator.myopenear;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.administrator.myopenear.bean.SexNews;
import com.example.administrator.myopenear.cn.sharesdk.onekeyshare.OnekeyShare;
import com.example.administrator.myopenear.utils.PlayActivitytwo;
import com.fmsirvent.ParallaxEverywhere.PEWImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class PlayActivity extends AppCompatActivity {


    //private VideoView video;
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

    private String summer;


    private ImageButton imgbutton;

    private ImageButton love;
    private ImageButton share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String[] info = bundle.getStringArray("info");


        img_1 = info[0];
        img_2 = info[1];
        shuoming = info[2];
        url = info[3];
        tiltl = info[4];
        summer = info[5];


        img_one = ((PEWImageView) findViewById(R.id.img_1));
        img_two = ((PEWImageView) findViewById(R.id.img_2));
        text_one = ((TextView) findViewById(R.id.text_1));
        text_two = ((TextView) findViewById(R.id.text_2));
        text_three = ((TextView) findViewById(R.id.text_3));
        imgbutton = ((ImageButton) findViewById(R.id.play));

        love = ((ImageButton) findViewById(R.id.love));
        share = ((ImageButton) findViewById(R.id.share));

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareSDK.initSDK(context);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");
// 启动分享GUI
                oks.show(context);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclick) {
                    love.setImageResource(R.drawable.love2);
                    isclick = true;
                    SexNews sexNews = new SexNews();
                    sexNews.setFeed(img_1);
                    sexNews.setBlurred(img_2);
                    sexNews.setTitle(tiltl);
                    sexNews.setDescription(shuoming);
                    sexNews.setCategory(summer);
                    sexNews.setPlayUrl(url);
                    sexNews.save();

                    Log.e("TAG+++++++++", getRandom(tiltl).toString());
                } else {
                    love.setImageResource(R.drawable.love1);
                    isclick = false;
                    new Delete().from(SexNews.class).where("title = ?", tiltl).execute();

                    Select select = new Select();
                    List<SexNews> newsList = select.from(SexNews.class).execute();


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




