package com.yangyuning.baidumusic.controller.services;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 * 服务 控制音乐
 */
public class MusicService extends Service {
    private MusicBinder musicBinder;
    private List<OwnLocalMusicLvBean> datas;
    //当前歌曲
    private int currentIndex = 0;
    //播放器
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    public class MusicBinder extends Binder {
        //获取音乐集合
        public List<OwnLocalMusicLvBean> getListViewDatas() {
            if (datas != null) {
                return datas;
            } else {
                return null;
            }
        }

        //播放音乐
        public void playMusic() {
            OwnLocalMusicLvBean currentMusic = datas.get(currentIndex);
            //获得当前歌曲路径
            String path = currentMusic.getUrl();
            //重置
            mediaPlayer.reset();
            try {
                //设置路径给音乐播放器
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //暂停音乐
        public void pauseMusic(){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }

        //获取当前音乐实体类
        public OwnLocalMusicLvBean getCurrentMusicBean() {
            return datas.get(currentIndex);
        }

        //下一曲
        public void nextMusic(){
            currentIndex++;
            if (currentIndex == datas.size()){
                currentIndex = 0;
            }
            mediaPlayer.reset();
            String path = datas.get(currentIndex).getUrl();
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //上一曲
        public void pastMusic(){
            currentIndex--;
            if (currentIndex < 0){
                currentIndex = datas.size() - 1;
            }
            mediaPlayer.reset();
            String path = datas.get(currentIndex).getUrl();
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //获取当前音乐播放状态
        public boolean getMusicIsPlaying(){
            return mediaPlayer.isPlaying();
        }

        //获取当前音乐时长
        public int getCurrentDruation(){
            return mediaPlayer.getDuration();
        }

        //获取当前音乐播放进度
        public int getCurrentMusicPosition(){
            return mediaPlayer.getCurrentPosition();
        }

        //快进快退
        public void musicForwardAndBack(int process){
            mediaPlayer.seekTo(process);
        }

        //退出应用, 停止音乐播放
        public void stopMusic(){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicBinder = new MusicBinder();

        //获取歌曲
        getLocalMusicInfo();

        //初始化播放器
        mediaPlayer = new MediaPlayer();
    }

    //获取歌曲
    private void getLocalMusicInfo() {
        datas = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
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
}
