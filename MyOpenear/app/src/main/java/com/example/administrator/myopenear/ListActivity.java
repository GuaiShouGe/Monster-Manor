package com.example.administrator.myopenear;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myopenear.adapter.MyPagerAdapter;
import com.example.administrator.myopenear.fragement.Find_List_Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class ListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tablayout;
    private TextView tool_title;
    private String[] title = {"时间排序","分享排序"};
    private List<Fragment> totalList = new ArrayList<Fragment>();
    private ViewPager viewPager_main;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_layout);

        Intent intent = getIntent();
        Bundle bundle =  intent.getExtras();
        name = bundle.getString("name");
        //Toast.makeText(ListActivity.this,"拿到了"+name,Toast.LENGTH_LONG).show();


        toolbar = ((Toolbar) findViewById(R.id.toolbar_main));
        tablayout = ((TabLayout) findViewById(R.id.tabLayout_main));
        tool_title = ((TextView) findViewById(R.id.toolbar_title));
        viewPager_main = ((ViewPager) findViewById(R.id.viewPager_main));

        for (int i = 0; i < title.length; i++) {
            //tabLayout_main.newTab().setIcon(R.mipmap.ic_launcher);
            Find_List_Fragment fragment = Find_List_Fragment.getInstance(i ,name);
            totalList.add(fragment);
        }

        PagerAdapter adapter = new MyPagerAdapter(
                getSupportFragmentManager(), totalList, title);
        viewPager_main.setAdapter(adapter);
        tool_title.setText(name);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        tablayout.setupWithViewPager(viewPager_main);
        tablayout.setTabsFromPagerAdapter(adapter);

    }
}
