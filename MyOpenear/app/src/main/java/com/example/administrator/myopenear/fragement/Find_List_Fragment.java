package com.example.administrator.myopenear.fragement;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.Find_List_Adapter;
import com.example.administrator.myopenear.bean.Fenglei;
import com.example.administrator.myopenear.bean.Find_List_Bean;
import com.example.administrator.myopenear.utils.HttpUtils2;
import com.example.administrator.myopenear.utils.Linkes;
import com.example.administrator.myopenear.utils.ParseUtils;
import com.example.administrator.myopenear.utils.ThreadUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Find_List_Fragment extends Fragment {
    private String url;
    private TextView text_name;
    private Handler handler = new Handler();

    private RecyclerView recyclerview;
    private Find_List_Adapter adapter;
    private Find_List_Bean find_list_bean;
    private LinearLayoutManager linearLayoutManager;
    private List<Find_List_Bean.ItemListEntity>list = new ArrayList<>();


    public static Find_List_Fragment getInstance(int tabindex,String name) {
        Find_List_Fragment fragment = new Find_List_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("tabindex", tabindex);
        bundle.putString("name",name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle2 = getArguments();

        int index = bundle2.getInt("tabindex");
         String name = bundle2.getString("name");
        switch (index){
            case 0:

                    url = Linkes.URL_CATEGORY_ONE +name+Linkes.URL_DATE;
                    //Log.e("TAG========111",url);

                break;
            case 1:

                    url =  Linkes.URL_CATEGORY_ONE +name+Linkes.URL_SHARE;
                    //Log.e("TAG=======2222",url);

                break;
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_list, container, false);


        recyclerview = ((RecyclerView) view.findViewById(R.id.find_list));
         linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(linearLayoutManager);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {

                    int lostPos = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    if (lostPos == linearLayoutManager.getItemCount() - 1) {

                        url = find_list_bean.getNextPageUrl();

                       datadown(url);
                    }
                }


            }
        });

        datadown(url);
        adapter = new Find_List_Adapter(getContext(),list);
        recyclerview.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    private void datadown( final String url) {

        //ptrFrameLayout.refreshComplete();
        new Thread(new Runnable() {

            @Override
            public void run() {

                String json= HttpUtils2.download(url);
                //String json = "{classpasad:"+json2+"}";
                find_list_bean = ParseUtils.parsefindlist(json);

                List<Find_List_Bean.ItemListEntity> datas = find_list_bean.getItemList();

                list.addAll(datas);

                ThreadUtils.notifyAdapter(handler, adapter);
            }
        }).start();
    }

}
