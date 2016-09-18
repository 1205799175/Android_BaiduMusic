package com.yangyuning.baidumusic.utils.interfaces;

/**
 * Created by dllo on 16/9/18.
 * RecyclerView点击事件接口
 */
public interface OnRvItemClick<T> {
    void onRvItemClickListener(int position, T t);
}
