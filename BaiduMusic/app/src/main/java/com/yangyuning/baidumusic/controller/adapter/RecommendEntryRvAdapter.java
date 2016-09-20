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
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐 轮播图下方三个图标RV 适配器
 */
public class RecommendEntryRvAdapter extends RecyclerView.Adapter<RecommendEntryRvAdapter.ViewHolder>{
    private Context context;
    private List<MusicRecommendBean.ResultBean.EntryBean.entryResultBean> datas;

    public RecommendEntryRvAdapter(Context context, List<MusicRecommendBean.ResultBean.EntryBean.entryResultBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_kinds_title_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTv.setText(datas.get(position).getTitle());
        Picasso.with(context).load(datas.get(position).getIcon()).into(holder.imgId);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgId;
        private TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            imgId = (ImageView) itemView.findViewById(R.id.item_recommend_entry_img);
            titleTv = (TextView) itemView.findViewById(R.id.item_recommend_entry_tv);
        }
    }
}
