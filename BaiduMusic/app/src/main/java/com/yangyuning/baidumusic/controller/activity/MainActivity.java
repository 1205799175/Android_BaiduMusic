package com.yangyuning.baidumusic.controller.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.VpAdapter;
import com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveFragment;
import com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveRvDetailFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.LocalMusicDetailsFragment;
import com.yangyuning.baidumusic.controller.fragment.MainFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.OwnFragment;
import com.yangyuning.baidumusic.controller.fragment.playpagefragment.PlayPageLyricFragment;
import com.yangyuning.baidumusic.utils.AeroGlassUtil;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsBaseActivity implements View.OnClickListener {
    private TextView songTv, singerTv;  //底部播放栏歌手名, 歌曲名
    private ImageView nextImg, playImg, listImg;    //底部播放栏按钮
    private LinearLayout linearLayout, main_ll;  //底部播放栏线性布局, main整体布局的线性布局

    private boolean isExit; // app退出标志位

    //弹出PopWindow相关内容
    private View popView;   //PopWindow布局
    private List<Fragment> fragments;   //PopWindow中三个fragment
    private ViewPager popVp;
    private VpAdapter popVpAdapter;
    private TextView popSonVgTv, popSingerTv;    //PopWindow上方歌名歌手
    private ImageView popBackImg, popLyricImg;  //返回, 词
    private ImageView popModeImg, popPastImg, popPlayImg, popNextImg, popListImg;   //PopWindow内播放模式, 上一曲,播放, 下一曲, 播放列表
    private SeekBar popSeekBar; //进度条
    private TextView popCurrentTimeTv, popDurationTv;   //进度条时间

    //广播接收者
    private FrameReceiver frameReceiver;
    private AliveTopRvReceiver aliveTopRvReceiver;
    private LocalMusicPlayReceiver localMusicPlayReceiver;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        songTv = byView(R.id.main_song);
        singerTv = byView(R.id.main_singer);
        nextImg = byView(R.id.main_next);
        playImg = byView(R.id.main_play);
        listImg = byView(R.id.main_playinglist);
        linearLayout = byView(R.id.main_play_layout);
        main_ll = byView(R.id.main_ll);
        //PopWindow
        popView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_play_music, null);
        popVp = (ViewPager) popView.findViewById(R.id.play_page_vp);
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

        nextImg.setOnClickListener(this);
        playImg.setOnClickListener(this);
        listImg.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
    }
    @Override
    protected void initDatas() {
        //替换占位布局
        replaceFrameLayout();
        //注册广播接收者
        initRegisterReceivers();
    }

    //替换占位布局
    private void replaceFrameLayout() {
        //刚开始替换为占位布局
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout, MainFragment.newInstance());
        ft.commit();
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
    }

    //广播接收者  从OwnFragment发送
    class FrameReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int i = intent.getIntExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (i) {
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE:
                    fragmentManager.popBackStack();
                    break;
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
            int pos = intent.getIntExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            switch (pos) {
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE:
                    fm.popBackStack();
                    break;
                default:
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_frame_layout, AliveRvDetailFragment.newInstance());
                    break;
            }
            ft.commit();
        }
    }

    //在本地音乐歌曲列表播放音乐 广播接收者注册
    class LocalMusicPlayReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String song = intent.getStringExtra(BaiduMusicValues.PLAY_MUSIC_SONG);
            String singer = intent.getStringExtra(BaiduMusicValues.PLAY_MUSIC_SINGER);
            songTv.setText(song);
            singerTv.setText(singer);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_play:    //播放键
                break;
            case R.id.main_next:    //下一曲
                break;
            case R.id.main_playinglist:     //播放列表
                break;
            case R.id.main_play_layout:     //播放栏, 点击弹出PopWindow播放界面
                //初始化PopWindow并显示
                showPopWindow(v);
                //初始化PopWindow数据
                initPopWindowData();
                break;
        }
    }

    /**
     * 初始化PopWindow并显示
     */
    private void showPopWindow(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        PopupWindow popupWindow = new PopupWindow(
                WindowManager.LayoutParams.MATCH_PARENT,
                ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) - getStatusBarHeight());
        BitmapDrawable bg = new BitmapDrawable(getResources(),
                AeroGlassUtil.doBlur(BitmapFactory.decodeResource(getResources(), R.mipmap.lunbo), 80, false)
        );
        popupWindow.setBackgroundDrawable(bg);
        popupWindow.setContentView(popView);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(main_ll, Gravity.NO_GRAVITY, 0, getStatusBarHeight());
    }

    /**
     * 初始化PopWindow数据
     */
    private void initPopWindowData() {
        fragments = new ArrayList<>();
        fragments.add(PlayPageLyricFragment.newInstance());
        fragments.add(PlayPageLyricFragment.newInstance());
        fragments.add(PlayPageLyricFragment.newInstance());
        popVpAdapter = new VpAdapter(getSupportFragmentManager(), fragments);
        popVp.setAdapter(popVpAdapter);
        popVp.setCurrentItem(1);
    }

    /**
     * 获取屏幕顶部标题栏高度
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
    public void exit(){
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
    }

}
