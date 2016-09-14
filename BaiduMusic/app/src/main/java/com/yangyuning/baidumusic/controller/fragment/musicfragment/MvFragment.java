package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicSongRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * MV Fragment
 */
public class MvFragment extends AbsBaseFragment {
    private RecyclerView rv;
    private List<MusicSongBean> datas;
    private MusicSongRvAdapter musicSongRvAdapter;

    public static MvFragment newInstance() {

        Bundle args = new Bundle();

        MvFragment fragment = new MvFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mv;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.music_mv_rv);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        //假数据
//        for (int i = 0; i < 20; i++) {
//            datas.add(new MusicSongBean("title", "area"));
//        }
//        musicSongRvAdapter = new MusicSongRvAdapter(context, datas);
//        rv.setAdapter(musicSongRvAdapter);

//        rv.setLayoutManager(new GridLayoutManager(context, BaiduMusicValues.MV_RECYCLERVIEW_ROW_NUM));
    }
}
