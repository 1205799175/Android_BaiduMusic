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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 推荐 歌单推荐 RV 适配器
 */
public class RecommendDiyRvAdapter extends RecyclerView.Adapter<RecommendDiyRvAdapter.ViewHodler>{

    private Context context;
    private List<MusicRecommendBean.ResultBean.DiyBean.diyResultBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 3 - 40;

    public RecommendDiyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRecommendBean.ResultBean.DiyBean.diyResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_img_title_rv, parent, false);
        ViewHodler viewHolder = new ViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        if (datas.get(position).getPic().equals("")){

        }else {
            Glide.with(context).load(datas.get(position).getPic()).into(holder.imagView);
        }
        holder.countTv.setText(datas.get(position).getListenum() + "");
        holder.songTv.setText(datas.get(position).getTitle());
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(height,width);
        holder.rl.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView imagView;
        private TextView songTv, countTv;
        private RelativeLayout rl;
        public ViewHodler(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.item_count_icon_rl);
            imagView = (ImageView) itemView.findViewById(R.id.item_song_bg_img);
            songTv = (TextView) itemView.findViewById(R.id.item_song_disc_tv);
            countTv = (TextView) itemView.findViewById(R.id.item_song_count_tv);
        }
    }
}
