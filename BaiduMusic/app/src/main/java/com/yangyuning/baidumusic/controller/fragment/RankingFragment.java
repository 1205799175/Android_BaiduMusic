package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingLvAdapter;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 排行 Fragment
 */
public class RankingFragment extends AbsBaseFragment {
    private List<MusicRankingBean> datas;
    private MusicRankingLvAdapter musicRankingLvAdapter;
    private ListView rangkingLv;
    private Context context;

    public static RankingFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RankingFragment fragment = new RankingFragment();
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
        return R.layout.fragment_ranking;
    }

    @Override
    protected void initView() {
        rangkingLv = byView(R.id.ranking_lv);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(new MusicRankingBean("新歌榜", "歌曲一-歌手", "歌曲二-歌手", "歌曲三-歌手"));
        }
        musicRankingLvAdapter = new MusicRankingLvAdapter(context, datas);
        rangkingLv.setAdapter(musicRankingLvAdapter);
    }
}
