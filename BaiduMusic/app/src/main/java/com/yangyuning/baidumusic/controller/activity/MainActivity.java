package com.yangyuning.baidumusic.controller.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.PagesFragment;

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
        for (int i = 0; i < 3; i++) {
            datas.add(new PagesFragment());
        }
        vpAdapter = new VpAdapter(getSupportFragmentManager(), datas);
        mainVp.setAdapter(vpAdapter);
        mainTb.setupWithViewPager(mainVp);

        mainTb.getTabAt(0).setText("我的");
        mainTb.getTabAt(1).setText("乐库");
        mainTb.getTabAt(2).setText("K歌");
    }
}
