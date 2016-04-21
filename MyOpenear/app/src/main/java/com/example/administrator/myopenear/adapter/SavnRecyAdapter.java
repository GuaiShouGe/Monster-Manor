package com.example.administrator.myopenear.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myopenear.PlayActivity;
import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.bean.Find_List_Bean;
import com.example.administrator.myopenear.bean.SexNews;
import com.example.administrator.myopenear.utils.DateFormatUtils;
import com.example.administrator.myopenear.utils.SavnActivityTwo;
import com.fmsirvent.ParallaxEverywhere.PEWImageView;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/4/1 0001.
 */
public class SavnRecyAdapter extends RecyclerView.Adapter<SavnRecyAdapter.ViewHolder> {


    private Context context;
    private List<SexNews> list;

    public SavnRecyAdapter(Context context, List<SexNews> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.savn_recy_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PEWImageView img_1 = (PEWImageView) holder.view.findViewById(R.id.savn_list_img);
        Picasso.with(context)
                .load(list.get(position).getFeed())
                .config(Bitmap.Config.RGB_565)
                .into(img_1);

        TextView tilte = (TextView) holder.view.findViewById(R.id.savn_list_title);
        tilte.setText(list.get(position).getTitle());

        TextView summer = (TextView) holder.view.findViewById(R.id.savn_list_summer);
        summer.setText(list.get(position).getCategory());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(position);
                String img1 = list.get(position).getBlurred();
                String img2 = list.get(position).getFeed();
                String tile = list.get(position).getTitle();
                String cat = list.get(position).getCategory();
                String shuoming = list.get(position).getDescription();
                String url = list.get(position).getPlayUrl();

                Intent intent = new Intent(context,SavnActivityTwo.class);


                Bundle bundle = new Bundle();

                String[] into ={img2,img1,shuoming,url,tile,cat};
                bundle.putStringArray("info",into);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;



        public ViewHolder(View viewitem) {
            super(viewitem);
            this.view = viewitem;

        }
    }
}

