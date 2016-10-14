package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.DownLoadBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;

import java.util.List;

/**
 * Created by dllo on 16/10/10.
 * 下载管理详情 LV 适配器
 */
public class DownDetailLvAdapter extends BaseAdapter {
    private List<DownLoadBean> datas;
    private Context context;

    public DownDetailLvAdapter(List<DownLoadBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas != null && datas.size() > 0 ? datas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        OwnLocalMusicViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_own_local_music_lv, parent, false);
            holder = new OwnLocalMusicViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OwnLocalMusicViewHolder) convertView.getTag();
        }
        holder.songTv.setText(datas.get(position).getTitle());
        holder.singerTv.setText(datas.get(position).getAuthor());
        holder.moreImg.setImageResource(R.mipmap.ic_list_normal);
        holder.moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiteOrmInstance.getLiteOrmInstance().deleteBySongIdDown(datas.get(position).getSongId());
                datas.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class OwnLocalMusicViewHolder{
        private TextView songTv, singerTv;
        private ImageView moreImg;
        public OwnLocalMusicViewHolder(View view){
            songTv = (TextView) view.findViewById(R.id.local_music_lv_song);
            singerTv = (TextView) view.findViewById(R.id.local_music_lv_singer);
            moreImg = (ImageView) view.findViewById(R.id.local_music_lv_more);
        }
    }
}

