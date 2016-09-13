package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;

import com.yangyuning.baidumusic.R;

/**
 * Created by dllo on 16/9/9.
 * 乐库 推荐 Fragment
 */
public class RecommendFragment extends AbsBaseFragment {
    private Context context;

    public static RecommendFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
