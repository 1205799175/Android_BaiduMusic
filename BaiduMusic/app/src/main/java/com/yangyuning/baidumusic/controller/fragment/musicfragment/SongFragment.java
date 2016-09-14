package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicSongRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 歌单 Fragment
 */
public class SongFragment extends AbsBaseFragment {
    private RecyclerView rv;
    private MusicSongRvAdapter musicSongRvAdapter;



    public static SongFragment newInstance() {

        Bundle args = new Bundle();

        SongFragment fragment = new SongFragment();
        fragment.setArguments(args);
        return fragment;
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
        //初始化适配器
        musicSongRvAdapter = new MusicSongRvAdapter(context);
        //绑定适配器
        rv.setAdapter(musicSongRvAdapter);
        //获取,解析数据
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_SONG, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicSongBean musicSongBean = gson.fromJson(resultStr, MusicSongBean.class);
                List<MusicSongBean.ContentBean> datas = musicSongBean.getContent();
                musicSongRvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
        //设置布局管理器
        rv.setLayoutManager(new GridLayoutManager(context, BaiduMusicValues.MV_RECYCLERVIEW_ROW_NUM));

    }
}
