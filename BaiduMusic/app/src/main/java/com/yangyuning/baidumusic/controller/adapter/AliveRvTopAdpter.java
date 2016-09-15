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
import com.yangyuning.baidumusic.model.bean.AliveRvTopBean;
import com.yangyuning.baidumusic.model.bean.MusicRadioBean;

import java.util.List;

/**
 * Created by dllo on 16/9/15.
 * 直播 上部 RecyclerView适配器
 */
public class AliveRvTopAdpter extends RecyclerView.Adapter<AliveRvTopAdpter.AliveTopRvViewHolder> {
    private Context context;
    private List<AliveRvTopBean.DataBean> datas;

    public AliveRvTopAdpter(Context context) {
        this.context = context;
    }

    public void setDatas(List<AliveRvTopBean.DataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public AliveTopRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alive_top_rv, parent, false);
        AliveTopRvViewHolder aliveTopRvViewHolder = new AliveTopRvViewHolder(view);
        return aliveTopRvViewHolder;
    }

    @Override
    public void onBindViewHolder(AliveTopRvViewHolder holder, int position) {
        holder.titleTv.setText(datas.get(position).getCategory_name());
        Picasso.with(context).load(datas.get(position).getImg_url()).resize(120, 120).into(holder.imgId);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class AliveTopRvViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private ImageView imgId;

        public AliveTopRvViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_alive_top_title_tv);
            imgId = (ImageView) itemView.findViewById(R.id.item_alive_top_img);
        }
    }
}
