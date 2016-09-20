package com.yangyuning.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/19.
 * 我的Fragment 本地歌曲 二级页面 ListView数据实体类
 */
public class OwnLocalMusicLvBean {
    private String song;    //歌名
    private String singer;  //歌手
    private long duration;  //时长
    private String url;     //歌曲路径

    public OwnLocalMusicLvBean() {
    }

    public OwnLocalMusicLvBean(String song, String singer, long duration, String url) {
        this.song = song;
        this.singer = singer;
        this.duration = duration;
        this.url = url;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
