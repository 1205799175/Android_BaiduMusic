package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class MusicRankingAdapter extends BaseAdapter {
    private Context context;
    private List<MusicRankingBean> datas;

    public MusicRankingAdapter(Context context, List<MusicRankingBean> datas) {
        this.context = context;
        this.datas = datas;
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
        viewHolder.songOneTv.setText(datas.get(position).getSongOne());
        viewHolder.songTwoTv.setText(datas.get(position).getSongTwo());
        viewHolder.songThreeTv.setText(datas.get(position).getSongThree());
        viewHolder.titleTv.setText(datas.get(position).getTitle());
        return convertView;
    }

    class ViewHolder{
        TextView songOneTv, songTwoTv, songThreeTv;
        TextView titleTv;
        LinearLayout imgId;
        private ViewHolder(View view){
            songOneTv = (TextView) view.findViewById(R.id.item_ranking_song_one);
            songTwoTv = (TextView) view.findViewById(R.id.item_rankging_song_two);
            songThreeTv = (TextView) view.findViewById(R.id.item_ranking_song_three);
            titleTv = (TextView) view.findViewById(R.id.item_ranking_title);
            imgId = (LinearLayout) view.findViewById(R.id.item_ranking_img);
        }
    }
}
