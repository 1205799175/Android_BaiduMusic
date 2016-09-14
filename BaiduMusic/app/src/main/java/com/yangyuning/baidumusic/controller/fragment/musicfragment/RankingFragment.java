package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 排行 Fragment
 */
public class RankingFragment extends AbsBaseFragment {
    private MusicRankingLvAdapter musicRankingLvAdapter;
    private ListView rangkingLv;

    private TextView songOneTv, songTwoTv, songThreeTv;
    private List<MusicRankingBean> datas;

    public static RankingFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RankingFragment fragment = new RankingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_ranking;
    }

    @Override
    protected void initView() {
        rangkingLv = byView(R.id.ranking_lv);
        songOneTv = byView(R.id.item_ranking_song_one);
        songTwoTv = byView(R.id.item_ranking_song_two);
        songThreeTv = byView(R.id.item_ranking_song_three);
    }

    @Override
    protected void initDatas() {
//        datas = new ArrayList<>();
//        //假数据
//        for (int i = 0; i < 10; i++) {
//            datas.add(new MusicRankingBean("新歌榜", "歌曲一-歌手", "歌曲二-歌手", "歌曲三-歌手"));
//        }


        musicRankingLvAdapter = new MusicRankingLvAdapter(context);
        rangkingLv.setAdapter(musicRankingLvAdapter);
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_RANKING, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicRankingBean musicRankingBean = gson.fromJson(resultStr, MusicRankingBean.class);
                List<MusicRankingBean.ContentBean.ContentChildBean> datas = musicRankingBean.getContent().getChildContent();
                musicRankingLvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });


    }
}
