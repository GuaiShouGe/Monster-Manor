package com.example.administrator.myopenear.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;


/**
 * Created by Administrator on 2016/3/29.
 */
public class RankFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] tabTitles;

    public RankFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments,String[] tabTitles) {
        super(fm);
        this.fragments = fragments;
        this.tabTitles = tabTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
