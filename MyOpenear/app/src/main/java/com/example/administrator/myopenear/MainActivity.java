package com.example.administrator.myopenear;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myopenear.fragement.DailyFragment;
import com.example.administrator.myopenear.fragement.Find_Fragment;
import com.example.administrator.myopenear.fragement.Hot_Fragment;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tab_category;
    private TextView tab_daily;
    private TextView tab_rank;
    private Fragment currentFragment;
    private Find_Fragment categoriesFragment;
    private DailyFragment dailyFragment;
    private Hot_Fragment rankFragment;
    private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    private Toolbar toolbar;
    private Context context = this;

    private DrawerLayout drawerLayout_main;
    private NavigationView navigationView_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chouti_layout);
        ShareSDK.initSDK(this);
        initUI();
        initTab();

        drawerLayout_main = (DrawerLayout) findViewById(R.id.drawerLayout_main);

        //初始化导航试图
        navigationView_main = (NavigationView) findViewById(R.id.navigationView_main);
        navigationView_main.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        drawerLayout_main.closeDrawers();
                        return true;
                    }

                });

        navigationView_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Intent intent = new Intent(context, SavnActivity.class);
                        context.startActivity(intent);
                        drawerLayout_main.closeDrawers();
                        break;


                }

                return false;
            }
        });

        View headerView = navigationView_main.getHeaderView(0);
        ImageView headerimg = (ImageView) headerView.findViewById(R.id.header_img);
        headerimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "讲道理", Toast.LENGTH_SHORT).show();
                QQ qq = new QQ(context);
                authorize(qq);
            }
        });



    }
    private void authorize(Platform plat) {
        //判断指定平台是否已经完成授权
        //用户是否已经登录过
        if (plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (userId != null) {
                String userName = plat.getDb().getUserName();
                Log.d("lenve", "用户已经获得授权：authorize: username:" + userName);
                return;
            }
            //用户还没登录过
        } else {
            //引导用户进行登录
            Log.d("lenve", "authorize: 用户尚未登录，引导用户进行登录！");
            plat.SSOSetting(false);
            plat.showUser(null);
            plat.authorize();
        }
        //监听登录是否成功
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            //完成
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.d("lenve", "onComplete: 登录成功！");
            }

            @Override
            //异常
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            //取消
            public void onCancel(Platform platform, int i) {

            }
        });
    }

    private void initTab() {
        if(dailyFragment == null){
            dailyFragment = new DailyFragment(context);
        }

        if(!dailyFragment.isAdded()){
            //提交事务
            transaction.add(R.id.fragment_container,dailyFragment).commit();

            //记录当前fragment
            currentFragment = dailyFragment;
            //设置tab变化
            tab_daily.setTextColor(Color.BLACK);
            tab_category.setTextColor(Color.WHITE);
            tab_rank.setTextColor(Color.WHITE);
        }
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        tab_category = (TextView) findViewById(R.id.tab_category);
        tab_daily = (TextView) findViewById(R.id.tab_daily);
        tab_rank = (TextView) findViewById(R.id.tab_rank);
        toolbar = ((Toolbar) findViewById(R.id.toolbar_main));
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitle("Today");
        setSupportActionBar(toolbar);

        tab_category.setOnClickListener(this);
        tab_daily.setOnClickListener(this);
        tab_rank.setOnClickListener(this);


    }

    /**
     * 添加或显示Fragment
     */

    private void addOrShowFragment(FragmentTransaction transaction,Fragment fragment){
        if(currentFragment == fragment){
            return;
        }

        if(!fragment.isAdded()){ //如果当前Fragment未被添加，则添加到Fragment管理器中
            getSupportFragmentManager().beginTransaction().hide(currentFragment)
                    .add(R.id.fragment_container,fragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_category:
                click_tab_category();
                break;
            case R.id.tab_daily:
                click_tab_daily();
                break;
            case R.id.tab_rank:
                click_tab_rank();
                break;
        }
    }

    private void click_tab_rank() {
        if(rankFragment == null){
            rankFragment = new Hot_Fragment(context);
        }

        addOrShowFragment(transaction,rankFragment);
        toolbar.setTitle(" ");
        tab_rank.setTextColor(Color.BLACK);
        tab_category.setTextColor(Color.WHITE);
        tab_daily.setTextColor(Color.WHITE);
    }

    private void click_tab_daily() {
        if(dailyFragment == null){
            dailyFragment = new DailyFragment(context);
        }

        addOrShowFragment(transaction,dailyFragment);
        tab_daily.setTextColor(Color.BLACK);
        tab_category.setTextColor(Color.WHITE);
        tab_rank.setTextColor(Color.WHITE);
    }

    private void click_tab_category() {
        if(categoriesFragment == null){
            categoriesFragment = new Find_Fragment(context);
        }

        addOrShowFragment(transaction,categoriesFragment);
        toolbar.setTitle(" ");
        tab_category.setTextColor(Color.BLACK);
        tab_rank.setTextColor(Color.WHITE);
        tab_daily.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout_main.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        ShareSDK.stopSDK(context);
        super.onDestroy();
    }
}



