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

import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.RankingDetailRvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 * 排行 详情 RV 适配器
 */
public class RankingDetailRvAdapter extends RecyclerView.Adapter<RankingDetailRvAdapter.MyViewHolder> {

    private List<RankingDetailRvBean.SongListBean> datas;
    private Context context;
//    private OnRankDetailRvListener onRankDetailRvListener;
//    private OnGetMoreListener onGetMoreListener;
//
//    public void setOnGetMoreListener(OnGetMoreListener onGetMoreListener) {
//        this.onGetMoreListener = onGetMoreListener;
//    }
//
//    public void setOnRankDetailRvListener(OnRankDetailRvListener onRankDetailRvListener) {
//        this.onRankDetailRvListener = onRankDetailRvListener;
//    }

    public RankingDetailRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<RankingDetailRvBean.SongListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ranking_detail_rv, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(datas.get(position).getTitle());
        holder.author.setText(datas.get(position).getAuthor());
        Picasso.with(context).load(datas.get(position).getPic_small()).into(holder.coverIv);
//        holder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onRankDetailRvListener.onItemClick(position);
//            }
//        });
//        holder.moreIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onGetMoreListener.OnMoreClick(position);
//            }
//        });
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

//    interface OnRankDetailRvListener{
//        void onItemClick(int position);
//    }
//
//    interface OnGetMoreListener{
//        void OnMoreClick(int position);
//    }
}


