package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 */
public class OwnLocalMusicLvAdapter extends BaseAdapter {
    private List<OwnLocalMusicLvBean> datas;
    private Context context;

    public OwnLocalMusicLvAdapter(List<OwnLocalMusicLvBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
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
        OwnLocalMusicViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_own_local_music_lv, parent, false);
            holder = new OwnLocalMusicViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OwnLocalMusicViewHolder) convertView.getTag();
        }
        holder.songTv.setText(datas.get(position).getSong());
        holder.singerTv.setText(datas.get(position).getSinger());
        return convertView;
    }

    class OwnLocalMusicViewHolder{
        private TextView songTv, singerTv, letterTv;
        public OwnLocalMusicViewHolder(View view){
            songTv = (TextView) view.findViewById(R.id.local_music_lv_song);
            singerTv = (TextView) view.findViewById(R.id.local_music_lv_singer);
            letterTv = (TextView) view.findViewById(R.id.local_music_lv_letter_tv);
        }
    }
}
