package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.DownLoadBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/10.
 * 下载管理Fragment
 */
public class DownLoadManageFragment extends AbsBaseFragment {
    private TextView backTv;
    private TabLayout tb;
    private ViewPager vp;
    private List<Fragment> fragments;
    private VpAdapter vpAdapter;

    public static DownLoadManageFragment newInstance() {
        Bundle args = new Bundle();
        DownLoadManageFragment fragment = new DownLoadManageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_down_manage;
    }

    @Override
    protected void initView() {
        tb = byView(R.id.down_manage_tb);
        vp = byView(R.id.down_manage_vp);
        backTv = byView(R.id.detail_title_back);
    }

    @Override
    protected void initDatas() {
        fragments = new ArrayList<>();
        fragments.add(DownLoadStateFragment.newInstance());
        fragments.add(DownLoadStateFragment.newInstance());
        vpAdapter = new VpAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(vpAdapter);
        tb.setupWithViewPager(vp);

        int musicNum = LiteOrmInstance.getLiteOrmInstance().queryData(DownLoadBean.class).size();
        tb.getTabAt(BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO).setText(BaiduMusicValues.DOWN_LOAD_HAVE_LEFT + musicNum + BaiduMusicValues.DOWN_LOAD_HAVE_RIGHT);
        tb.getTabAt(BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE).setText(BaiduMusicValues.DOWN_LOAD_IS);
        initListener();
    }

    private void initListener() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }
}
