package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * 本地歌曲 二级页面
 */
public class LocalMusicDetailsFragment extends AbsBaseFragment {
    private ViewPager vp;
    private TabLayout tb;
    private List<Fragment> datas;
    private VpAdapter vpAdapter;

    private TextView localMusicBack;

    public static LocalMusicDetailsFragment newInstance() {
        Bundle args = new Bundle();
        LocalMusicDetailsFragment fragment = new LocalMusicDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own_local_music;
    }

    @Override
    protected void initView() {
        vp = byView(R.id.own_local_music_vp);
        tb = byView(R.id.own_local_music_tb);
        localMusicBack = byView(R.id.detail_title_back);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        datas.add(LocalMusicTabFragment.newInstance());
        datas.add(LocalMusicTabFragment.newInstance());
        datas.add(LocalMusicTabFragment.newInstance());
        datas.add(LocalMusicTabFragment.newInstance());

        vpAdapter = new VpAdapter(getChildFragmentManager(), datas);
        vp.setAdapter(vpAdapter);
        tb.setupWithViewPager(vp);

        //点击事件 点击标题返回
        addBackListener();
        //点击返回

        tb.getTabAt(0).setText(getString(R.string.local_tab_song));
        tb.getTabAt(1).setText(getString(R.string.local_tab_file));
        tb.getTabAt(2).setText(getString(R.string.local_tab_songer));
        tb.getTabAt(3).setText(getString(R.string.local_tab_album));
    }

    //点击事件 点击标题返回
    private void addBackListener() {
        localMusicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

}
