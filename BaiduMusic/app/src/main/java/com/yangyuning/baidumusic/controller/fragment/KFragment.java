package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;

import com.yangyuning.baidumusic.R;

/**
 * Created by dllo on 16/9/9.
 * K歌Fragment
 */
public class KFragment extends AbsBaseFragment {
    private Context context;

    public static KFragment newInstance() {

        Bundle args = new Bundle();

        KFragment fragment = new KFragment();
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
        return R.layout.fragment_k;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
