package com.example.administrator.myopenear.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.adapter.Find_Adapter;
import com.example.administrator.myopenear.bean.Fenglei;
import com.example.administrator.myopenear.bean.MyApi;
import com.example.administrator.myopenear.utils.HttpUtils2;
import com.example.administrator.myopenear.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Find_Fragment extends Fragment {

    private Find_Adapter adapter;
    private List<Fenglei.ClasspasadEntity> list = new ArrayList<>();
    private Fenglei categoryListBean;
    private Context context;

    public Find_Fragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_category_fragment,container,false);
        RecyclerView recyclerView = ((RecyclerView) view.findViewById(R.id.caterory_recy));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        String url = MyApi.CATEGORY_LIST_URL;

        datadown(url);
        adapter = new Find_Adapter(context,list);
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void datadown(final String url) {

        //ptrFrameLayout.refreshComplete();
        new Thread(new Runnable() {

            @Override
            public void run() {

                String json2 = HttpUtils2.download(url);
                String json = "{classpasad:"+json2+"}";
                categoryListBean = ParseUtils.parsecate(json);
                Log.e("TAG",json);
                List<Fenglei.ClasspasadEntity> datas = categoryListBean.getClasspasad();
                list.addAll(datas);

                //ThreeUtils.notifyAdapter(handler,adapter);
            }
        }).start();
    }


}
