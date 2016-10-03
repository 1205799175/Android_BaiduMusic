package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.RecommendSongerLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class RecommendDetailSongerFragment extends AbsBaseFragment {

    private TextView backTv;
    private LinearLayout moreLl;
    private ViewPager songerVp;
    private TabLayout songerTb;
    private MyListView songerLv;
    private List<String> datas;
    private RecommendSongerLvAdapter adapter;

    public static RecommendDetailSongerFragment newInstance() {
        Bundle args = new Bundle();
        RecommendDetailSongerFragment fragment = new RecommendDetailSongerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend_songer_detail;
    }

    @Override
    protected void initView() {
        backTv = byView(R.id.recommend_songer_detail_back);
        moreLl = byView(R.id.recommend_songer_detail_more);
        songerVp = byView(R.id.recommend_songer_detail_vp);
        songerTb = byView(R.id.recommend_songer_detail_tb);
        songerLv = byView(R.id.recommend_songer_detail_lv);
    }

    @Override
    protected void initDatas() {
        adapter = new RecommendSongerLvAdapter(context);
        //给ListView设置数据
        initListView();
        addListener();
    }

    private void initListView() {
        datas = new ArrayList<>();
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_CHINA_MAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_CHINA_WOMAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_CHINA_TEAM);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_USA_MAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_USA_WOMAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_USA_TEAM);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_KOREA_MAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_KOREA_WOMAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_KOREA_TEAM);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_JAPAN_MAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_JAPAN_WOMAN);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_JAPAN_TEAM);
        datas.add(BaiduMusicValues.RECOMMEND_SONGER_OTHER);
        songerLv.setAdapter(adapter);
        adapter.setDatas(datas);
    }

    private void addListener() {
        backTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_RECOMMEND_SONGER);
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
                context.sendBroadcast(intent);
            }
        });
    }
}
