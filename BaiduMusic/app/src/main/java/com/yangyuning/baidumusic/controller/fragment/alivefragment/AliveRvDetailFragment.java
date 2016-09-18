package com.yangyuning.baidumusic.controller.fragment.alivefragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/18.
 */
public class AliveRvDetailFragment extends AbsBaseFragment {
    private TextView titleBackTv;   //标题
    private VpAdapter vpAdapter;
    private List<Fragment> fragments;   //存Fragment的集合
    private TabLayout tb;
    private ViewPager vp;

    public static AliveRvDetailFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AliveRvDetailFragment fragment = new AliveRvDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_alive_rv_detail;
    }

    @Override
    protected void initView() {
        titleBackTv = byView(R.id.detail_title_back);
        tb = byView(R.id.alive_rv_detail_tb);
        vp = byView(R.id.alive_rv_detail_vp);
    }

    @Override
    protected void initDatas() {
        //设置标题
        titleBackTv.setText(R.string.tab_title_alive);
        fragments = new ArrayList<>();
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_ALL));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_WOMAN));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_GOODVOICE));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_NEW));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_BOOM));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_JOY));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_SISTER));
        fragments.add(AliveDetailTabFragment.newInstance(BaiduMusicValues.ALIVE_TOP_RV_DETAIL_RECOMMEND));
        //初始化适配器
        vpAdapter = new VpAdapter(getChildFragmentManager(), fragments);
        vp.setAdapter(vpAdapter);
        tb.setupWithViewPager(vp);

        //设置标题
        tb.getTabAt(0).setText(R.string.alive_top_rv_tab_title_all);
        tb.getTabAt(1).setText(R.string.alive_top_rv_tab_title_woman);
        tb.getTabAt(2).setText(R.string.alive_top_rv_tab_title_goodVoice);
        tb.getTabAt(3).setText(R.string.alive_top_rv_tab_title_new);
        tb.getTabAt(4).setText(R.string.alive_top_rv_tab_title_boom);
        tb.getTabAt(5).setText(R.string.alive_top_rv_tab_title_joy);
        tb.getTabAt(6).setText(R.string.alive_top_rv_tab_title_sister);
        tb.getTabAt(7).setText(R.string.alive_top_rv_tab_title_recommend);
        tb.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
