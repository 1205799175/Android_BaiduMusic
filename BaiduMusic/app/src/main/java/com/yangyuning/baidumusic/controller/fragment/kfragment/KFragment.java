package com.yangyuning.baidumusic.controller.fragment.kfragment;

import android.os.Bundle;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.KLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.KLvBean;
import com.yangyuning.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * K歌Fragment
 */
public class KFragment extends AbsBaseFragment {
    private List<KLvBean> datas;
    private KLvAdapter kLvAdapter;
    private MyListView myListView;

    public static KFragment newInstance() {

        Bundle args = new Bundle();

        KFragment fragment = new KFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_k;
    }

    @Override
    protected void initView() {
        myListView = byView(R.id.k_lv);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        //假数据
        for (int i = 0; i < 10; i++) {
            datas.add(new KLvBean("歌手-歌名", "1000人唱过"));
        }
        kLvAdapter = new KLvAdapter(context, datas);
        myListView.setAdapter(kLvAdapter);
    }
}
