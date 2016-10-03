package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.List;

/**
 * Created by dllo on 16/9/30.
 */
public class SongDetailRvAdapter extends RecyclerView.Adapter<SongDetailRvAdapter.ViewHolder> {

    private Context context;
    private List<MusicBean> datas;
    private OnRvItemClick<MusicBean> onRvItemClickListener;

    public void setOnRvItemClickListener(OnRvItemClick<MusicBean> onRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener;
    }

    public SongDetailRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MusicBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song_detail_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.titleTv.setText(datas.get(position).getSonginfo().getTitle());
        holder.artistTv.setText(datas.get(position).getSonginfo().getAuthor());
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
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView artistTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_songlist_title_tv);
            artistTv = (TextView) itemView.findViewById(R.id.item_songlist_artist_tv);
        }


    }


}

