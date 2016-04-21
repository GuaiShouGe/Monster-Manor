package com.example.administrator.myopenear.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myopenear.PlayActivity;
import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.bean.RankListBean;
import com.example.administrator.myopenear.utils.DateFormatUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public class BaseRankListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<RankListBean.ItemListEntity> itemList;
    private Context context;

    public BaseRankListAdapter(List<RankListBean.ItemListEntity> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rank_list,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SimpleDraweeView img_cover = (SimpleDraweeView) holder.itemView.findViewById(R.id.img_cover);
        TextView text_title = (TextView) holder.itemView.findViewById(R.id.text_title);
        TextView text_summary = (TextView) holder.itemView.findViewById(R.id.text_summary);
        TextView index = (TextView) holder.itemView.findViewById(R.id.index);

        img_cover.setTag(itemList.get(position).getData().getCover().getFeed());

        if(img_cover.getTag() != null && img_cover.getTag().equals(itemList.get(position).getData().getCover().getFeed())){
            Log.e("tag","8888888888888");
            img_cover.setImageURI(Uri.parse(itemList.get(position).getData().getCover().getFeed()));
            img_cover.setTag(itemList.get(position).getData().getCover().getFeed());

        }else{

            return;
        }


        text_title.setText(itemList.get(position).getData().getTitle());
        text_summary.setText("#" + itemList.get(position).getData().getCategory() + " / "
                + DateFormatUtils.formatDuration(itemList.get(position).getData().getDuration()));
        index.setText(position+1+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PlayActivity.class);
                Bundle bundle = new Bundle();

                String img_1 = itemList.get(position).getData().getCover().getFeed();
                String img_2 = itemList.get(position).getData().getCover().getBlurred();
                String text_1 = itemList.get(position).getData().getTitle();
                String text_2 = ("#"+itemList.get(position).getData().getCategory()+" / "
                        + DateFormatUtils.formatDuration(itemList.get(position).getData().getDuration()));
                String text_3 = itemList.get(position).getData().getDescription();

                String url = itemList.get(position).getData().getPlayUrl();
                String[] into = {img_1,img_2,text_3,url,text_1,text_2};



                bundle.putStringArray("info",into);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
