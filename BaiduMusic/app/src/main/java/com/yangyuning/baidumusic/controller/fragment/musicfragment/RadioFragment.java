package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRadioRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicRadioBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 电台Fragment
 */
public class RadioFragment extends AbsBaseFragment {
    private RecyclerView rv;
    private List<MusicRadioBean> datas;
    private MusicRadioRvAdapter musicRadioRvAdapter;

    public static RadioFragment newInstance() {

        Bundle args = new Bundle();

        RadioFragment fragment = new RadioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_radio;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.music_radio_rv);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        //假数据
        for (int i = 0; i < 12; i++) {
            datas.add(new MusicRadioBean("电台", R.mipmap.ic_launcher));
        }
        musicRadioRvAdapter = new MusicRadioRvAdapter(context, datas);
        rv.setAdapter(musicRadioRvAdapter);
        rv.setLayoutManager(new GridLayoutManager(context, 4));
    }
}
