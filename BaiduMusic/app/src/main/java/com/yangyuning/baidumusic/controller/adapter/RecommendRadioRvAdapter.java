package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐 乐播节目 RV 适配器
 */
public class RecommendRadioRvAdapter extends RecyclerView.Adapter<RecommendRadioRvAdapter.ViewHolder>{

    private Context context;
    private List<MusicRecommendBean.ResultBean.RadioBean.radioResultBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;

    public RecommendRadioRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRecommendBean.ResultBean.RadioBean.radioResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_icon_title_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(datas.get(position).getPic()).override(width, height).into(holder.imgId);
        holder.titleTv.setText(datas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? 6 : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTv;
        private ImageView imgId;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_song_with_icon_tv);
            imgId = (ImageView) itemView.findViewById(R.id.item_song_with_icon_img);
        }
    }
}

