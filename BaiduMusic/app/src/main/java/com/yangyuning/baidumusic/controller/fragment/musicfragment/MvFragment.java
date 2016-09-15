package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicMvRvAdapter;
import com.yangyuning.baidumusic.controller.adapter.MusicSongRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicMvBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * MV Fragment
 */
public class MvFragment extends AbsBaseFragment {
    private RecyclerView rv;
    private MusicMvRvAdapter musicMvRvAdapter;

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
        musicMvRvAdapter = new MusicMvRvAdapter(context);
        rv.setAdapter(musicMvRvAdapter);
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_MV, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicMvBean musicMvBean = gson.fromJson(resultStr, MusicMvBean.class);
                List<MusicMvBean.ResultBean.MvListBean> datas = musicMvBean.getResult().getMv_list();
                musicMvRvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });

        rv.setLayoutManager(new GridLayoutManager(context, BaiduMusicValues.MV_RECYCLERVIEW_ROW_NUM));
    }
}
