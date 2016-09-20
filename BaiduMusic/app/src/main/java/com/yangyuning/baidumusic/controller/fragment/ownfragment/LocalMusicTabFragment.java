package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
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
    private MediaPlayer player;


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

        //初始化音乐播放器
        player = new MediaPlayer();
    }

    @Override
    protected void initDatas() {
        //添加头布局
//        View view = LayoutInflater.from(context).inflate(R.layout.item_header_local_music_lv, null);
//        listView.addHeaderView(view);

        //获取本机g歌曲信息
        getMusicDatas();
        //初始化适配器
        ownLocalMusicLvAdapter = new OwnLocalMusicLvAdapter(datas, context);
        listView.setAdapter(ownLocalMusicLvAdapter);

        //ListView行点击事件播放歌曲
        playMusic();
    }

    //获取本机歌曲信息
    private void getMusicDatas() {
        datas = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));   //歌曲名
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); //歌手
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); //歌曲时长
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));      //歌曲地址
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));    //是否是音乐
//            Log.d("rrr", "-----" + title);
//            Log.d("rrr", "-----" + singer);
//            Log.d("rrr", "duration:" + duration);
//            Log.d("rrr", "-----" + url);
//            Log.d("rrr", "isMusic:" + isMusic);
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
                //获取当前行的歌曲
                OwnLocalMusicLvBean currentMusic = datas.get(position);
                //获取当前歌曲的路径
                String path = currentMusic.getUrl();
                //重置
                player.reset();
                try {
                    //路径设置给音乐播放器
                    player.setDataSource(path);
                    //准备
                    player.prepare();
                    //开始
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //发送广播
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_PLAY_MUSIC);
                intent.putExtra(BaiduMusicValues.PLAY_MUSIC_SONG, currentMusic.getSong());
                intent.putExtra(BaiduMusicValues.PLAY_MUSIC_SINGER, currentMusic.getSinger());
                context.sendBroadcast(intent);
            }
        });




    }

}
