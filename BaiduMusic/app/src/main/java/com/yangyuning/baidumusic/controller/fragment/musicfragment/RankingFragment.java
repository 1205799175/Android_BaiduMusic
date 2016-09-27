package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicRankingBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 排行 Fragment
 */
public class RankingFragment extends AbsBaseFragment {
    private MusicRankingLvAdapter musicRankingLvAdapter;
    private ListView rangkingLv;

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
    }

    @Override
    protected void initDatas() {
        //初始化适配器, 绑定适配器
        musicRankingLvAdapter = new MusicRankingLvAdapter(context);
        //获得并解析网络数据
        getNetDatas();
        rangkingLv.setAdapter(musicRankingLvAdapter);
        //行点击事件 进入二级页面
        initListener();
    }

    //获得并解析网络数据
    private void getNetDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_RANKING, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicRankingBean musicRankingBean = gson.fromJson(resultStr, MusicRankingBean.class);
                List<MusicRankingBean.ContentBean> datas = musicRankingBean.getContent();
                musicRankingLvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {
            }
        });
    }

    //行点击事件 进入二级页面
    private void initListener() {
        rangkingLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_RANKING_DETAIL);
                intent.putExtra(BaiduMusicValues.RANKING_DETAIL_KET_POSITION, position);
                context.sendBroadcast(intent);
            }
        });
    }

}
