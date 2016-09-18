package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.AliveRvBottomBean;

import java.util.List;

/**
 * Created by dllo on 16/9/15.
 * 直播 下方 RecyClerView适配器
 */
public class AliveRvBottomAdapter extends RecyclerView.Adapter<AliveRvBottomAdapter.AliveBottomRvViewHolder>{
    private Context context;
    private List<AliveRvBottomBean.DataBean.mDataBean> datas;

    public AliveRvBottomAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<AliveRvBottomBean.DataBean.mDataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public AliveBottomRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_song_rv, parent, false);
        AliveBottomRvViewHolder aliveBottomRvViewHolder = new AliveBottomRvViewHolder(view);
        return aliveBottomRvViewHolder;
    }

    @Override
    public void onBindViewHolder(AliveBottomRvViewHolder holder, int position) {
        holder.earImg.setImageResource(R.mipmap.img_live_play_people);
        holder.playImg.setImageResource(R.mipmap.ic_live_play_press);
        holder.titleTv.setText(datas.get(position).getNickname());
        holder.listenum.setText(datas.get(position).getUsercount() + "");
        Picasso.with(context).load(datas.get(position).getLiveimg()).resize(380, 280).into(holder.imgId);

    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class AliveBottomRvViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv, listenum;
        private ImageView imgId;
        private ImageView earImg, playImg;

        public AliveBottomRvViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_music_song_title_tv);
            imgId = (ImageView) itemView.findViewById(R.id.item_music_song_img);
            listenum = (TextView) itemView.findViewById(R.id.item_music_song_listenum);
            earImg = (ImageView) itemView.findViewById(R.id.item_music_song_ear);
            playImg = (ImageView) itemView.findViewById(R.id.item_music_song_play);
        }
    }
}
