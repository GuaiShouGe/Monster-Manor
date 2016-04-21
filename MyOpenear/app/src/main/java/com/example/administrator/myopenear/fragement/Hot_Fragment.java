package com.example.administrator.myopenear.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.RankFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Hot_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private Context context;
    private String[] typ = {"周排行","月排行","总排行"};

    public Hot_Fragment(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_rank_fragment,container,false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_rank);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_rank);

        String[] arrTabTitles = {"周排行","月排行","总排行"};
        for(int i = 0;i<arrTabTitles.length;i++){
            Three_Frament fragment = Three_Frament.getInstan(i);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new RankFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragments,arrTabTitles));

        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
