package com.yangyuning.baidumusic.controller.fragment;

import android.widget.ListView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingAdapter;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 */
public class RankingFragment extends AbsBaseFragment {
    private List<MusicRankingBean> datas;
    private MusicRankingAdapter musicRankingAdapter;
    private ListView rangkingLv;
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
        musicRankingAdapter = new MusicRankingAdapter(BaiduMusicApp.getContext(), datas);
        rangkingLv.setAdapter(musicRankingAdapter);
    }
}
