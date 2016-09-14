package com.yangyuning.baidumusic.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveFragment;
import com.yangyuning.baidumusic.controller.fragment.kfragment.KFragment;
import com.yangyuning.baidumusic.controller.fragment.musicfragment.MusicFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.OwnFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 占位Fragment
 */
public class MainFragment extends AbsBaseFragment {
    private TabLayout frameTb;
    private ViewPager frameVp;

    private List<Fragment> datas;
    private VpAdapter vpAdapter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_frame_layout;
    }

    @Override
    protected void initView() {
        frameTb = byView(R.id.frame_tb);
        frameVp = byView(R.id.frame_vp);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        datas.add(OwnFragment.newInstance());
        datas.add(MusicFragment.newInstance());
        datas.add(KFragment.newInstance());
        datas.add(AliveFragment.newInstance());
        vpAdapter = new VpAdapter(getChildFragmentManager(), datas);
        frameVp.setAdapter(vpAdapter);
        frameTb.setupWithViewPager(frameVp);

        frameTb.getTabAt(0).setText(getString(R.string.tab_title_own));
        frameTb.getTabAt(1).setText(getString(R.string.tab_title_music));
        frameTb.getTabAt(2).setText(getString(R.string.tab_title_k));
        frameTb.getTabAt(3).setText(getString(R.string.tab_title_alive));
        frameTb.setTabTextColors(Color.argb(255, 207, 207, 207), Color.WHITE);
    }
}
