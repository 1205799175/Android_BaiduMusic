package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.DownDetailLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.DownLoadBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/10.
 * 下载管理 详情页 正在下载, 已下载Fragment
 */
public class DownLoadStateFragment extends AbsBaseFragment {
    private ListView listView;
    private DownDetailLvAdapter adapter;
    private List<DownLoadBean> datas;

    public static DownLoadStateFragment newInstance() {
        Bundle args = new Bundle();
        DownLoadStateFragment fragment = new DownLoadStateFragment();
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
        datas = new ArrayList<>();
        datas = LiteOrmInstance.getLiteOrmInstance().queryData(DownLoadBean.class);
        adapter = new DownDetailLvAdapter(datas, context);
        listView.setAdapter(adapter);

        initListView();
    }

    private void initListView() {
        //添加头布局
        View headerView = LayoutInflater.from(context).inflate(R.layout.item_header_local_music_lv, null);
        listView.setHeaderDividersEnabled(false);
        listView.addHeaderView(headerView);
    }
}
