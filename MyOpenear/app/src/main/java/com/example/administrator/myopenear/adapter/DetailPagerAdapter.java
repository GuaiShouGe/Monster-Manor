package com.example.administrator.myopenear.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class DetailPagerAdapter extends PagerAdapter{

    private List<View> list_img;
    private Context context;
    //private DailyBean.IssueListEntity issueListEntity;

    public DetailPagerAdapter(List<View > list_img,Context context) {
        this.list_img = list_img;
        this.context = context;
        //this.issueListEntity = issueListEntity;

    }

    @Override
    public int getCount() {
        return list_img.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list_img.get(position));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        container.addView(list_img.get(position));


        return list_img.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
