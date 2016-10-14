package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/19.
 * 我的Fragment 本地音乐 二级页面 ListView适配器
 */
public class OwnLocalMusicLvAdapter extends BaseAdapter {
    private List<MusicBean> datas;
    private Context context;

    public OwnLocalMusicLvAdapter(List<MusicBean> datas, Context context) {
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
        holder.songTv.setText(datas.get(position).getSonginfo().getTitle());
        holder.singerTv.setText(datas.get(position).getSonginfo().getAuthor());
        if (MusicService.musicBinder.getCurrentMusicBean() != null){
            if (holder.songTv.getText().toString().equals(MusicService.musicBinder.getCurrentMusicBean().getSonginfo().getTitle())){
                holder.songTv.setTextColor(context.getResources().getColor(R.color.main_title_layout));
                holder.singerTv.setTextColor(context.getResources().getColor(R.color.main_title_layout));
            } else {
                holder.songTv.setTextColor(Color.BLACK);
                holder.singerTv.setTextColor(Color.BLACK);
            }
        }
        return convertView;
    }

    class OwnLocalMusicViewHolder{
        private TextView songTv, singerTv;
        public OwnLocalMusicViewHolder(View view){
            songTv = (TextView) view.findViewById(R.id.local_music_lv_song);
            singerTv = (TextView) view.findViewById(R.id.local_music_lv_singer);
        }
    }
}
