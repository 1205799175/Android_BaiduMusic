package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.KLvBean;
import com.yangyuning.baidumusic.model.bean.OwnLvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/13.
 */
public class KLvAdapter extends BaseAdapter {
    private Context context;
    private List<KLvBean> datas;

    public KLvAdapter(Context context, List<KLvBean> datas) {
        this.context = context;
        this.datas = datas;
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
        viewHolder.nameTv.setText(datas.get(position).getName());
        viewHolder.timeTv.setText(datas.get(position).getTiem());
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
