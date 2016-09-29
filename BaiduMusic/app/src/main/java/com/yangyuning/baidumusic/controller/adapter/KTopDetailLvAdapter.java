package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.KLvBean;
import com.yangyuning.baidumusic.model.bean.KTopDetailLvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 * K歌 KTV 华语金曲, 欧美经典 详情页
 */
public class KTopDetailLvAdapter extends BaseAdapter {
    private Context context;
    private List<KTopDetailLvBean.ResultBean.ItemsBean> datas;

    public KTopDetailLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<KTopDetailLvBean.ResultBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_k_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameTv.setText(datas.get(position).getSong_title());
        viewHolder.timeTv.setText(datas.get(position).getArtist_name());
        return convertView;
    }

    class ViewHolder {
        TextView nameTv, timeTv;

        public ViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_k_name);
            timeTv = (TextView) view.findViewById(R.id.item_k_time);
        }
    }
}
