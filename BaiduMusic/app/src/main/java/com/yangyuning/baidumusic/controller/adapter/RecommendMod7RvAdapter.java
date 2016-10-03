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
import java.util.jar.Pack200;

/**
 * Created by dllo on 16/9/20.
 * 推荐 专栏 RV 适配器
 */
public class RecommendMod7RvAdapter extends RecyclerView.Adapter<RecommendMod7RvAdapter.ViewHolder>{

    private Context context;
    private List<MusicRecommendBean.ResultBean.Mod7Bean.mod7ResultBean> datas;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 7 ;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 7;

    public RecommendMod7RvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicRecommendBean.ResultBean.Mod7Bean.mod7ResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recommend_mod7_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleTv.setText(datas.get(position).getTitle());
        holder.stateTv.setText(datas.get(position).getDesc());
        Glide.with(context).load(datas.get(position).getPic()).into(holder.imgId);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(height,width);
        holder.imgId.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? 4 : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgId;
        private TextView titleTv, stateTv;
        public ViewHolder(View itemView) {
            super(itemView);
            imgId = (ImageView) itemView.findViewById(R.id.mine_song_sheet_icon_img);
            titleTv = (TextView) itemView.findViewById(R.id.mine_song_sheet_name_tv);
            stateTv = (TextView) itemView.findViewById(R.id.mine_song_sheet_piece_tv);
        }
    }
}
