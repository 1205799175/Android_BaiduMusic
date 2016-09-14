package com.yangyuning.baidumusic.controller.fragment.alivefragment;

import android.os.Bundle;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 * 直播Fragment
 */
public class AliveFragment extends AbsBaseFragment {

    public static AliveFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AliveFragment fragment = new AliveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_alive;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
