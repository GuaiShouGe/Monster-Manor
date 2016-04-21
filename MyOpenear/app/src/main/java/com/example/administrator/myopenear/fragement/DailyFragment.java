package com.example.administrator.myopenear.fragement;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.DailyListAdapter;
import com.example.administrator.myopenear.bean.DailyBean;
import com.example.administrator.myopenear.bean.MyApi;
import com.example.administrator.myopenear.utils.HttpUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/3/26.
 */
public class DailyFragment extends Fragment {

    private PullToRefreshListView ptrListView;
    private List<DailyBean.IssueListEntity> issueList = new ArrayList<DailyBean.IssueListEntity>();
    private DailyListAdapter adapter;
    private Handler handler = new Handler();
    private DailyBean dailyBean;
    private int num = 2;
    private String url = MyApi.DAILY_LIST_URL + num;
    private int lastItem;
    private Context context;

    public DailyFragment(Context context) {
            this.context = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_daily_fragment,container,false);
        ptrListView = (PullToRefreshListView) view.findViewById(R.id.ptrListView);

        adapter = new DailyListAdapter(issueList,context);
        ptrListView.setAdapter(adapter);

        ptrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                num = 2;
                issueList.clear();
                initData(url);
                ptrListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrListView.onRefreshComplete();
                    }
                },500);
            }
        });

        ptrListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    if(view.getLastVisiblePosition() == view.getCount() -1){
                        num += 2;
                        initData(dailyBean.getNextPageUrl());

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1;
            }
        });

        initData(url);

        return view;
    }

    private void initData(String url) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        HttpUtils.enqueue(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "访问网络失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    return;
                }

                String json = response.body().string();
                Log.e("TAG", json);
                Gson gson = new Gson();
                dailyBean = gson.fromJson(json, DailyBean.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        issueList.addAll(dailyBean.getIssueList());

                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
