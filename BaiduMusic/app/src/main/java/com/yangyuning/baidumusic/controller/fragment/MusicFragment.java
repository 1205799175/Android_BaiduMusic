package com.yangyuning.baidumusic.controller.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class MusicFragment extends AbsBaseFragment{
    private ViewPager musicVp;
    private TabLayout musicTb;
    private List<Fragment> datas;
    private VpAdapter vpAdapter;
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
        datas.add(new RecommendFragment());
        datas.add(new RankingFragment());
        datas.add(new SongFragment());
        datas.add(new RadioFragment());
        datas.add(new MvFragment());
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
