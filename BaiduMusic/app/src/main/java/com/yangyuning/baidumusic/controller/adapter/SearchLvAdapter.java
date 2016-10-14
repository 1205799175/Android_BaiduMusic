package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.model.bean.SearchBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 搜索 LV 实体类
 */
public class SearchLvAdapter extends BaseAdapter {
    private Context context;
    private SearchBean.ResultBean datas;

    public SearchLvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SearchBean.ResultBean datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.getSong_info().getSong_list().size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.getSong_info().getSong_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_song_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(datas.getSong_info().getSong_list().get(position).getTitle());
        holder.authorTv.setText(datas.getSong_info().getSong_list().get(position).getAuthor());

        return convertView;
    }

    class ViewHolder{
        TextView titleTv, descTv, authorTv;
        ImageView detailImg;
        public ViewHolder(View view) {
            titleTv = (TextView) view.findViewById(R.id.search_song_item_title);
            descTv = (TextView) view.findViewById(R.id.search_song_item_desc);
            authorTv = (TextView) view.findViewById(R.id.search_song_item_author);
            detailImg = (ImageView) view.findViewById(R.id.search_song_item_detail);
        }
    }

}
