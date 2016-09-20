package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐 今日推荐歌曲RV 适配器
 */
public class RecommendRecsongRvAdapter extends RecyclerView.Adapter<RecommendRecsongRvAdapter.ViewHolder>{

    private Context context;
    private List<MusicRecommendBean.ResultBean.RecsongBean.recsongResultBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 7 ;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 7;

    public RecommendRecsongRvAdapter(Context context, List<MusicRecommendBean.ResultBean.RecsongBean.recsongResultBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_today_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(datas.get(position).getPic_premium()).into(holder.imgId);
        holder.nameTv.setText(datas.get(position).getAuthor());
        holder.songTv.setText(datas.get(position).getTitle());

        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(height,width);
        holder.imgId.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? 3 : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgId;
        private TextView songTv, nameTv;
        public ViewHolder(View itemView) {
            super(itemView);
            imgId = (ImageView) itemView.findViewById(R.id.today_recommend_cirimg);
            songTv = (TextView) itemView.findViewById(R.id.today_recommend_title_tv);
            nameTv = (TextView) itemView.findViewById(R.id.today_recommend_singer_tv);
        }
    }
}
