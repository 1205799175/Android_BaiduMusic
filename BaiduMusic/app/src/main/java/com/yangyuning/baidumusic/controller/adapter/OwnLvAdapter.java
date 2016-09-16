package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.OwnLvBean;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 我的Fragment上端ListView适配器
 */
public class OwnLvAdapter extends BaseAdapter {
    private Context context;
    private List<OwnLvBean> datas;

    public OwnLvAdapter(Context context, List<OwnLvBean> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_own_top_lv, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameTv.setText(datas.get(position).getName());
        viewHolder.numberTv.setText(datas.get(position).getNumber());
        viewHolder.img.setImageResource(datas.get(position).getImgId());
        viewHolder.arrowImg.setImageResource(datas.get(position).getArrowImgId());
        return convertView;
    }

    class ViewHolder {
        TextView nameTv, numberTv;
        ImageView img, arrowImg;

        public ViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.item_own_name_tv);
            numberTv = (TextView) view.findViewById(R.id.item_own_top_number_tv);
            img = (ImageView) view.findViewById(R.id.item_own_img);
            arrowImg = (ImageView) view.findViewById(R.id.item_own_arrow);
        }
    }
}