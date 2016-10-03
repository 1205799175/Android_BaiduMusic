package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;

import java.util.List;

/**
 * Created by dllo on 16/9/30.
 * 推荐 歌手 详情页 ListView
 */
public class RecommendSongerLvAdapter extends BaseAdapter{
    private List<String> datas;
    private Context context;

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public RecommendSongerLvAdapter(Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_songer_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView titleTv;

        public ViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.recommend_songer_lv_title);
        }
    }
}
