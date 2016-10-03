package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 * 乐库 排行 ListView适配器
 */
public class MusicRankingLvAdapter extends BaseAdapter {
    private Context context;
    private List<MusicRankingBean.ContentBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 3 - 40;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;

    public MusicRankingLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRankingBean.ContentBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null && datas.size() > 0 ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music_ranking_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(datas.get(position).getName());
        Glide.with(context).load(datas.get(position).getPic_s192()).override(width, height).into(viewHolder.titleImg);
        viewHolder.songOneTv.setText(datas.get(position).getmContent().get(0).getTitle() + " - " + datas.get(position).getmContent().get(0).getAuthor());
        viewHolder.songTwoTv.setText(datas.get(position).getmContent().get(1).getTitle() + " - " + datas.get(position).getmContent().get(1).getAuthor());
        viewHolder.songThreeTv.setText(datas.get(position).getmContent().get(2).getTitle() + " - " + datas.get(position).getmContent().get(2).getAuthor());
        return convertView;
    }

    class ViewHolder{
        TextView songOneTv, songTwoTv, songThreeTv;
        TextView titleTv;
        ImageView titleImg;
        private ViewHolder(View view){
            songOneTv = (TextView) view.findViewById(R.id.item_ranking_song_one);
            songTwoTv = (TextView) view.findViewById(R.id.item_ranking_song_two);
            songThreeTv = (TextView) view.findViewById(R.id.item_ranking_song_three);
            titleTv = (TextView) view.findViewById(R.id.item_ranking_title);
            titleImg = (ImageView) view.findViewById(R.id.item_ranking_img);

        }
    }
}
