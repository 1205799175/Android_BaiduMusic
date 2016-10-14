package com.yangyuning.baidumusic.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 * 播放PopWindow 线性布局的适配器
 */
public class PlayPageLinearPagerAdapter extends PagerAdapter {
    private Context context;
    private List<LinearLayout> datas;

    public PlayPageLinearPagerAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<LinearLayout> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout ll = datas.get(position);
        container.addView(ll);
        return ll;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }
}
