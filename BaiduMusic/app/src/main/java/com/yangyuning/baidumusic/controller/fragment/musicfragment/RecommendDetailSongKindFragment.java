package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/30.
 * 推荐 歌曲分类
 */
public class RecommendDetailSongKindFragment extends AbsBaseFragment {

    public static RecommendDetailSongKindFragment newInstance() {
        Bundle args = new Bundle();
        RecommendDetailSongKindFragment fragment = new RecommendDetailSongKindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend_song_kind_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
