package com.example.administrator.myopenear.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myopenear.PlayActivity;
import com.example.administrator.myopenear.R;
import com.example.administrator.myopenear.bean.DailyBean;
import com.example.administrator.myopenear.utils.DateFormatUtils;
import com.example.administrator.myopenear.utils.DetailActivity;
import com.fmsirvent.ParallaxEverywhere.PEWImageView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Rockish on 2016/3/28.
 */
public class DailyListAdapter extends BaseAdapter {

    private List<DailyBean.IssueListEntity> issueList;
    private Context context;
    //private Typeface typeface;

    public DailyListAdapter(List<DailyBean.IssueListEntity> issueList, Context context) {
        this.issueList = issueList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return issueList.size();
    }

    @Override
    public Object getItem(int position) {
        return issueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //typeface = Typeface.createFromAsset(context.getAssets(),"fonts/lobstor.ttf");
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            if(LayoutInflater.from(context) == null){
                //Log.e("TAG","9999777"+issueList.size());
            }
            convertView = LayoutInflater.from(context).inflate(R.layout.item_baselist,null);

            viewHolder.date_header = (TextView) convertView.findViewById(R.id.date_header);

            viewHolder.img_cover_one = (PEWImageView) convertView.findViewById(R.id.img_cover_one);
            viewHolder.text_title_one = (TextView) convertView.findViewById(R.id.text_title_one);
            viewHolder.text_summary_one = (TextView) convertView.findViewById(R.id.text_summary_one);

            viewHolder.img_cover_two = (PEWImageView) convertView.findViewById(R.id.img_cover_two);
            viewHolder.text_title_two = (TextView) convertView.findViewById(R.id.text_title_two);
            viewHolder.text_summary_two = (TextView) convertView.findViewById(R.id.text_summary_two);

            viewHolder.img_cover_three = (PEWImageView) convertView.findViewById(R.id.img_cover_three);
            viewHolder.text_title_three = (TextView) convertView.findViewById(R.id.text_title_three);
            viewHolder.text_summary_three = (TextView) convertView.findViewById(R.id.text_summary_three);

            viewHolder.img_cover_four = (PEWImageView) convertView.findViewById(R.id.img_cover_four);
            viewHolder.text_title_four = (TextView) convertView.findViewById(R.id.text_title_four);
            viewHolder.text_summary_four = (TextView) convertView.findViewById(R.id.text_summary_four);

            viewHolder.img_cover_five = (PEWImageView) convertView.findViewById(R.id.img_cover_five);
            viewHolder.text_title_five = (TextView) convertView.findViewById(R.id.text_title_five);
            viewHolder.text_summary_five = (TextView) convertView.findViewById(R.id.text_summary_five);

            convertView.setTag(viewHolder);
            Log.e("TAG","9999988"+issueList.size());
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position == 0){
            viewHolder.date_header.setVisibility(View.GONE);
        }else if("weekendExtra".equals(issueList.get(position).getType())){
            viewHolder.date_header.setText("-Weekend Special-");
            //viewHolder.date_header.setTypeface(typeface);
        }else {
            viewHolder.date_header.setText(issueList.get(position).getItemList().get(0).getData().getText());
            //viewHolder.date_header.setTypeface(typeface);
        }

       int count = issueList.get(position).getCount() - 5;

        if(issueList.get(position).getCount()==6 && !issueList.get(position).getItemList().get(5).getType().equals("video")){
            count  = count -1;
           // Log.e("TAG","等于六");


        }
        if(issueList.get(position).getCount()==8 && !issueList.get(position).getItemList().get(6).getType().equals("video")){
            count  = count -2;
            // Log.e("TAG","等于六");


        }
        if(issueList.get(position).getCount()==7 && issueList.get(position).getItemList().get(5).getType().equals("video")){
            count  = count -1;
            // Log.e("TAG","等于六");


        }


        initItemInner(position, viewHolder.img_cover_one, viewHolder.text_title_one, viewHolder.text_summary_one, count);
        initItemInner(position,viewHolder.img_cover_two,viewHolder.text_title_two,viewHolder.text_summary_two,count + 1);
        initItemInner(position,viewHolder.img_cover_three,viewHolder.text_title_three,viewHolder.text_summary_three,count + 2);
        initItemInner(position,viewHolder.img_cover_four,viewHolder.text_title_four,viewHolder.text_summary_four,count + 3);
        initItemInner(position,viewHolder.img_cover_five,viewHolder.text_title_five,viewHolder.text_summary_five,count + 4);


        return convertView;
    }

    private void initItemInner(final int position,PEWImageView img_cover,TextView text_title,TextView text_summary, final int index) {
        Picasso.with(context)
                .load(Uri.parse(issueList.get(position).getItemList().get(index).getData().getCover().getFeed()))
                .resize(600,480)
                .into(img_cover);
        img_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetail(position, index);
            }
        });


        text_title.setText(issueList.get(position).getItemList().get(index).getData().getTitle());
        text_summary.setText("#"+issueList.get(position).getItemList().get(index).getData().getCategory()+" / "
                + DateFormatUtils.formatDuration(issueList.get(position).getItemList().get(index).getData().getDuration()));



    }

    class ViewHolder {
        private TextView date_header;

        private PEWImageView img_cover_one;
        private TextView text_title_one;
        private TextView text_summary_one;

        private PEWImageView img_cover_two;
        private TextView text_title_two;
        private TextView text_summary_two;

        private PEWImageView img_cover_three;
        private TextView text_title_three;
        private TextView text_summary_three;

        private PEWImageView img_cover_four;
        private TextView text_title_four;
        private TextView text_summary_four;

        private PEWImageView img_cover_five;
        private TextView text_title_five;
        private TextView text_summary_five;
    }

    public void intozhe(int position,int finalCount){
        Intent intent = new Intent(context,PlayActivity.class);
        Bundle bundle = new Bundle();

        String img_1 = issueList.get(position).getItemList().get(finalCount).getData().getCover().getFeed();
        String img_2 = issueList.get(position).getItemList().get(finalCount).getData().getCover().getBlurred();
        String shuoming = issueList.get(position).getItemList().get(finalCount).getData().getDescription();
        String url = issueList.get(position).getItemList().get(finalCount).getData().getPlayUrl();
        String tiltl = issueList.get(position).getItemList().get(finalCount).getData().getTitle();
        String summer = ("#"+issueList.get(position).getItemList().get(position).getData().getCategory()+" / "
                + DateFormatUtils.formatDuration(issueList.get(position).getItemList().get(finalCount).getData().getDuration()));
        String[] info ={img_1,img_2,shuoming,url,tiltl,summer};

        bundle.putStringArray("info", info);

        intent.putExtras(bundle);

        context.startActivity(intent);
    }
    public void getDetail(int position,int index){
        DailyBean.IssueListEntity issueListEntity = issueList.get(position);
        Intent intent = new Intent(context, DetailActivity.class);
        //intent.putExtra("issueList", issueListEntity);
       intent.putExtra("issueList", issueListEntity);
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
