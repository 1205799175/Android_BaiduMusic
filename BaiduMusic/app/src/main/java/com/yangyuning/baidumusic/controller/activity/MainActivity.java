package com.yangyuning.baidumusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveFragment;
import com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveRvDetailFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.LocalMusicDetailsFragment;
import com.yangyuning.baidumusic.controller.fragment.MainFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.OwnFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

public class MainActivity extends AbsBaseActivity {
    private FrameLayout frameLayout;
    private TextView songTv, singerTv;
    private ImageView iconImg, nextImg, playImg, listImg;

    private FrameReceiver frameReceiver;
    private AliveTopRvReceiver aliveTopRvReceiver;
    private LocalMusicPlayReceiver localMusicPlayReceiver;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        frameLayout = byView(R.id.main_frame_layout);
        songTv = byView(R.id.main_song);
        singerTv = byView(R.id.main_singer);
        iconImg = byView(R.id.main_song_icon);
        nextImg = byView(R.id.main_next);
        playImg = byView(R.id.main_play);
        listImg = byView(R.id.main_playinglist);
    }

    @Override
    protected void initDatas() {
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

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout, MainFragment.newInstance());
        ft.commit();
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

    //广播接收者取消注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(frameReceiver);
        unregisterReceiver(aliveTopRvReceiver);
        unregisterReceiver(localMusicPlayReceiver);
    }

}
