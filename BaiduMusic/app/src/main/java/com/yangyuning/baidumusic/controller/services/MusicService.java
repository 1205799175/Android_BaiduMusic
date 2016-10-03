package com.yangyuning.baidumusic.controller.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 16/9/20.
 * 服务 控制音乐
 */
public class MusicService extends Service {
    public static MusicBinder musicBinder;
    private static List<MusicBean> datas;
    private static boolean isLocalMusic = true; //判断是否为本地歌曲

    //当前歌曲
    private int currentIndex = 0;
    //播放器
    private MediaPlayer mediaPlayer;

    //播放模式
    private int currentPlayMode = 0;
    //定义播放模式的数组
    private PLAY_MODE[] playMode = {PLAY_MODE.RANDOM, PLAY_MODE.ORDER, PLAY_MODE.ROUNDSINGLE, PLAY_MODE.LOOP};

    public static void setDatas(List<MusicBean> datas){
        MusicService.datas = datas;
        isLocalMusic = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicBinder = new MusicBinder();
        //初始化播放器
        mediaPlayer = new MediaPlayer();
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        //获取歌曲
        datas = getLocalMusicInfo();
        //完成监听 自动播放下一首
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 处理下一首
                if (playMode[currentPlayMode] == PLAY_MODE.ROUNDSINGLE) {    //单曲循环
                    musicBinder.playMusicByMode(currentIndex);
                }
                if (playMode[currentPlayMode] == PLAY_MODE.ORDER) {  //顺序播放
                    musicBinder.nextMusixORDER();
                }
                if (playMode[currentPlayMode] == PLAY_MODE.RANDOM) { //随机播放
                    currentIndex = randomPosition();
                    musicBinder.playMusicByMode(currentIndex);
                }
                if (playMode[currentPlayMode] == PLAY_MODE.LOOP) {  //循环播放
                    musicBinder.nextMusic();
                }
                if (currentIndex != datas.size()) {     //发送广播, 通知数据改变, 歌名, 歌手
                    Intent intent = new Intent();
                    intent.setAction(BaiduMusicValues.THE_ACTION_PLAY_PAGE_PLAY);
                    sendBroadcast(intent);
                }
            }
        });
    }

    //获得随机播放的随机数
    private int randomPosition() {
        Random random = new Random(System.currentTimeMillis());
        double randomDouble = random.nextDouble();
        double rate = randomDouble / 1.0;
        int cur = (int) (rate * datas.size());
        if (cur == currentIndex) {
            cur = random.nextInt(3);
        }
        return cur;
    }


    public class MusicBinder extends Binder {
        //获取音乐集合
        public List<MusicBean> getListViewDatas() {
            if (datas != null) {
                return datas;
            } else {
                return null;
            }
        }

        /**
         * 播放音乐
         */
        public void playMusic() {
            try {
                mediaPlayer.reset();
                //设置路径给音乐播放器
                mediaPlayer.setDataSource(datas.get(currentIndex).getBitrate().getFile_link());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 暂停音乐
         */
        public void pauseMusic() {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }

        /**
         * 下一曲
         */
        public void nextMusic() {
            // 根据播放模式获得下一个将要播放的音乐
            currentIndex = getNextPos(1);
            if (currentIndex == datas.size()) {
                currentIndex = 0;
                if (playMode[currentPlayMode] == PLAY_MODE.ORDER) {
                    Toast.makeText(MusicService.this, "顺序播放完成", Toast.LENGTH_SHORT).show();
                    resetPlayer();
                    return;
                }
            }
            playMusic();
        }


        /**
         * 下一曲, 列表播放完不循环
         */
        public void nextMusixORDER() {
            currentIndex++;
            if (currentIndex >= datas.size()) {
                mediaPlayer.pause();
            } else {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(datas.get(currentIndex).getBitrate().getFile_link());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * 上一曲
         */
        public void pastMusic() {
            currentIndex = getNextPos(-1);
            if (currentIndex < 0) {
                currentIndex = datas.size() - 1;
                if (playMode[currentPlayMode] == PLAY_MODE.ORDER) {
                    Toast.makeText(MusicService.this, "没有可播放的歌曲", Toast.LENGTH_SHORT).show();
                    resetPlayer();
                    return;
                }
            }
            playMusic();
        }

        private int getNextPos(int add) {
            int next = 0;
            switch (playMode[currentPlayMode]) {
                case RANDOM:
                    next = randomPosition();
                    break;
                // 上一曲或下一曲用add区分
                case ORDER:
                case LOOP:
                case ROUNDSINGLE:
                    next = currentIndex + add;
                    break;
            }
            return next;
        }

        public void resetPlayer() {
            currentIndex = 0;
            musicBinder.playMusic();
            musicBinder.pauseMusic();
        }

        /**
         * 根据播放模式播放当前音乐
         */
        public void playMusicByMode(int position) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(datas.get(position).getBitrate().getFile_link());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 设置当前音乐并播放
         */
        public void setCurrentMusic(MusicBean songBean, int pos) {
            try {
                currentIndex = pos;
                mediaPlayer.reset();
                mediaPlayer.setDataSource(songBean.getBitrate().getFile_link());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 判断是不是本地音乐
         */
        public boolean isLocalMusic() {
            return isLocalMusic;
        }

        /**
         * 改变播放模式
         */
        public void changePlayMode(int position) {
            currentPlayMode = position;
        }

        /**
         * 获取当前音乐时长
         */
        public int getCurrentDruation() {
            return mediaPlayer.getDuration();
        }

        /**
         * 获取当前音乐播放进度
         */
        public int getCurrentMusicPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        /**
         * 获取当前音乐实体类
         */
        public MusicBean getCurrentMusicBean() {
            return datas.get(currentIndex);
        }

        /**
         * 快进快退
         */
        public void musicForwardAndBack(int process) {
            mediaPlayer.seekTo(process);
        }

        /**
         * 获取当前音乐播放状态
         */
        public boolean getMusicIsPlaying() {
            return mediaPlayer.isPlaying();
        }

        /**
         * 退出应用, 停止音乐播放
         */
        public void stopMusic() {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    //获取歌曲
    public static List<MusicBean> getLocalMusicInfo() {
        datas = new ArrayList<>();
        Cursor cursor = BaiduMusicApp.getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));   //歌曲名
            String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); //歌手
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); //歌曲时长
            String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));      //歌曲地址
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));    //是否是音乐
            String display = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));//专辑
            MusicBean musicBean = new MusicBean();
            //设置歌曲路径, 时长
            MusicBean.BitrateBean bitrateBean = new MusicBean.BitrateBean();
            bitrateBean.setFile_link(url);
            bitrateBean.setFile_duration((int) duration);
            musicBean.setBitrate(bitrateBean);
            //设置歌曲信息
            MusicBean.SonginfoBean songinfoBean = new MusicBean.SonginfoBean();
            songinfoBean.setAuthor(singer);
            songinfoBean.setTitle(title);
            songinfoBean.setAlbum_title(display);
            musicBean.setSonginfo(songinfoBean);
            //添加数据
            datas.add(musicBean);
        }
        cursor.close();
        return datas;
    }

    /**
     * 四种播放模式
     */
    public enum PLAY_MODE {
        LOOP, ORDER, RANDOM, ROUNDSINGLE;   //循环播放, 顺序播放, 随机播放, 单曲循环
    }

}
