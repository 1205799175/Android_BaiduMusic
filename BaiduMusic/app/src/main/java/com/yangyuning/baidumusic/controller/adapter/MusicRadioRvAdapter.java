package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRadioBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;

import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 乐库 电台 RecyclerView适配器
 */
public class MusicRadioRvAdapter extends RecyclerView.Adapter<MusicRadioRvAdapter.RadioViewHolder>{
    private Context context;
    private List<MusicRadioBean> datas;

    public MusicRadioRvAdapter(Context context, List<MusicRadioBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_radio_rv, parent, false);
        RadioViewHolder radioViewHolder = new RadioViewHolder(view);
        return radioViewHolder;
    }

    @Override
    public void onBindViewHolder(RadioViewHolder holder, int position) {
        holder.titleTv.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class RadioViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv;
        private ImageView imgId;

        public RadioViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.music_radio_tv);
            imgId = (ImageView) itemView.findViewById(R.id.music_radio_img);
        }
    }
}
