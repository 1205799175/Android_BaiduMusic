package com.yangyuning.baidumusic.controller.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.MainFragment;

public class MainActivity extends AbsBaseActivity {
    private FrameLayout frameLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        frameLayout = byView(R.id.main_frame_layout);
    }

    @Override
    protected void initDatas() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout, MainFragment.newInstance());
        ft.commit();
    }
}
