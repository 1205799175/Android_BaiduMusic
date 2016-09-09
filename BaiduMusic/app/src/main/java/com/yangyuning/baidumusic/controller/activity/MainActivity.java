package com.yangyuning.baidumusic.controller.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.AliveFragment;
import com.yangyuning.baidumusic.controller.fragment.KFragment;
import com.yangyuning.baidumusic.controller.fragment.OwnFragment;
import com.yangyuning.baidumusic.controller.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity {

    private TabLayout mainTb;
    private ViewPager mainVp;

    private List<Fragment> datas;
    private VpAdapter vpAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        mainTb = byView(R.id.main_tb);
        mainVp = byView(R.id.main_vp);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        datas.add(new OwnFragment());
        datas.add(new MusicFragment());
        datas.add(new KFragment());
        datas.add(new AliveFragment());
        vpAdapter = new VpAdapter(getSupportFragmentManager(), datas);
        mainVp.setAdapter(vpAdapter);
        mainTb.setupWithViewPager(mainVp);

        mainTb.getTabAt(0).setText("我的");
        mainTb.getTabAt(1).setText("乐库");
        mainTb.getTabAt(2).setText("K歌");
        mainTb.getTabAt(3).setText("直播");
        mainTb.setTabTextColors(Color.argb(255, 207, 207, 207), Color.WHITE);
    }
}
