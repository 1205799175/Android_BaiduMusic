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
import com.yangyuning.baidumusic.model.bean.MusicMvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/15.
 * 乐库 MV RecyclerView适配器
 */
public class MusicMvRvAdapter extends RecyclerView.Adapter<MusicMvRvAdapter.MvViewHodler>{
    private Context context;
    private List<MusicMvBean.ResultBean.MvListBean> datas;

    public MusicMvRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicMvBean.ResultBean.MvListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MvViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music_mv_rv, parent, false);
        MvViewHodler mvViewHodler = new MvViewHodler(view);
        return mvViewHodler;
    }

    @Override
    public void onBindViewHolder(MvViewHodler holder, int position) {
        Picasso.with(context).load(datas.get(position).getThumbnail2()).resize(360, 200).into(holder.imgId);
        holder.titleTv.setText(datas.get(position).getTitle());
        holder.artistTv.setText(datas.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MvViewHodler extends RecyclerView.ViewHolder{
        private TextView titleTv, artistTv;
        private ImageView imgId;

        public MvViewHodler(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_mv_title_tv);
            imgId = (ImageView) itemView.findViewById(R.id.item_mv_title_img);
            artistTv = (TextView) itemView.findViewById(R.id.item_mv_artist_tv);
        }
    }
}
