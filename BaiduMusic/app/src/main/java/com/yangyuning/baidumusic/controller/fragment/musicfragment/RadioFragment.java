package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRadioRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicRadioBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 电台Fragment
 */
public class RadioFragment extends AbsBaseFragment {
    private RecyclerView rv;
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
        //初始化适配器, 绑定适配器
        musicRadioRvAdapter = new MusicRadioRvAdapter(context);
        //获取网络数据, 解析
        getNetDatas();
        rv.setAdapter(musicRadioRvAdapter);
        //设置布局管理器
        rv.setLayoutManager(new GridLayoutManager(context, 4));
    }

    //获取网络数据, 解析
    private void getNetDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_RADIO, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicRadioBean musicRadioBean = gson.fromJson(resultStr, MusicRadioBean.class);
                List<MusicRadioBean.ResultBean> datas = musicRadioBean.getResult();
                musicRadioRvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
