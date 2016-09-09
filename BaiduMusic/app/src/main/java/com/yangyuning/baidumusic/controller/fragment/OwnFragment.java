package com.yangyuning.baidumusic.controller.fragment;

import android.widget.ListView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLvAdapter;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.model.bean.OwnLvBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class OwnFragment extends AbsBaseFragment {
    private ListView firstLv;
    private OwnLvAdapter ownLvAdapter;
    private List<OwnLvBean> datas;
    @Override
    protected int setLayout() {
        return R.layout.fragment_own;
    }

    @Override
    protected void initView() {
        firstLv = byView(R.id.own_first_lv);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        datas.add(new OwnLvBean(R.mipmap.ic_launcher, "本地音乐", "10首"));
        datas.add(new OwnLvBean(R.mipmap.ic_launcher, "最近播放", "10首"));
        datas.add(new OwnLvBean(R.mipmap.ic_launcher, "下载管理", "10首"));
        datas.add(new OwnLvBean(R.mipmap.ic_launcher, "我的K歌", "10首"));
        ownLvAdapter = new OwnLvAdapter(BaiduMusicApp.getContext(), datas);
        firstLv.setAdapter(ownLvAdapter);
    }
}
