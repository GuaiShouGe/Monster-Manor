package com.example.administrator.myopenear.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myopenear.PlayActivity;
import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.bean.Find_List_Bean;
import com.example.administrator.myopenear.utils.DateFormatUtils;
import com.example.administrator.myopenear.utils.PlayActivitytwo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fmsirvent.ParallaxEverywhere.PEWImageView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/30 0030.
 */
public class Find_List_Adapter extends RecyclerView.Adapter<Find_List_Adapter.ViewHolder> {


    private Context context;
    private List<Find_List_Bean.ItemListEntity>list;

    public Find_List_Adapter(Context context, List<Find_List_Bean.ItemListEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =  LayoutInflater.from(context).inflate(R.layout.find_list_rey,parent,false);
        ViewHolder vh = new ViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TextView text_title = (TextView) holder.view.findViewById(R.id.find_list_title);
        TextView text_summer = (TextView) holder.view.findViewById(R.id.find_list_summer);
        PEWImageView find_img = (PEWImageView) holder.view.findViewById(R.id.find_list_img);

        text_title.setText(list.get(position).getData().getTitle());
        text_summer.setText("#" + list.get(position).getData().getCategory() + " / "
                + DateFormatUtils.formatDuration(list.get(position).getData().getDuration()));

        Picasso.with(context)
                .load(list.get(position).getData().getCover().getFeed())
                .config(Bitmap.Config.RGB_565)
                .into(find_img);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PlayActivity.class);
                Bundle bundle = new Bundle();
               String img_1 = list.get(position).getData().getCover().getFeed();
                String img_2 = list.get(position).getData().getCover().getBlurred();
                String text_1 = list.get(position).getData().getTitle();
                String text_2 = ("#"+list.get(position).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(list.get(position).getData().getDuration()));
                String text_3 = list.get(position).getData().getDescription();

                String url = list.get(position).getData().getPlayUrl();
                String[] into = {img_1,img_2,text_3,url,text_1,text_2};

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
        //@Bind(R.id.find_list_img)PEWImageView find_list_img;
        //@Bind(R.id.find_list_title)TextView find_list_title;
        //@Bind(R.id.find_list_summer)TextView find_list_summer;
        public ViewHolder(View viewitem) {
            super(viewitem);
            this.view = viewitem;
           // ButterKnife.bind(this, view);
        }
    }
}
