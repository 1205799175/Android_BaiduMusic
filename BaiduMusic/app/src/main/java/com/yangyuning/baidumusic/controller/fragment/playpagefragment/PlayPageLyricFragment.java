package com.yangyuning.baidumusic.controller.fragment.playpagefragment;

import android.os.Bundle;
import android.view.View;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/21.
 * 播放音乐 歌词界面
 */
public class PlayPageLyricFragment extends AbsBaseFragment implements View.OnClickListener {

    public static PlayPageLyricFragment newInstance() {
        Bundle args = new Bundle();
        PlayPageLyricFragment fragment = new PlayPageLyricFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_playpage_lyric;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initDatas() {
    }

    @Override
    public void onClick(View v) {
    }

}
