package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库Fragment
 */
public class MusicFragment extends AbsBaseFragment {
    private ViewPager musicVp;
    private TabLayout musicTb;
    private List<Fragment> datas;
    private VpAdapter vpAdapter;
    //广播接收者
    private RecommendToSongReceiver recommendToSongReceiver;

    public static MusicFragment newInstance() {
        Bundle args = new Bundle();
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
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

        musicTb.getTabAt(0).setText(getString(R.string.music_tab_recommend));
        musicTb.getTabAt(1).setText(getString(R.string.music_tab_ranking));
        musicTb.getTabAt(2).setText(getString(R.string.music_tab_song));
        musicTb.getTabAt(3).setText(getString(R.string.music_tab_radio));
        musicTb.getTabAt(4).setText(getString(R.string.music_tab_mv));
        musicTb.setTabTextColors(Color.BLACK, Color.argb(255, 0, 180, 255));
        musicVp.setOffscreenPageLimit(0);

        //注册广播接收者
        initReceiver();
    }

    private void initReceiver() {
        recommendToSongReceiver = new RecommendToSongReceiver();
        IntentFilter toSongFilter = new IntentFilter();
        toSongFilter.addAction(BaiduMusicValues.THE_ACTION_RECOMMEND_TO_SONG);
        context.registerReceiver(recommendToSongReceiver, toSongFilter);
    }

    class RecommendToSongReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            musicVp.setCurrentItem(2);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(recommendToSongReceiver);
    }
}
