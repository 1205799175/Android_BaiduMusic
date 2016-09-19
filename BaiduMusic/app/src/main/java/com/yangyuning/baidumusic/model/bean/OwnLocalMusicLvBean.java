package com.yangyuning.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/19.
 * 我的Fragment 本地歌曲 二级页面 ListView数据实体类
 */
public class OwnLocalMusicLvBean {
    private String song;    //歌名
    private String singer;  //歌手

    public OwnLocalMusicLvBean() {
    }

    public OwnLocalMusicLvBean(String song, String singer) {
        this.song = song;
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
