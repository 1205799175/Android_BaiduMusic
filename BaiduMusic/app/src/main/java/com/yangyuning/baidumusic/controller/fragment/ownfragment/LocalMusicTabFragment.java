package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.os.Bundle;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/13.
 * 本地音乐 二级页面中嵌套的Fragment
 */
public class LocalMusicTabFragment extends AbsBaseFragment {

    public static LocalMusicTabFragment newInstance() {
        
        Bundle args = new Bundle();
        LocalMusicTabFragment fragment = new LocalMusicTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own_local_music_tab;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initDatas() {
    }
}
