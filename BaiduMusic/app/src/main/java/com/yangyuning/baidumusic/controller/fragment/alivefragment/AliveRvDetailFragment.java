package com.yangyuning.baidumusic.controller.fragment.alivefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/18.
 * 直播上部Rv二级界面
 */
public class AliveRvDetailFragment extends AbsBaseFragment {
    private TextView titleBackTv;   //标题
    private ImageView searchImg, scanImg, sortImg;    //搜索图标, 扫描, 排序图标
    private VpAdapter vpAdapter;
    private List<Fragment> fragments;   //存Fragment的集合
    private TabLayout tb;
    private ViewPager vp;

    public ViewPager getVp() {
        return vp;
    }

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
        searchImg = byView(R.id.local_music_search_img);
        sortImg = byView(R.id.local_music_sort_img);
        scanImg = byView(R.id.local_music_scan_img);
        titleBackTv = byView(R.id.detail_title_back);
        tb = byView(R.id.alive_rv_detail_tb);
        vp = byView(R.id.alive_rv_detail_vp);
    }

    @Override
    protected void initDatas() {
        //设置标题
        titleBackTv.setText(R.string.tab_title_alive);
        //撤销不需要的图标
        searchImg.setVisibility(View.GONE);
        scanImg.setVisibility(View.GONE);
        sortImg.setVisibility(View.GONE);

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

        //点击事件 点击标题返回
        addBackListener();

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

    //点击事件 点击标题返回
    private void addBackListener() {
        titleBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_ALIVE_RV);
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
                context.sendBroadcast(intent);
            }
        });
    }
}
