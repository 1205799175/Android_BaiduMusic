package com.yangyuning.baidumusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.playpagefragment.PlayPageLyricFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;
import com.yangyuning.baidumusic.utils.AeroGlassUtil;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

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

    private TextView songTv, singerTv;
    private ImageView backImg, lyricImg;
    private LinearLayout playMusicBg;

    private boolean flag = false;   //切换歌/词 状态记录

    //播放模式图标
    private int[] modeIcon;
    private int[] modeName = new int[]{R.string.random_mode, R.string.order_mode, R.string.roundsingle_mode, R.string.loop_mode};
    private int currentMode = 0;

    //广播相关
    private PlayPageReceiver playPageReceiver;

    //播放按钮相关
    private ImageView modeImg, pastImg, playImg, nextImg, listImg;
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
            musicBinder.playMusic();
            musicBinder.pauseMusic();
            setMusicTimeInfo();
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
        modeImg = byView(R.id.play_music_form_img); //播放模式
        pastImg = byView(R.id.play_music_past_img); //上一曲
        playImg = byView(R.id.play_music_play_img); //播放
        nextImg = byView(R.id.play_music_next_img); //下一曲
        listImg = byView(R.id.play_music_list_img); //播放列表
        seekBar = byView(R.id.play_music_seekbar);  //进度条
        songTv = byView(R.id.play_music_song_tv);   //显示歌名
        singerTv = byView(R.id.play_music_singer_tv);   //显示歌手
        backImg = byView(R.id.play_music_back_img); //退出
        lyricImg = byView(R.id.play_music_lyric_img);   //词
        playMusicBg = byView(R.id.play_music_ll); //背景
        currentTimeTv = byView(R.id.play_music_current_time_tv);
        durationTv = byView(R.id.play_music_duration_tv);

        modeImg.setOnClickListener(this);
        pastImg.setOnClickListener(this);
        playImg.setOnClickListener(this);
        nextImg.setOnClickListener(this);
        listImg.setOnClickListener(this);
        backImg.setOnClickListener(this);
        lyricImg.setOnClickListener(this);
        //实现快进快退, 给进度条状态改变设置监听事件
        initSeekBar();
    }

    private void initSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (musicBinder != null) {
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
            if (msg.what == 101) {
                int index = (int) msg.obj;
                seekBar.setProgress(index);

                long currentTime = musicBinder.getCurrentMusicPosition();
                long minute = currentTime / (1000 * 60);
                long second = (currentTime % (1000 * 60)) / 1000;
                String minuteStr = String.valueOf(minute);
                String secondStr = String.valueOf(second);
                if (minuteStr.length() == 1) {
                    minuteStr = "0" + minuteStr;
                }
                if (secondStr.length() == 1) {
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

        //注册广播
        playPageReceiver = new PlayPageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
        registerReceiver(playPageReceiver, filter);

        //设置毛玻璃效果
        BitmapDrawable bg = new BitmapDrawable(getResources(),
                AeroGlassUtil.doBlur(BitmapFactory.decodeResource(getResources(), R.mipmap.lunbo), 90, false));
        playMusicBg.setBackground(bg);
        //设置初始播放模式
        initPlayMode();

        //绑定服务
        intent = new Intent(PlayMusicPageActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    //获取播放界面播放模式图片, 名称
    private void initPlayMode() {
        TypedArray playMode;
        playMode = getResources().obtainTypedArray(R.array.play_page_mode);
        int len = playMode.length();
        modeIcon = new int[len];
        for (int i = 0; i < len; i++)
            modeIcon[i] = playMode.getResourceId(i, 0);
        playMode.recycle();
        modeImg.setImageResource(modeIcon[0]);
    }


    //进度条线程
    private class SeekBarRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (musicBinder != null) {
                    if (musicBinder.getMusicIsPlaying()) {
                        if (musicBinder != null) {
                            int currentPosition = musicBinder.getCurrentMusicPosition();
                            Message message = new Message();
                            message.what = 101;
                            message.obj = currentPosition;
                            handler.sendMessage(message);
                        }
                        try {
                            Thread.sleep(1000);
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
        switch (v.getId()) {
            case R.id.play_music_form_img:  //播放模式:循环, 随机, 单曲等等
                currentMode++;
                currentMode = currentMode % modeIcon.length;
                modeImg.setImageResource(modeIcon[currentMode]);
                musicBinder.changePlayMode(currentMode);
                Toast.makeText(this, modeName[currentMode], Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_music_past_img:  //上一曲
                if (musicBinder != null) {
                    musicBinder.pastMusic();
                    //设置进度条的最大值
                    seekBar.setMax(musicBinder.getCurrentDruation());
                    //设置歌曲时间
                    setMusicTimeInfo();
                }
                break;
            case R.id.play_music_play_img:  //开始/暂停
                if (musicBinder != null) {
                    musicBinder.pauseMusic();
                    if (!musicBinder.getMusicIsPlaying()) {
                        playImg.setSelected(false);
                    } else {
                        playImg.setSelected(true);
                    }
                }
                break;
            case R.id.play_music_next_img:  //下一曲
                if (musicBinder != null) {
                    musicBinder.nextMusic();
                    //设置进度条的最大值
                    seekBar.setMax(musicBinder.getCurrentDruation());
                    //设置歌曲时间
                    setMusicTimeInfo();
                }
                break;
            case R.id.play_music_list_img:  //播放列表
                break;
            case R.id.play_music_back_img:
                finish();
                break;
            case R.id.play_music_lyric_img:  // 切换歌/词
                if (flag == false) {
                    lyricImg.setImageResource(R.mipmap.bt_playpage_button_pic_normal);
                    vp.setCurrentItem(2);
                    flag = true;
                } else if (flag == true) {
                    lyricImg.setImageResource(R.mipmap.bt_playpage_button_lyric_normal);
                    vp.setCurrentItem(1);
                    flag = false;
                }

                break;
        }
        //设置播放键状态
        initBtnState();
    }

    private void initBtnState() {
        if (musicBinder.getMusicIsPlaying()) {
            playImg.setSelected(true);
        } else {
            playImg.setSelected(false);
        }
    }

    //设置最大时长和歌曲信息
    private void setMusicTimeInfo() {
        OwnLocalMusicLvBean bean = musicBinder.getCurrentMusicBean();
        //时间需要改成 00:00  分秒形式
        long duration = bean.getDuration();
        long minute = duration / (1000 * 60);
        long second = (duration % (1000 * 60)) / 1000;
        //把long类型的秒-->String
        String minuteStr = String.valueOf(minute);
        String secondStr = String.valueOf(second);
        if (minuteStr.length() == 1) {
            minuteStr = "0" + minuteStr;
        }
        if (secondStr.length() == 1) {
            secondStr = "0" + secondStr;
        }
        durationTv.setText(minuteStr + ":" + secondStr);
        String song = bean.getSong();
        String singer = bean.getSinger();
        songTv.setText(song);
        singerTv.setText(singer);
        seekBar.setMax((int) bean.getDuration());
    }

    //广播接收者 改变歌手, 歌曲, 时长
    private class PlayPageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            setMusicTimeInfo();
        }
    }

    //接触绑定
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(playPageReceiver);
        unbindService(serviceConnection);
        if (musicBinder != null) {
            musicBinder.stopMusic();
        }
        musicBinder = null;
    }
}
