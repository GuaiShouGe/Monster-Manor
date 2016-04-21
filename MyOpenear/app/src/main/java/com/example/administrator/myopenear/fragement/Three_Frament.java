package com.example.administrator.myopenear.fragement;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.BaseRankListAdapter;
import com.example.administrator.myopenear.bean.RankListBean;
import com.example.administrator.myopenear.utils.HttpUtils;
import com.example.administrator.myopenear.utils.HttpUtils2;
import com.example.administrator.myopenear.utils.ParseUtils;
import com.example.administrator.myopenear.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Three_Frament extends Fragment {
    private Context context;
    private String type;
    private View view;
    private BaseRankListAdapter adapter;
    private String url;
    private RankListBean rankListBean;
    private Handler handler = new Handler();
    private List<RankListBean.ItemListEntity>list = new ArrayList<>();




    public static Three_Frament getInstan(int flag) {
        Three_Frament fragment = new Three_Frament();
        Bundle bundle = new Bundle();
        bundle.putInt("flag",flag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        int flag = bundle.getInt("flag");
        switch (flag){
            case 0:
                url = "http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=weekly&udid=4dde598bed5a4cf29a12b93b8006189955517851&vc=89&vn=1.13.1&deviceModel=SM701&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market";
                break;
            case 1:
                url ="http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=monthly&udid=4dde598bed5a4cf29a12b93b8006189955517851&vc=89&vn=1.13.1&deviceModel=SM701&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market";
                break;
            case 2:
                url ="http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=historical&udid=4dde598bed5a4cf29a12b93b8006189955517851&vc=89&vn=1.13.1&deviceModel=SM701&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market";
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {

                view = inflater.inflate(R.layout.layout_rank_list_fragment,container,false);
                //url = "http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=weekly&udid=4dde598bed5a4cf29a12b93b8006189955517851&vc=89&vn=1.13.1&deviceModel=SM701&first_channel=eyepetizer_wandoujia_market&last_channel=eyepetizer_wandoujia_market";

                adapter = new BaseRankListAdapter(list,getContext());
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_ranklist);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                recyclerView.setAdapter(adapter);
                datadown(url);

        }
        return view;
    }


    private void datadown(final String url) {

        //ptrFrameLayout.refreshComplete();
        new Thread(new Runnable() {

            @Override
            public void run() {

                String json = HttpUtils2.download(url);
                rankListBean = ParseUtils.parseran(json);
                List<RankListBean.ItemListEntity> datas = rankListBean.getItemList();
                list.addAll(datas);

                ThreadUtils.notifyAdapter(handler, adapter);
            }
        }).start();
    }
}
