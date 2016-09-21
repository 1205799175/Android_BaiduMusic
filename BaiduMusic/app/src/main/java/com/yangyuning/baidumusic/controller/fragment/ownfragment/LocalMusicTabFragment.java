package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLocalMusicLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.OwnLvBottomAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * 本地音乐 二级页面中嵌套的Fragment
 */
public class LocalMusicTabFragment extends AbsBaseFragment {

    private ListView listView;
    private OwnLocalMusicLvAdapter ownLocalMusicLvAdapter;
    private List<OwnLocalMusicLvBean> datas;
    //定义音乐播放器
    private MediaPlayer mediaPlayer;

    public static LocalMusicTabFragment newInstance() {
        Bundle args = new Bundle();
        LocalMusicTabFragment fragment = new LocalMusicTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own_local_music_tab;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.local_music_lv);
    }

    @Override
    protected void initDatas() {
        //初始化播放器
        mediaPlayer = new MediaPlayer();
        //添加头布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_header_local_music_lv, null);
        listView.setHeaderDividersEnabled(false);
        listView.addHeaderView(view);

        //获取歌曲
        getLocalMusicInfo();

        //初始化适配器, 设置数据
        ownLocalMusicLvAdapter = new OwnLocalMusicLvAdapter(datas, context);
        listView.setAdapter(ownLocalMusicLvAdapter);

        //ListView行点击事件播放歌曲
        playMusic();
    }

    //获取歌曲
    private void getLocalMusicInfo() {
        datas = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));   //歌曲名
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); //歌手
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); //歌曲时长
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));      //歌曲地址
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));    //是否是音乐
            if (isMusic != 0){
                OwnLocalMusicLvBean musicBean = new OwnLocalMusicLvBean(title, singer, duration, url);
                datas.add(musicBean);
            }
        }
        cursor.close();
    }

    //ListView行点击事件播放歌曲
    private void playMusic() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("aaa", "position:" + position);
                if (position == 0){

                } else {
                    try {
                        //因为头布局, pos减1
                        OwnLocalMusicLvBean currentMusic = datas.get(position - 1);
                        String path = currentMusic.getUrl();
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(path);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        //发送广播
                        Intent intent = new Intent();
                        intent.setAction(BaiduMusicValues.THE_ACTION_PLAY_MUSIC);
                        intent.putExtra(BaiduMusicValues.PLAY_MUSIC_SONG, datas.get(position - 1).getSong());
                        intent.putExtra(BaiduMusicValues.PLAY_MUSIC_SINGER, datas.get(position - 1).getSinger());
                        context.sendBroadcast(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
