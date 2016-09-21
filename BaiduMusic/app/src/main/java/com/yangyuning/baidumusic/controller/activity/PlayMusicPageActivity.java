package com.yangyuning.baidumusic.controller.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.playpagefragment.PlayPageLyricFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/21.
 * 播放音乐界面
 */
public class PlayMusicPageActivity extends AbsBaseActivity implements View.OnClickListener {
    private List<Fragment> fragments;
    private ViewPager vp;
    private VpAdapter vpAdapter;

    //播放按钮相关
    private ImageView formImg, pastImg, playImg, nextImg, listImg;
    private SeekBar seekBar;
    private TextView currentTimeTv, durationTv;
    //服务相关
    private Intent intent;
    private MusicService.MusicBinder musicBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (MusicService.MusicBinder) service;
            new Thread(new SeekBarRunnable()).start();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_play_music;
    }

    @Override
    protected void initView() {
        vp = byView(R.id.play_page_vp);
        formImg = byView(R.id.play_music_form_img);
        pastImg = byView(R.id.play_music_past_img);
        playImg = byView(R.id.play_music_play_img);
        nextImg = byView(R.id.play_music_next_img);
        listImg = byView(R.id.play_music_list_img);
        seekBar = byView(R.id.play_music_seekbar);
        currentTimeTv = byView(R.id.play_music_current_time_tv);
        durationTv = byView(R.id.play_music_duration_tv);

        formImg.setOnClickListener(this);
        pastImg.setOnClickListener(this);
        playImg.setOnClickListener(this);
        nextImg.setOnClickListener(this);
        listImg.setOnClickListener(this);
        //实现快进快退, 给进度条状态改变设置监听事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    if (musicBinder != null){
                        musicBinder.musicForwardAndBack(progress);
                        seekBar.setProgress(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //刷新进度条的Handler
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 101){
                int index = (int) msg.obj;
                seekBar.setProgress(index);

                long minute = index / (1000 * 60);
                long second = (index % (1000 * 60)) / 1000;
                String minuteStr = String.valueOf(minute);
                String secondStr = String.valueOf(second);
                if (minuteStr.length() == 1){
                    minuteStr = "0" + minuteStr;
                }
                if (secondStr.length() == 1){
                    secondStr = "0" + secondStr;
                }
                currentTimeTv.setText(minuteStr + ":" + secondStr);
            }
            return false;
        }
    });

    @Override
    protected void initDatas() {
        fragments = new ArrayList<>();
        fragments.add(PlayPageLyricFragment.newInstance());
        fragments.add(PlayPageLyricFragment.newInstance());
        fragments.add(PlayPageLyricFragment.newInstance());
        vpAdapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(vpAdapter);
        vp.setCurrentItem(1);

        //绑定服务
        intent = new Intent(PlayMusicPageActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    //进度条线程
    private class SeekBarRunnable implements Runnable{
        @Override
        public void run() {
            while (true){
                if (musicBinder != null){
                    if (musicBinder.getMusicIsPlaying()){
                        int currentPosition = musicBinder.getCurrentMusicPosition();
                        Message message = new Message();
                        message.what = 101;
                        message.obj = currentPosition;
                        handler.sendMessage(message);
                        try {
                            Thread.sleep(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_music_form_img:  //播放模式:循环, 随机, 单曲等等
                break;
            case R.id.play_music_past_img:  //上一曲
                if (musicBinder != null){
                    musicBinder.pastMusic();
                    OwnLocalMusicLvBean bean = musicBinder.getCurrentMusicBean();
                    //设置歌曲时间
                    setMusicTimeInfo(bean);
                    //发送广播
                    Intent intent = new Intent();
                    intent.setAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
                    intent.putExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SONG, bean.getSong());
                    intent.putExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SINGER, bean.getSinger());
                    sendBroadcast(intent);
                }
                break;
            case R.id.play_music_play_img:  //开始暂停
                if (musicBinder != null){
                    if (!musicBinder.getMusicIsPlaying()){
                        playImg.setSelected(true);
                        musicBinder.playMusic();
                        //获得当前歌曲的信息
                        OwnLocalMusicLvBean bean = musicBinder.getCurrentMusicBean();
                        //设置进度条的最大值
                        seekBar.setMax(musicBinder.getCurrentDruation());
                        //设置歌曲时间
                        setMusicTimeInfo(bean);
                        //发送广播
                        Intent intent = new Intent();
                        intent.setAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
                        intent.putExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SONG, bean.getSong());
                        intent.putExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SINGER, bean.getSinger());
                        sendBroadcast(intent);
                    } else {
                        musicBinder.pauseMusic();
                        playImg.setSelected(false);
                    }
                }
                break;
            case R.id.play_music_next_img:  //下一曲
                if (musicBinder != null){
                    musicBinder.nextMusic();
                    OwnLocalMusicLvBean bean = musicBinder.getCurrentMusicBean();
                    //设置歌曲时间
                    setMusicTimeInfo(bean);
                    //发送广播
                    Intent intent = new Intent();
                    intent.setAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
                    intent.putExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SONG, bean.getSong());
                    intent.putExtra(BaiduMusicValues.THE_ACTION_PLAY_PAGE_SINGER, bean.getSinger());
                    sendBroadcast(intent);
                }
                break;
            case R.id.play_music_list_img:  //播放列表
                break;
        }
    }

    private void setMusicTimeInfo(OwnLocalMusicLvBean ownLocalMusicLvBean){
        //时间需要改成 00:00  分秒形式
        long duration = ownLocalMusicLvBean.getDuration();
        long minute = duration / (1000 * 60);
        long second = (duration % (1000 * 60)) / 1000;
        //把long类型的秒-->String
        String minuteStr = String.valueOf(minute);
        String secondStr = String.valueOf(second);
        if (minuteStr.length() == 1){
            minuteStr = "0" + minuteStr;
        }
        if (secondStr.length() == 1){
            secondStr = "0" + secondStr;
        }
        durationTv.setText(minuteStr + ":" + secondStr);
    }

    //接触绑定
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

}
