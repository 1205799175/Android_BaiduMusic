package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐 场景电台 RV 适配器
 */
public class RecommendScenRcAdapter extends RecyclerView.Adapter<RecommendScenRcAdapter.ViewHolder> {
    private Context context;
    private List<MusicRecommendBean.ResultBean.SceneBean.ScenResultBean.ActionBean> datas;

    public RecommendScenRcAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRecommendBean.ResultBean.SceneBean.ScenResultBean.ActionBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_scen_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.bgImg.setImageResource(R.mipmap.img_recommend_lebo_orange);
                break;
            case 1:
                holder.bgImg.setImageResource(R.mipmap.img_recommend_lebo_green);
                break;
            case 2:
                holder.bgImg.setImageResource(R.mipmap.img_recommend_lebo_cyan);
                break;
            case 3:
                holder.bgImg.setImageResource(R.mipmap.img_recommend_lebo_blue);
                break;
        }
        Glide.with(context).load(datas.get(position).getIcon_android()).override(100, 100).into(holder.iconImg);
        holder.titleTv.setText(datas.get(position).getScene_name());

    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? 4 : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private ImageView iconImg, bgImg;

        public ViewHolder(View itemView) {
            super(itemView);
            bgImg = (ImageView) itemView.findViewById(R.id.item_recommend_scen_bg_img);
            iconImg = (ImageView) itemView.findViewById(R.id.item_recommend_scen_icon_img);
            titleTv = (TextView) itemView.findViewById(R.id.item_recommend_scen_name_tv);
        }
    }
}
