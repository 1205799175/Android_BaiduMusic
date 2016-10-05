package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.activity.MainActivity;
import com.yangyuning.baidumusic.controller.adapter.SongDetailRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.interfaces.OnChangeMusicListener;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/29.
 * 歌单 详情
 */
public class SongDetailFragment extends AbsBaseFragment{

    private ImageView backImg, bgImg;//退出, 背景图
    private TextView styleTv, countTv, favTv, songNumTv, titleTv;
    private ImageView favImg, downImg, shareImg;
    private RecyclerView rv;
    private SongDetailRvAdapter songDetailRvAdapter;
    private List<MusicBean> dataList;
    private OnChangeMusicListener onChangeMusicListener;

    public static SongDetailFragment newInstance(Bundle bundle) {
        SongDetailFragment fragment = new SongDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_song_detail;
    }

    @Override
    protected void initView() {
        titleTv = byView(R.id.music_song_detail_title);
        bgImg = byView(R.id.song_detail_toolbar_layout_bg);
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
        songDetailRvAdapter = new SongDetailRvAdapter(context);
        //解析网络数据
        getNetDatas();
        initListener();
        rv.setAdapter(songDetailRvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(context));
    }

    private void getNetDatas() {
        Bundle bundle = getArguments();
        MusicSongBean.ContentBean contentBean = (MusicSongBean.ContentBean) bundle.getSerializable("contentBean");
        List<String> songIds = contentBean.getSongIds();
        dataList = new ArrayList<>();
        for (int i = 0; i < songIds.size(); i++) {
            String url = BaiduMusicValues.SONG_ULR_HEAD + songIds.get(i) + BaiduMusicValues.SONG_URL_FOOT;
            VolleyInstance.getInstance().startResult(url, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    resultStr = MainActivity.fixJsonData(resultStr);
                    Gson gson = new Gson();
                    MusicBean musicBean = gson.fromJson(resultStr, MusicBean.class);
                    int duration = musicBean.getBitrate().getFile_duration();
                    musicBean.getBitrate().setFile_duration(duration * 1000);
                    dataList.add(musicBean);
                    songDetailRvAdapter.setDatas(dataList);
                }

                @Override
                public void failure() {

                }
            });
        }
        songDetailRvAdapter.setDatas(dataList);
        // 设置顶部背景图片以及文字内容
        if (contentBean.getPic_w300() != null && !contentBean.getPic_w300().equals("")) {
            Picasso.with(context).load(contentBean.getPic_w300()).into(bgImg);
        } else if (contentBean.getPic_300() != null && !contentBean.getPic_300().equals("")) {
            Picasso.with(context).load(contentBean.getPic_300()).into(bgImg);
        }
        titleTv.setText(contentBean.getTitle());
        styleTv.setText(contentBean.getTag());
        countTv.setText(contentBean.getListenum());
        favTv.setText(contentBean.getCollectnum());
        songNumTv.setText("共" + songIds.size() + "首歌");

    }

    private void initListener() {
        //返回按钮
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        //播放网络歌曲
        songDetailRvAdapter.setOnRvItemClickListener(new OnRvItemClick<MusicBean>() {
            @Override
            public void onRvItemClickListener(int position, MusicBean musicBean) {
                MusicService.setDatas(dataList);
                MusicService.musicBinder.playMusicByMode(position);
                onChangeMusicListener.OnChangeMuisc(dataList.get(position), position);
            }
        });
        onChangeMusicListener = (OnChangeMusicListener) context;
    }


}
