package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicMvRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicMvBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.List;

/**
 * Created by dllo on 16/9/16.
 * MV 最新最热Fragment
 */
public class MvNewAndHotFragment extends AbsBaseFragment {
    private RecyclerView rv;
    private MusicMvRvAdapter musicMvRvAdapter;

    public static MvNewAndHotFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(BaiduMusicValues.THE_NEWINSTANCE_URL_KEY, url);
        MvNewAndHotFragment fragment = new MvNewAndHotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mv_rv;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.music_mv_frame_rv);
    }

    @Override
    protected void initDatas() {
        //获得网络数据并解析
        getNetDatas();
        //初始化适配器, 绑定适配器
        musicMvRvAdapter = new MusicMvRvAdapter(context);
        rv.setAdapter(musicMvRvAdapter);
        //设置布局管理器
        rv.setLayoutManager(new GridLayoutManager(context, BaiduMusicValues.MV_RECYCLERVIEW_ROW_NUM));
    }

    //获得网络数据并解析
    private void getNetDatas() {
        //获得传来的网址
        Bundle bundle = getArguments();
        String mvUrl = bundle.getString(BaiduMusicValues.THE_NEWINSTANCE_URL_KEY);
        VolleyInstance.getInstance().startResult(mvUrl, new VolleyResult() {
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
    }
}
