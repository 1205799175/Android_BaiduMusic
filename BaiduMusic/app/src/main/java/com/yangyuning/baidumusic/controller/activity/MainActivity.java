package com.yangyuning.baidumusic.controller.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.PlayPageLinearPagerAdapter;
import com.yangyuning.baidumusic.controller.adapter.PlayPageLyricLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.LoginFragment;
import com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveRvDetailFragment;
import com.yangyuning.baidumusic.controller.fragment.kfragment.KDetailFragment;
import com.yangyuning.baidumusic.controller.fragment.musicfragment.RankingDetailFragment;
import com.yangyuning.baidumusic.controller.fragment.musicfragment.RecommendDetailSongerFragment;
import com.yangyuning.baidumusic.controller.fragment.musicfragment.SongDetailFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.LocalMusicDetailsFragment;
import com.yangyuning.baidumusic.controller.fragment.MainFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.LyricBean;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.AeroGlassUtil;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;
import com.yangyuning.baidumusic.utils.interfaces.OnChangeMusicListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity implements View.OnClickListener, OnChangeMusicListener {
    private TextView minSongTv, minSingerTv;  //底部播放栏歌手名, 歌曲名
    private ImageView minNextImg, minPlayImg, minListImg, minBgIcon;    //底部播放栏按钮, 播放歌曲图标
    private LinearLayout linearLayout, main_ll;  //底部播放栏线性布局, main整体布局的线性布局

    private boolean isExit; // app退出标志位

    private FrameLayout frameLayout;
    //弹出播放PopWindow相关内容
    private PopupWindow popupWindow;
    private ImageView popBgImg; //PopWindow背景
    private View popView;   //PopWindow布局
    private List<LinearLayout> linearLayouts;   //PopWindow中三个布局
    private PlayPageLinearPagerAdapter playPageLinearPagerAdapter;
    private ViewPager popVp;
    private TabLayout popTb;
    private TextView popSonVgTv, popSingerTv;    //PopWindow上方歌名歌手
    private ImageView popBackImg, popLyricImg;  //返回, 词
    private ImageView popModeImg, popPastImg, popPlayImg, popNextImg, popListImg;   //PopWindow内播放模式, 上一曲,播放, 下一曲, 播放列表
    private SeekBar popSeekBar; //进度条
    private TextView popCurrentTimeTv, popDurationTv;   //进度条时间
    private boolean flag = false;   //切换歌/词 状态记录
    private LinearLayout playPageLl;    //播放界面 歌词 最右面
    private LinearLayout playPageLyrivLl; //播放界面 中间
    private GestureDetector gestureDetector;

    //歌词同步
    private PlayPageLyricLvAdapter lyricLvAdapter;
    private ListView lyrivLv;
    private ImageView playPageCenterBgImg;  //播放歌曲中间页背景图片

    //播放模式图标
    private int[] modeIcon;
    private int[] modeName = new int[]{R.string.random_mode, R.string.order_mode, R.string.roundsingle_mode, R.string.loop_mode};
    private int[] minListModeIcon;
    private int currentMode = 0;

    //弹出minbar播放列表相关
    private PopupWindow minPopWin;
    private View minListPopView;    //min播放列表PopWindow布局
    private ImageView minListPopMode;   //min播放列表PopWindow播放模式
    private Button minListPopBtn;   //min播放列表PopWindow:正在播放列表
    private TextView minListPopClearAll, minListPopTv;    //全部清空, 清空后显示的文字
//    private ListView minListLv; //ListView

    //服务相关
    private Intent intent;
    private MusicService.MusicBinder musicBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicBinder = (MusicService.MusicBinder) service;
            new Thread(new SeekBarRunnable()).start();
            // 启动歌词同步线程
            new Thread(new NotifySynLyricThread()).start();
            initSeekBar();
            musicBinder.playMusic();
            musicBinder.pauseMusic();
            setMusicTimeInfo();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //广播接收者
    private FrameReceiver frameReceiver;
    private AliveTopRvReceiver aliveTopRvReceiver;
    private LocalMusicPlayReceiver localMusicPlayReceiver;
    private RankingDetailReceiver rankingDetailReceiver;
    private PlayPageReceiver playPageReceiver;
    private SongDetailReceiver songDetailReceiver;
    private KDeatilReceiver kDeatilReceiver;
    private RecommendDetailSongerReceiver recommendDetailSongerReceiver;
    private MainFragmentToLoginReceiver mainFragmentToLoginReceiver;

    //请求队列
    private RequestQueue queue;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        queue = Volley.newRequestQueue(MainActivity.this);

        frameLayout = byView(R.id.main_frame_layout);

        minBgIcon = byView(R.id.main_song_icon);
        minSongTv = byView(R.id.main_song);
        minSingerTv = byView(R.id.main_singer);
        minNextImg = byView(R.id.main_next);
        minPlayImg = byView(R.id.main_play);
        minListImg = byView(R.id.main_playinglist);
        linearLayout = byView(R.id.main_play_layout);
        main_ll = byView(R.id.main_ll);
        //播放PopWindow
        popView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_popwin_play_music, null);
        popBgImg = (ImageView) popView.findViewById(R.id.play_page_pop_bg_img);
        popVp = (ViewPager) popView.findViewById(R.id.play_page_vp);
        popTb = (TabLayout) popView.findViewById(R.id.play_page_tb);
        popSonVgTv = (TextView) popView.findViewById(R.id.play_music_song_tv);
        popSingerTv = (TextView) popView.findViewById(R.id.play_music_singer_tv);
        popBackImg = (ImageView) popView.findViewById(R.id.play_music_back_img);
        popLyricImg = (ImageView) popView.findViewById(R.id.play_music_lyric_img);
        popModeImg = (ImageView) popView.findViewById(R.id.play_music_form_img);
        popPastImg = (ImageView) popView.findViewById(R.id.play_music_past_img);
        popPlayImg = (ImageView) popView.findViewById(R.id.play_music_play_img);
        popNextImg = (ImageView) popView.findViewById(R.id.play_music_next_img);
        popListImg = (ImageView) popView.findViewById(R.id.play_music_list_img);
        popSeekBar = (SeekBar) popView.findViewById(R.id.play_music_seekbar);
        popCurrentTimeTv = (TextView) popView.findViewById(R.id.play_music_current_time_tv);
        popDurationTv = (TextView) popView.findViewById(R.id.play_music_duration_tv);
        //歌词界面
        playPageLl = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_play_page_lyric, null);
        lyrivLv = (ListView) playPageLl.findViewById(R.id.play_page_lyric_lv);
        playPageLyrivLl = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_playpage_lyric, null);
        playPageCenterBgImg = (ImageView) playPageLyrivLl.findViewById(R.id.play_page_lyric_img);

        //min播放列表PopWindow
        minListPopView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_popwin_minlist, null);
        minListPopMode = (ImageView) minListPopView.findViewById(R.id.min_list_pop_mode_img);
        minListPopBtn = (Button) minListPopView.findViewById(R.id.min_list_pop_isplaying_btn);
        minListPopClearAll = (TextView) minListPopView.findViewById(R.id.min_list_pop_clear_all_tv);
        minListPopTv = (TextView) minListPopView.findViewById(R.id.min_list_pop_tv);
//        minListLv = (ListView) minListPopView.findViewById(R.id.min_list_pop_lv);

        popModeImg.setOnClickListener(this);
        popPastImg.setOnClickListener(this);
        popPlayImg.setOnClickListener(this);
        popNextImg.setOnClickListener(this);
        popListImg.setOnClickListener(this);
        popBackImg.setOnClickListener(this);
        popLyricImg.setOnClickListener(this);

        minListPopMode.setOnClickListener(this);
        minListPopBtn.setOnClickListener(this);
        minListPopClearAll.setOnClickListener(this);
        //实现快进快退, 给进度条状态改变设置监听事件
        initSeekBar();

        minNextImg.setOnClickListener(this);
        minPlayImg.setOnClickListener(this);
        minListImg.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }

    private void initSeekBar() {
        popSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                popSeekBar.setProgress(index);

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
                popCurrentTimeTv.setText(minuteStr + ":" + secondStr);
            } else if (msg.what == 102) {
                int scrollPos = msg.arg1;
                lyrivLv.smoothScrollToPosition(scrollPos);
            } else if (msg.what == 103) {
                int lvPos = msg.arg1;
                View view = lyrivLv.getChildAt(lvPos);
                if (view != null) {
                    TextView tv = (TextView) view.findViewById(R.id.item_playpage_lyric_tv);
                    tv.setTextColor(Color.WHITE);
                    if (lvPos - 1 >= 0) {
                        View lastView = lyrivLv.getChildAt(lvPos - 1);
                        TextView lastTv = (TextView) lastView.findViewById(R.id.item_playpage_lyric_tv);
                        lastTv.setTextColor(Color.DKGRAY);
                    }
                }
            }
            return false;
        }
    });

    @Override
    protected void initDatas() {
        //替换占位布局
        replaceFrameLayout();
        //注册广播接收者
        initRegisterReceivers();

        //设置初始播放模式
        initPlayMode();

        //绑定服务
        intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    //替换占位布局
    private void replaceFrameLayout() {
        //刚开始替换为占位布局
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout, MainFragment.newInstance());
        ft.commit();
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
        popModeImg.setImageResource(modeIcon[0]);

        TypedArray minPlayMode;
        minPlayMode = getResources().obtainTypedArray(R.array.play_list_mode);
        int length = minPlayMode.length();
        minListModeIcon = new int[length];
        for (int j = 0; j < length; j++)
            minListModeIcon[j] = minPlayMode.getResourceId(j, 0);
        minPlayMode.recycle();
        minListPopMode.setImageResource(minListModeIcon[0]);
    }

    /**
     * 在任何界面播放的音乐一改变就会回调该方法,使该界面的播放器播放指定音乐
     */
    @Override
    public void OnChangeMuisc(MusicBean musicBean, int position) {
        if (musicBinder != null) {
            musicBinder.setCurrentMusic(musicBean, position);
            updateUIState();
        }
    }

    private void updateUIState() {
        // 设置音乐
        setMusicTimeInfo();
        // 初始化按钮状态
        initBtnState();
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

    //注册广播接收者
    private void initRegisterReceivers() {
        //我的Fragment广播接收者注册
        frameReceiver = new FrameReceiver();
        IntentFilter ownFilter = new IntentFilter();
        ownFilter.addAction(BaiduMusicValues.THE_ACTION_OWN_LOCAL);
        registerReceiver(frameReceiver, ownFilter);

        //直播Fragment广播接收者注册
        aliveTopRvReceiver = new AliveTopRvReceiver();
        IntentFilter aliveTopRvFilter = new IntentFilter();
        aliveTopRvFilter.addAction(BaiduMusicValues.THE_ACTION_ALIVE_RV);
        registerReceiver(aliveTopRvReceiver, aliveTopRvFilter);

        //在本地音乐歌曲列表播放音乐 广播接收者注册
        localMusicPlayReceiver = new LocalMusicPlayReceiver();
        IntentFilter localFilter = new IntentFilter();
        localFilter.addAction(BaiduMusicValues.THE_ACTION_PLAY_MUSIC);
        registerReceiver(localMusicPlayReceiver, localFilter);

        //点击进入排行详情页
        rankingDetailReceiver = new RankingDetailReceiver();
        IntentFilter rankFilter = new IntentFilter();
        rankFilter.addAction(BaiduMusicValues.THE_ACTION_RANKING_DETAIL);
        registerReceiver(rankingDetailReceiver, rankFilter);

        //PopWindow改变歌手歌名注册广播
        playPageReceiver = new PlayPageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
        registerReceiver(playPageReceiver, filter);

        //歌单详情页广播接收者
        songDetailReceiver = new SongDetailReceiver();
        IntentFilter songDetailFilter = new IntentFilter();
        songDetailFilter.addAction(BaiduMusicValues.THE_ACTION_SONG_TO_DETAIL);
        registerReceiver(songDetailReceiver, songDetailFilter);

        //广播接收者 进K歌详情
        kDeatilReceiver = new KDeatilReceiver();
        IntentFilter kDeatailFilter = new IntentFilter();
        kDeatailFilter.addAction(BaiduMusicValues.THE_ACTION_K_TO_DETAIL);
        registerReceiver(kDeatilReceiver, kDeatailFilter);

        //广播接收者 推荐页面 歌手详情页
        recommendDetailSongerReceiver = new RecommendDetailSongerReceiver();
        IntentFilter songerFilter = new IntentFilter();
        songerFilter.addAction(BaiduMusicValues.THE_ACTION_RECOMMEND_SONGER);
        registerReceiver(recommendDetailSongerReceiver, songerFilter);

        //MainFragment 到LoginFragment
        mainFragmentToLoginReceiver = new MainFragmentToLoginReceiver();
        IntentFilter loginFilter = new IntentFilter();
        loginFilter.addAction(BaiduMusicValues.THE_ACTION_TO_LOGIN);
        registerReceiver(mainFragmentToLoginReceiver, loginFilter);
    }

    //广播接收者  从OwnFragment发送
    class FrameReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int i = intent.getIntExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (i) {
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO:
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame_layout, LocalMusicDetailsFragment.newInstance());
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE:
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame_layout, LocalMusicDetailsFragment.newInstance());
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_TWO:
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame_layout, LocalMusicDetailsFragment.newInstance());
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_THREE:
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame_layout, LocalMusicDetailsFragment.newInstance());
                    break;
            }
            fragmentTransaction.commit();
        }
    }

    //广播接收者注册 直播Fragment发送
    class AliveTopRvReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.main_frame_layout, AliveRvDetailFragment.newInstance());
            ft.commit();
        }
    }

    //在本地音乐歌曲列表播放音乐 广播接收者注册
    class LocalMusicPlayReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String song = intent.getStringExtra(BaiduMusicValues.PLAY_MUSIC_SONG);
            String singer = intent.getStringExtra(BaiduMusicValues.PLAY_MUSIC_SINGER);
            minSongTv.setText(song);
            minSingerTv.setText(singer);
        }
    }

    //点击进入排行详情页
    class RankingDetailReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra(BaiduMusicValues.RANKING_DETAIL_KET_TYPE, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.main_frame_layout, RankingDetailFragment.newInstance(type));
            ft.commit();
        }
    }

    //广播接收者 改变PopWindow歌手, 歌曲, 时长
    private class PlayPageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            setMusicTimeInfo();
        }
    }

    //广播接收者 进入歌单详情
    private class SongDetailReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getBundleExtra("bundle");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, SongDetailFragment.newInstance(bundle));
            ft.commit();
        }
    }

    //广播接收者 进K歌详情
    private class KDeatilReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int pos = intent.getIntExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (pos) {
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, KDetailFragment.newInstance(BaiduMusicValues.K_KTV, BaiduMusicValues.K_KTVS));
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_TWO:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, KDetailFragment.newInstance(BaiduMusicValues.K_CHINA, BaiduMusicValues.K_CHINAS));
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_THREE:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, KDetailFragment.newInstance(BaiduMusicValues.K_USA, BaiduMusicValues.K_USAS));
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_FOUR:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, KDetailFragment.newInstance(BaiduMusicValues.K_KTV, BaiduMusicValues.K_MAN));
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_FIVE:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, KDetailFragment.newInstance(BaiduMusicValues.K_KTV, BaiduMusicValues.K_WOMAN));
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_SIX:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, KDetailFragment.newInstance(BaiduMusicValues.K_KTV, BaiduMusicValues.K_TEAM));
                    break;
            }
            ft.commit();
        }
    }

    //广播接收者 推荐页面 歌手详情页
    private class RecommendDetailSongerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int pos = intent.getIntExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            switch (pos) {
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, RecommendDetailSongerFragment.newInstance());
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, RecommendDetailSongerFragment.newInstance());
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_TWO:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, RecommendDetailSongerFragment.newInstance());
                    break;
            }
            ft.commit();
        }
    }

    //广播接收者 到登录界面
    private class MainFragmentToLoginReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(null);
            ft.replace(R.id.main_frame_layout, LoginFragment.newInstance());
            ft.commit();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_play:    //播放键
                if (musicBinder != null) {
                    musicBinder.pauseMusic();
                    setMusicTimeInfo();
                    //初始化notification
                    initNotifocation();
                }
                break;
            case R.id.main_next:    //下一曲
                if (musicBinder != null) {
                    musicBinder.nextMusic();
                    setMusicTimeInfo();
                }
                break;
            case R.id.main_playinglist:     //播放列表, 弹出minBar播放列表PopWindow
                showMinListPopWin();
                break;
            case R.id.main_play_layout:     //播放栏, 点击弹出PopWindow播放界面
                //初始化PopWindow并显示
                showPopWindow();
                //初始化PopWindow数据
                initPopWindowData();
                break;
            case R.id.play_music_form_img:  //PopWindow播放模式:循环, 随机, 单曲等等
                currentMode++;
                currentMode = currentMode % modeIcon.length;
                popModeImg.setImageResource(modeIcon[currentMode]);
                musicBinder.changePlayMode(currentMode);
                Toast.makeText(this, modeName[currentMode], Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_music_past_img:  //PopWindow上一曲
                if (musicBinder != null) {
                    musicBinder.pastMusic();
                    //设置进度条的最大值
                    popSeekBar.setMax(musicBinder.getCurrentDruation());
                    //设置歌曲时间
                    setMusicTimeInfo();
                }
                break;
            case R.id.play_music_play_img:  //PopWindow开始/暂停
                if (musicBinder != null) {
                    musicBinder.pauseMusic();
                }
                break;
            case R.id.play_music_next_img:  //PopWindow下一曲
                if (musicBinder != null) {
                    musicBinder.nextMusic();
                    //设置进度条的最大值
                    popSeekBar.setMax(musicBinder.getCurrentDruation());
                    //设置歌曲时间
                    setMusicTimeInfo();
                }
                break;
            case R.id.play_music_list_img:  //PopWindow播放列表
                break;
            case R.id.play_music_back_img:
                popupWindow.dismiss();
                break;
            case R.id.play_music_lyric_img:  // PopWindow切换歌/词
                if (flag == false) {
                    popLyricImg.setImageResource(R.mipmap.bt_playpage_button_pic_normal);
                    popVp.setCurrentItem(2);
                    flag = true;
                } else if (flag == true) {
                    popLyricImg.setImageResource(R.mipmap.bt_playpage_button_lyric_normal);
                    popVp.setCurrentItem(1);
                    flag = false;
                }
                break;
            case R.id.min_list_pop_clear_all_tv:       //min PopWindow全部清除
                break;
            case R.id.min_list_pop_isplaying_btn:   //正在播放列表, 点击退出PopWindow
                minPopWin.dismiss();
                break;
            case R.id.min_list_pop_mode_img:    //min PopWindow播放模式
                currentMode++;
                currentMode = currentMode % minListModeIcon.length;
                minListPopMode.setImageResource(minListModeIcon[currentMode]);
                musicBinder.changePlayMode(currentMode);
                Toast.makeText(this, modeName[currentMode], Toast.LENGTH_SHORT).show();
                break;
        }
        //设置播放键状态
        initBtnState();
    }

    //初始化notification
    private void initNotifocation() {
        //管理者
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //设置内容
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.notify_music_title, musicBinder.getCurrentMusicBean().getSonginfo().getTitle());
        remoteViews.setTextColor(R.id.notify_music_title, Color.BLACK);
        remoteViews.setTextViewText(R.id.notify_music_singer, musicBinder.getCurrentMusicBean().getSonginfo().getAuthor());
        remoteViews.setTextColor(R.id.notify_music_singer, Color.BLACK);
        remoteViews.setImageViewResource(R.id.notify_music_icon, R.mipmap.ic_launcher);
//        remoteViews.setImageViewResource(R.id.notify_start, R.mipmap.start);
//        remoteViews.setImageViewResource(R.id.notify_next, R.mipmap.next);
//        remoteViews.setImageViewResource(R.id.notify_stop, R.mipmap.stop);
        //延时意图
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pi1 = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.notify_img, pi1);
        builder.setContent(remoteViews);
        Notification notification = builder.build();
        manager.notify(2, notification);
    }

    private void showMinListPopWin() {
        minPopWin = new PopupWindow(WindowManager.LayoutParams.MATCH_PARENT, ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 2);
        minPopWin.setContentView(minListPopView);
        minPopWin.setFocusable(true);
        minPopWin.setOutsideTouchable(true);
        minPopWin.setBackgroundDrawable(new ColorDrawable(0));
        minPopWin.showAtLocation(main_ll, Gravity.BOTTOM, 0, 200);
    }

    private void initBtnState() {
        if (musicBinder.getMusicIsPlaying()) {
            popPlayImg.setSelected(true);
            minPlayImg.setImageResource(R.mipmap.bt_minibar_play_normal);
        } else {
            popPlayImg.setSelected(false);
            minPlayImg.setImageResource(R.mipmap.bt_minibar_pause_normal);
        }
    }

    //设置最大时长和歌曲信息
    private void setMusicTimeInfo() {
        MusicBean bean = musicBinder.getCurrentMusicBean();
        //时间需要改成 00:00  分秒形式
        long duration = bean.getBitrate().getFile_duration();
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
        popDurationTv.setText(minuteStr + ":" + secondStr);
        String song = bean.getSonginfo().getTitle();
        String singer = bean.getSonginfo().getAuthor();
        minSongTv.setText(song);

        // 设置迷你播放栏
        if (bean.getSonginfo().getPic_radio() != null && !bean.getSonginfo().getPic_radio().equals("")) {
            Picasso.with(MainActivity.this).load(bean.getSonginfo().getPic_radio()).into(minBgIcon);
            requestMusicImg(bean);
        } else {
            //如果没有图片则设置为默认
            minBgIcon.setImageResource(R.mipmap.img_recommend_lebo_orange);
        }
        minSingerTv.setText(singer);
        popSonVgTv.setText(song);
        popSingerTv.setText(singer);
        popSeekBar.setMax(bean.getBitrate().getFile_duration());
    }

    /**
     * 初始化PopWindow并显示
     */
    private void showPopWindow() {
        popupWindow = new PopupWindow(
                WindowManager.LayoutParams.MATCH_PARENT,
                ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) - getStatusBarHeight());
        BitmapDrawable bg = new BitmapDrawable(getResources(),
                AeroGlassUtil.doBlur(BitmapFactory.decodeResource(getResources(), R.mipmap.lunbo), 80, false));
        popupWindow.setBackgroundDrawable(bg);
        popupWindow.setContentView(popView);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(main_ll, Gravity.NO_GRAVITY, 0, getStatusBarHeight());
    }

    /**
     * 初始化PopWindow数据
     */
    private void initPopWindowData() {
        linearLayouts = new ArrayList<>();
        playPageLinearPagerAdapter = new PlayPageLinearPagerAdapter(MainActivity.this);

        lyricLvAdapter = new PlayPageLyricLvAdapter(MainActivity.this);
        lyrivLv.setAdapter(lyricLvAdapter);
        // 获取歌词数据
        if (!musicBinder.isLocalMusic()) {
            VolleyInstance.getInstance().startResult(BaiduMusicValues.SONG_ULR_HEAD + musicBinder.getCurrentMusicBean().getSonginfo().getSong_id() + BaiduMusicValues.SONG_URL_FOOT, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    resultStr = fixJsonData(resultStr);
                    // 获得歌曲实体类
                    Gson gson = new Gson();
                    MusicBean musicBean = gson.fromJson(resultStr, MusicBean.class);
                    int duration = musicBean.getBitrate().getFile_duration();
                    musicBean.getBitrate().setFile_duration(duration * 1000);
                    // 添加歌曲图片和文字
                    if (!musicBean.getSonginfo().getPic_premium().equals(""))
                        Picasso.with(MainActivity.this).load(musicBean.getSonginfo().getPic_premium()).into(playPageCenterBgImg);
                    // 请求图片
                    requestMusicImg(musicBean);
                }

                @Override
                public void failure() {

                }
            });
        }
        linearLayouts.add(playPageLl);
        linearLayouts.add(playPageLyrivLl);
        linearLayouts.add(playPageLl);
        // 适配器设置数据
        playPageLinearPagerAdapter.setDatas(linearLayouts);
        popVp.setAdapter(playPageLinearPagerAdapter);
        popTb.setupWithViewPager(popVp);
        popVp.setCurrentItem(1);
        //滑动时歌/词切换
        popVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    popLyricImg.setImageResource(R.mipmap.bt_playpage_button_pic_press);
                } else {
                    popLyricImg.setImageResource(R.mipmap.bt_playpage_button_lyric_press);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置PopWindow背景图片和中间的图片
     *
     * @param musicBean
     */
    private void requestMusicImg(MusicBean musicBean) {
        ImageRequest imageRequest = new ImageRequest(musicBean.getSonginfo().getPic_premium(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                playPageCenterBgImg.setImageBitmap(bitmap);
                bitmap = AeroGlassUtil.doBlur(bitmap, 40, false);
                popBgImg.setImageBitmap(bitmap);
            }
        }, 2000, 2000, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(imageRequest);
        //请求歌词
        requestLyric(musicBean);
    }

    /**
     * 请求歌词
     */
    private void requestLyric(MusicBean musicBean) {
        VolleyInstance.getInstance().startResult(musicBean.getSonginfo().getLrclink(), new VolleyResult() {
            @Override
            public void success(String resultStr) {
                // 解决中文乱码问题
                String praseResult = "";
                try {
                    praseResult = new String(resultStr.getBytes("ISO8859-1"), "utf-8"); // 解决中文乱码问题
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                // 把整个歌词 按行分割成 单行歌词实体
                List<LyricBean> stringList = new ArrayList<>();
                // 保存上一行的结束(\n)位置
                int lastLine = 0;
                // 按行分割
                for (int i = 0; i < praseResult.length(); i++) {
                    if (praseResult.charAt(i) == '\n') {    // 如果遍历到回车符就截取该行
                        int min = 0;
                        int sec = 0;
                        int msec10 = 0;
                        String pieceAll = praseResult.substring(lastLine, i);   // 单行歌词
                        String piece = "";
                        // 去掉时间的单行歌词
                        if (pieceAll.length() >= 10 && Character.isDigit(pieceAll.charAt(1))) {
                            min = Integer.parseInt(pieceAll.substring(1, 3));   // 时间项的获取
                            sec = Integer.parseInt(pieceAll.substring(4, 6));
                            msec10 = Integer.parseInt(pieceAll.substring(7, 9));
                            for (int l = 0; l < pieceAll.length(); l++) {
                                if (pieceAll.charAt(l) == ']') {
                                    piece = pieceAll.substring(l + 1, pieceAll.length());
                                    break;
                                }
                            }
                        } else {
                            lastLine = i + 1;   // 保存该行的结束位置
                            continue;
                        }
                        stringList.add(new LyricBean(piece, min, sec, msec10)); // 把拆分的数据封装到实体类,便于操作
                        lastLine = i + 1;   // 保存该行的结束位置
                    }
                }
                if (lyricLvAdapter != null)
                    lyricLvAdapter.setDatas(stringList);
            }

            @Override
            public void failure() {
                Toast.makeText(MainActivity.this, "请求歌词失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int lastPos = 0;

    public void OnColorChanged(int pos) {
        if (lastPos != pos) {
            Message colorMessage = new Message();
            colorMessage.what = 103;
            colorMessage.arg1 = pos;
            handler.sendMessage(colorMessage);
            lastPos = pos;
        } else {
            lastPos = pos;
        }
    }

    /**
     * 控制歌词同步的线程
     */
    private class NotifySynLyricThread extends Thread {
        public boolean isRun = true;

        @Override
        public void run() {
            while (isRun) {
                if (musicBinder != null && musicBinder.getMusicIsPlaying()) {
                    if (lyricLvAdapter != null) {
                        for (int i = 0; i < lyricLvAdapter.getCount() - 1; i++) {
                            LyricBean beanLast = lyricLvAdapter.getItem(i);
                            LyricBean beanNext = lyricLvAdapter.getItem(i + 1);
                            int lastSec = beanLast.getSeconds();
                            int nextSec = beanNext.getSeconds();
                            long seek = musicBinder.getCurrentMusicPosition();
                            int seekSec = (int) (seek / 1000);
                            if (seekSec >= lastSec && seekSec <= nextSec) {
                                lyrivLv.smoothScrollToPositionFromTop(i + 1, lyrivLv.getMeasuredHeight() / 2);
                                OnColorChanged(i);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取屏幕顶部标题栏高度
     *
     * @return 标题栏高度
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @NonNull
    public static String fixJsonData(String resultStr) {
        // 数据格式错误,修正
        StringBuffer stringBuffer = new StringBuffer(resultStr);
        stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());
        stringBuffer.delete(0, 1);
        resultStr = stringBuffer.toString();
        return resultStr;
    }

    ///////////////////////////
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }
    };

    //当按下BACK键时，会被onKeyDown捕获，判断是BACK键，则执行exit方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    //首先判断isExit的值，如果为false的话，则置为true，
    //同时会弹出提示，并在2000毫秒（2秒）后发出一个消息，在Handler中将此值还原成false
    //如果在发送消息间隔的2秒内，再次按了BACK键，则再次执行exit方法，此时isExit的值已为true，则会执行退出的方法
    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), R.string.exit_app, Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }
///////////////////////////////

    //广播接收者取消注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(frameReceiver);
        unregisterReceiver(aliveTopRvReceiver);
        unregisterReceiver(localMusicPlayReceiver);
        unregisterReceiver(rankingDetailReceiver);
        unregisterReceiver(playPageReceiver);
        unregisterReceiver(songDetailReceiver);
        unregisterReceiver(kDeatilReceiver);
        unregisterReceiver(recommendDetailSongerReceiver);
        unregisterReceiver(mainFragmentToLoginReceiver);
        unbindService(serviceConnection);
        if (musicBinder != null) {
            musicBinder.stopMusic();
        }
        musicBinder = null;
    }

}
