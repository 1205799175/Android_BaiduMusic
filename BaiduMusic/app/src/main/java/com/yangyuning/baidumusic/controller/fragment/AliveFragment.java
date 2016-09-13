package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;

import com.yangyuning.baidumusic.R;

/**
 * Created by dllo on 16/9/9.
 * 直播Fragment
 */
public class AliveFragment extends AbsBaseFragment {
    private Context context;

    public static AliveFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AliveFragment fragment = new AliveFragment();
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
        return R.layout.fragment_alive;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
