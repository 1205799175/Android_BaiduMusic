package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.AliveRvTopBean;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.RankingDetailRvBean;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 * 排行 详情 RV 适配器
 */
public class RankingDetailRvAdapter extends RecyclerView.Adapter<RankingDetailRvAdapter.MyViewHolder> {

    private List<MusicBean> datas;
    private Context context;

    private int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 7;
    private int width = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.WIDTH) / 4;

    private OnRvItemClick<MusicBean> onRvItemClickListener;
    private OnRvItemClick<MusicBean> onRvMoreClickListener;

    public void setOnRvItemClickListener(OnRvItemClick<MusicBean> onRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener;
    }

    public void setOnRvMoreClickListener(OnRvItemClick<MusicBean> onRvMoreClickListener) {
        this.onRvMoreClickListener = onRvMoreClickListener;
    }

    public RankingDetailRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ranking_detail_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title.setText(datas.get(position).getSonginfo().getTitle());
        holder.author.setText(datas.get(position).getSonginfo().getAuthor());
        Glide.with(context).load(datas.get(position).getSonginfo().getPic_small()).override(width, height).into(holder.coverIv);

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRvItemClickListener != null){
                    int position = holder.getLayoutPosition();
                    MusicBean bean = datas.get(position);
                    onRvItemClickListener.onRvItemClickListener(position, bean);
                }
            }
        });
        holder.moreIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRvMoreClickListener != null){
                    int position = holder.getLayoutPosition();
                    MusicBean bean = datas.get(position);
                    onRvMoreClickListener.onRvItemClickListener(position, bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView coverIv, moreIv;
        TextView title, author;
        RelativeLayout item;
        public MyViewHolder(View itemView) {
            super(itemView);
            moreIv = (ImageView) itemView.findViewById(R.id.item_rank_detail_rv_more_iv);
            coverIv = (ImageView) itemView.findViewById(R.id.item_rank_detail_rv_cover_iv);
            title = (TextView) itemView.findViewById(R.id.item_rank_detail_rv_title);
            author = (TextView) itemView.findViewById(R.id.item_rank_detail_rv_author);
            item = (RelativeLayout) itemView.findViewById(R.id.item_rank_detail_rv);
        }
    }

}


