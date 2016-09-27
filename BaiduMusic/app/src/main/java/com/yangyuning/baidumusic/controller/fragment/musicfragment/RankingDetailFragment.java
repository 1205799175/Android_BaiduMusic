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
import com.yangyuning.baidumusic.controller.adapter.RankingDetailRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.RankingDetailRvBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.List;

/**
 * Created by dllo on 16/9/27.
 */
public class RankingDetailFragment extends AbsBaseFragment {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView toolBarBgImg;    //顶部背景
    private TextView totalSongNum;  //歌曲数
    private ImageView backImg, downImg, shareImg;      //返回键, 下载, 分享
    private RecyclerView recyclerView;
    private RankingDetailRvAdapter rankingDetalAdapter;

    public static RankingDetailFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
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
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_RANKING_DETAIL);
                intent.putExtra(BaiduMusicValues.RANKING_DETAIL_KET_POSITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
                context.sendBroadcast(intent);
            }
        });
    }

    //获取网络数据
    private void getNetData() {
        Bundle bundle = getArguments();
        int id = (int) bundle.get("id");
        String url = BaiduMusicValues.MUSIC_RANKING_DETAIL_HEAD + id + BaiduMusicValues.MUSIC_RANKING_DETAIL_BOTTOM;
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                RankingDetailRvBean bean = gson.fromJson(resultStr, RankingDetailRvBean.class);
                List<RankingDetailRvBean.SongListBean> datas = bean.getSong_list();
                rankingDetalAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }


}
