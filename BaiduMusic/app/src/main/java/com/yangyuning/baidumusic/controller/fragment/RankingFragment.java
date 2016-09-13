package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingLvAdapter;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 排行 Fragment
 */
public class RankingFragment extends AbsBaseFragment {
    private MusicRankingLvAdapter musicRankingLvAdapter;
    private ListView rangkingLv;
    private Context context;

    //定义请求队列
    private RequestQueue queue;
    private String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.billboard.billCategory&format=json&kflag=2";

    private TextView songOneTv, songTwoTv, songThreeTv;
    private List<MusicRankingBean> datas;

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
        songOneTv = byView(R.id.item_ranking_song_one);
        songTwoTv = byView(R.id.item_ranking_song_two);
        songThreeTv = byView(R.id.item_ranking_song_three);

    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add(new MusicRankingBean("新歌榜", "歌曲一-歌手", "歌曲二-歌手", "歌曲三-歌手"));
        }
        musicRankingLvAdapter = new MusicRankingLvAdapter(context);
        musicRankingLvAdapter.setDatas(datas);
        rangkingLv.setAdapter(musicRankingLvAdapter);
    }
}
