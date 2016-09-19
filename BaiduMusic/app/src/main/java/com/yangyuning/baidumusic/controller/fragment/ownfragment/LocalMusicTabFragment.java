package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLocalMusicLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.OwnLvBottomAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * 本地音乐 二级页面中嵌套的Fragment
 */
public class LocalMusicTabFragment extends AbsBaseFragment {

    private ListView listView;
    private OwnLocalMusicLvAdapter ownLocalMusicLvAdapter;
    private List<OwnLocalMusicLvBean> datas;
    public static LocalMusicTabFragment newInstance() {
        
        Bundle args = new Bundle();
        LocalMusicTabFragment fragment = new LocalMusicTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own_local_music_tab;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.local_music_lv);
    }

    @Override
    protected void initDatas() {
        //假数据
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_local_music_lv, null);
        listView.addHeaderView(view);
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(new OwnLocalMusicLvBean("爱情转移", "陈奕迅"));
        }
        //初始化适配器
        ownLocalMusicLvAdapter = new OwnLocalMusicLvAdapter(datas, context);
        listView.setAdapter(ownLocalMusicLvAdapter);
    }
}
