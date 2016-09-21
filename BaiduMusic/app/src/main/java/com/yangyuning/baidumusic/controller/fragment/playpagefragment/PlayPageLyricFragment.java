package com.yangyuning.baidumusic.controller.fragment.playpagefragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

/**
 * Created by dllo on 16/9/21.
 * 播放音乐 歌词界面
 */
public class PlayPageLyricFragment extends AbsBaseFragment implements View.OnClickListener {

    private TextView songTv, singerTv;
    private ImageView backImg;
    private PlayMusicPageReceiver playMusicPageReceiver;

    public static PlayPageLyricFragment newInstance() {
        Bundle args = new Bundle();
        PlayPageLyricFragment fragment = new PlayPageLyricFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_playpage_lyric;
    }

    @Override
    protected void initView() {
        songTv = byView(R.id.playpage_lyric_song_tv);
        singerTv = byView(R.id.playpage_lyric_singer_tv);
        backImg = byView(R.id.playpage_lyric_back_img);
        backImg.setOnClickListener(PlayPageLyricFragment.this);
    }

    @Override
    protected void initDatas() {
        //注册广播
        playMusicPageReceiver = new PlayMusicPageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
        context.registerReceiver(playMusicPageReceiver, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.playpage_lyric_back_img:
                break;
        }
    }

    //广播接收者
    class PlayMusicPageReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String song = intent.getStringExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SONG);
            String singer = intent.getStringExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SINGER);
            songTv.setText(song);
            singerTv.setText(singer);
        }
    }

    //取消注册

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(playMusicPageReceiver);
    }
}
