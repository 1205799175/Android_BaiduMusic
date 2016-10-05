package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.LyricBean;

import java.util.List;

/**
 * Created by dllo on 16/10/3.
 * 播放音乐界面 歌词ListView适配器
 */
public class PlayPageLyricLvAdapter extends BaseAdapter{
    private Context context;
    private List<LyricBean> datas;


    public void setDatas(List<LyricBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public PlayPageLyricLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public LyricBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_playpage_lyric_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.lyricTv.setText(datas.get(position).getLyricPiece());
        return convertView;
    }

    class ViewHolder {
        TextView lyricTv;

        public ViewHolder(View view) {
            lyricTv = (TextView) view.findViewById(R.id.item_playpage_lyric_tv);
        }
    }
}
