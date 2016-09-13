package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;

import com.yangyuning.baidumusic.R;

/**
 * Created by dllo on 16/9/13.
 * 本地歌曲详情页
 */
public class LocalMusicDetailsFragment extends AbsBaseFragment{
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

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

    }

    @Override
    protected void initDatas() {

    }
}
