package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/29.
 */
public class RecommendDetailSongerFragment extends AbsBaseFragment {

    public static RecommendDetailSongerFragment newInstance() {
        Bundle args = new Bundle();
        RecommendDetailSongerFragment fragment = new RecommendDetailSongerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend_songer_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
