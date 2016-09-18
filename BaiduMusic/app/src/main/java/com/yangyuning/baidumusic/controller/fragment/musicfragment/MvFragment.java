package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

/**
 * Created by dllo on 16/9/9.
 * MV Fragment
 */
public class MvFragment extends AbsBaseFragment {
    private RadioGroup radioGroup;

    public static MvFragment newInstance() {

        Bundle args = new Bundle();

        MvFragment fragment = new MvFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_mv;
    }

    @Override
    protected void initView() {
        radioGroup = byView(R.id.music_mv_rg);
    }

    @Override
    protected void initDatas() {
        //点击切换最新最热
        ChangePageOfNewHot();
    }

    //点击切换最新最热
    private void ChangePageOfNewHot() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fm = getChildFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                switch (checkedId){
                    case R.id.music_mv_new_rb:
                        ft.replace(R.id.music_mv_frame_layout, MvNewAndHotFragment.newInstance(BaiduMusicValues.MUSIC_NEW_MV));
                        break;
                    case R.id.music_mv_hot_rb:
                        ft.replace(R.id.music_mv_frame_layout, MvNewAndHotFragment.newInstance(BaiduMusicValues.MUSIC_HOT_MV));
                        break;
                }
                ft.commit();
            }
        });
        //默认选中
        radioGroup.check(R.id.music_mv_new_rb);
    }
}
