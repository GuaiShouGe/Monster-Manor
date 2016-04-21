package com.example.administrator.myopenear.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myopenear.ListActivity;
import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.bean.CategoryListBean;
import com.example.administrator.myopenear.bean.Fenglei;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fmsirvent.ParallaxEverywhere.PEWImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Find_Adapter extends RecyclerView.Adapter<Find_Adapter.ViewHolder> {
    private Context context ;
    private List<Fenglei.ClasspasadEntity> data;


    public Find_Adapter(Context context, List<Fenglei.ClasspasadEntity> data) {
        this.data = data;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_category_list,parent,false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.category_img.setImageURI(Uri.parse(data.get(position).getBgPicture()));
        holder.category_text.setText(data.get(position).getName());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"名称"+data.get(position).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",data.get(position).getName());
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        @Bind(R.id.category_img)SimpleDraweeView category_img;
        @Bind(R.id.category_text)TextView category_text;
        public ViewHolder(View viewitem) {
            super(viewitem);
            this.view = viewitem;
            ButterKnife.bind(this, view);
        }
    }


}

