package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.MyFavoriteSongBean;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * 我喜欢的单曲 LV 适配器
 */
public class FavoriteLvAdapter extends BaseAdapter {
    private Context context;
    private List<MyFavoriteSongBean> datas;

    private OnFavGetMoreListener getMoreListener;

    public void setGetMoreListener(OnFavGetMoreListener getMoreListener) {
        this.getMoreListener = getMoreListener;
    }

    public FavoriteLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<MyFavoriteSongBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_k_lv,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(datas.get(position).getTitle());
        holder.authorTv.setText(datas.get(position).getAuthor());
        holder.moreImg.setImageResource(R.mipmap.ic_list_more_normal);
        holder.moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMoreListener.onGetMore(position);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView titleTv, authorTv;
        ImageView moreImg;
        public ViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.item_k_name);
            authorTv = (TextView) view.findViewById(R.id.item_k_time);
            moreImg = (ImageView) view.findViewById(R.id.item_k_mike);
        }
    }

    public interface OnFavGetMoreListener{
        void onGetMore(int position);
    }
}
