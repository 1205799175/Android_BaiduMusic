package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 * 乐库 推荐 Fragment
 */
public class RecommendFragment extends AbsBaseFragment {
    public static RecommendFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
