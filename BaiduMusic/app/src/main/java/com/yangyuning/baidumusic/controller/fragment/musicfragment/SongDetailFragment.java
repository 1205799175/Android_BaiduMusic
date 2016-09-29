package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/29.
 * 歌单 详情
 */
public class SongDetailFragment extends AbsBaseFragment {

    private ImageView backImg;//退出
    private TextView styleTv, countTv, favTv, songNumTv;
    private ImageView favImg, downImg, shareImg;
    private RecyclerView rv;

    public static SongDetailFragment newInstance(List<String> songId) {
        Bundle args = new Bundle();
        args.putSerializable("songId", (ArrayList<String>) songId);
        SongDetailFragment fragment = new SongDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_song_detail;
    }

    @Override
    protected void initView() {
        backImg = byView(R.id.music_song_detail_back_btn);
        styleTv = byView(R.id.music_song_detail_style);
        countTv = byView(R.id.music_song_detail_count);
        favTv = byView(R.id.music_song_detail_fav_tv);
        songNumTv = byView(R.id.msuic_song_detail_total_songs);
        favImg = byView(R.id.music_song_detail_fav);
        downImg = byView(R.id.music_song_detail_down_load_all);
        shareImg = byView(R.id.music_song_detail_share_song_list);
        rv = byView(R.id.music_song_detail_rv);
    }

    @Override
    protected void initDatas() {
        //解析网络数据
        getNetDatas();
        initListener();
    }

    private void getNetDatas() {
        Bundle bundle = getArguments();
        String songId = bundle.getString("songId");
        String url = BaiduMusicValues.SONG_ULR_HEAD + songId + BaiduMusicValues.SONG_URL_FOOT;
        Log.d("sss", url + "==");
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();

            }

            @Override
            public void failure() {

            }
        });
    }

    private void initListener() {
        //返回按钮
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_SONG_TO_DETAIL);
                intent.putExtra(BaiduMusicValues.SONG_DETAIL_KET_POSITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
                context.sendBroadcast(intent);
            }
        });
    }



}
