package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐 热销专辑 RV 适配器
 */
public class RecommendMix22RvAdapter extends RecyclerView.Adapter<RecommendMix22RvAdapter.ViewHolder>{

    private Context context;
    private List<MusicRecommendBean.ResultBean.Mix22Bean.mix22ResultBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;

    public RecommendMix22RvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRecommendBean.ResultBean.Mix22Bean.mix22ResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_title_with_singer_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getPic()).resize(width, height).into(holder.imgId);
        holder.titleTv.setText(datas.get(position).getTitle());
        holder.singetTv.setText(datas.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? 3 : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgId;
        private TextView titleTv, singetTv;

        public ViewHolder(View itemView) {
            super(itemView);
            imgId = (ImageView) itemView.findViewById(R.id.item_song_layout_with_singer_img);
            titleTv = (TextView) itemView.findViewById(R.id.item_song_with_singer_title_tv);
            singetTv = (TextView) itemView.findViewById(R.id.item_song_with_singer_singer_tv);
        }
    }
}

