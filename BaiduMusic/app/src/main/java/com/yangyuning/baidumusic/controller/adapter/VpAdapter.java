package com.yangyuning.baidumusic.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/9/8.
 * ViewPager适配器
 * @author yangyuning
 * @time 9-8
 * @version 1.0
 */
public class VpAdapter extends FragmentPagerAdapter {

    private List<Fragment> datas;

    public VpAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
