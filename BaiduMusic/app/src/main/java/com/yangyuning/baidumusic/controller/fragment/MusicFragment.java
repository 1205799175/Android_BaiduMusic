package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库Fragment
 */
public class MusicFragment extends AbsBaseFragment{
    private ViewPager musicVp;
    private TabLayout musicTb;
    private List<Fragment> datas;
    private VpAdapter vpAdapter;
    private Context context;

    public static MusicFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initView() {
        musicTb = byView(R.id.music_tb);
        musicVp = byView(R.id.music_vp);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        datas.add(RecommendFragment.newInstance());
        datas.add(RankingFragment.newInstance());
        datas.add(SongFragment.newInstance());
        datas.add(RadioFragment.newInstance());
        datas.add(MvFragment.newInstance());
        vpAdapter = new VpAdapter(getChildFragmentManager(), datas);
        musicVp.setAdapter(vpAdapter);
        musicTb.setupWithViewPager(musicVp);

        musicTb.getTabAt(0).setText("推荐");
        musicTb.getTabAt(1).setText("排行");
        musicTb.getTabAt(2).setText("歌单");
        musicTb.getTabAt(3).setText("电台");
        musicTb.getTabAt(4).setText("MV");
        musicTb.setTabTextColors(Color.BLACK, Color.argb(255, 45, 208, 242));

    }
}
