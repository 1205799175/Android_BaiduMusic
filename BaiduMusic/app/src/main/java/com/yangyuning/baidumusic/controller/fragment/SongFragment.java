package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicSongRvAdapter;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 歌单 Fragment
 */
public class SongFragment extends AbsBaseFragment {
    private Context context;

    private RecyclerView rv;
    private List<MusicSongBean> datas;
    private MusicSongRvAdapter musicSongRvAdapter;

    public static SongFragment newInstance() {

        Bundle args = new Bundle();

        SongFragment fragment = new SongFragment();
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
        return R.layout.fragment_song;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.music_song_rv);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            datas.add(new MusicSongBean("title", "area"));
        }
        musicSongRvAdapter = new MusicSongRvAdapter(context, datas);
        rv.setAdapter(musicSongRvAdapter);

        rv.setLayoutManager(new GridLayoutManager(context, 2));

    }
}
