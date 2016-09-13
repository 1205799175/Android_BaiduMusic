package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 乐库 歌单 RecyclerView适配器
 */
public class MusicSongRvAdapter extends RecyclerView.Adapter<MusicSongRvAdapter.SongViewHolder>{
    private Context context;
    private List<MusicSongBean> datas;

    public MusicSongRvAdapter(Context context, List<MusicSongBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_song_rv, parent, false);
        SongViewHolder songViewHolder = new SongViewHolder(view);
        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.titleTv.setText(datas.get(position).getTitle());
        holder.areaTv.setText(datas.get(position).getArea());
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class SongViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv, areaTv;

        public SongViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.music_song_title_tv);
            areaTv = (TextView) itemView.findViewById(R.id.music_song_area_tv);
        }
    }
}
