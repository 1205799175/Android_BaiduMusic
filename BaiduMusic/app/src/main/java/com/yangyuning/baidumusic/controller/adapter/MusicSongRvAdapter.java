package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 乐库 歌单 RecyclerView适配器
 */
public class MusicSongRvAdapter extends RecyclerView.Adapter<MusicSongRvAdapter.SongViewHolder> {
    private Context context;
    private List<MusicSongBean.ContentBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 2;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 2;

    public MusicSongRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicSongBean.ContentBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
        holder.styleTv.setText(datas.get(position).getTag());
        holder.listenum.setText(datas.get(position).getListenum());
        Picasso.with(context).load(datas.get(position).getPic_300()).into(holder.imgId);

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(height,width);
        holder.imgId.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv, styleTv, listenum;
        private ImageView imgId;

        public SongViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_music_song_title_tv);
            styleTv = (TextView) itemView.findViewById(R.id.item_music_song_style_tv);
            imgId = (ImageView) itemView.findViewById(R.id.item_music_song_img);
            listenum = (TextView) itemView.findViewById(R.id.item_music_song_listenum);
        }
    }
}
