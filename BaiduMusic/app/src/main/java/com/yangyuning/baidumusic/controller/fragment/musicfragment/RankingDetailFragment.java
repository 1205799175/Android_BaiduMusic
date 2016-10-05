package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RankingDetailRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.RankingDetailRvBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/27.
 * 乐库 排行 详情
 */
public class RankingDetailFragment extends AbsBaseFragment {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView toolBarBgImg;    //顶部背景
    private TextView totalSongNum;  //歌曲数
    private ImageView backImg, downImg, shareImg;      //返回键, 下载, 分享
    private RecyclerView recyclerView;
    private RankingDetailRvAdapter rankingDetalAdapter;
    private List<RankingDetailRvBean.SongListBean> datas;

    public static RankingDetailFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        RankingDetailFragment fragment = new RankingDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_ranking_detail;
    }

    @Override
    protected void initView() {
        collapsingToolbarLayout = byView(R.id.rank_detail_toolbar_layout);
        toolBarBgImg = byView(R.id.rank_detail_toolbar_layout_bg);
        backImg = byView(R.id.rank_detail_back_btn);
        downImg = byView(R.id.rank_detail_down_load_all);
        shareImg = byView(R.id.rank_detail_share_song_list);
        totalSongNum = byView(R.id.rank_detail_total_songs);
        recyclerView = byView(R.id.rank_detail_rv);
    }

    @Override
    protected void initDatas() {
        rankingDetalAdapter = new RankingDetailRvAdapter(context);
        //获取网络数据
        getNetData();
        recyclerView.setAdapter(rankingDetalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        addListener();
    }

    private void addListener() {
        //点击退出详情
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    //获取网络数据
    private void getNetData() {
        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        String url = BaiduMusicValues.MUSIC_RANKING_DETAIL_HEAD + type + BaiduMusicValues.MUSIC_RANKING_DETAIL_BOTTOM;
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                RankingDetailRvBean bean = gson.fromJson(resultStr, RankingDetailRvBean.class);
                datas = bean.getSong_list();
                rankingDetalAdapter.setDatas(datas);
                Picasso.with(context).load(bean.getBillboard().getPic_s210()).into(toolBarBgImg);
                totalSongNum.setText("共" + datas.size() + "首歌");
                collapsingToolbarLayout.setTitle(bean.getBillboard().getName());
            }

            @Override
            public void failure() {

            }
        });
    }


}
