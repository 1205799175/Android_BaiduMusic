package com.yangyuning.baidumusic.model.bean;

/**
 * Created by dllo on 16/10/3.
 * 歌词实体类
 */
public class LyricBean {
    private String lyricPiece;
    private int min;
    private int sec;
    private int msec10;

    public LyricBean() {
    }

    public LyricBean(String lyricPiece, int min, int sec, int msec10) {
        this.lyricPiece = lyricPiece;
        this.min = min;
        this.sec = sec;
        this.msec10 = msec10;
    }

    public String getLyricPiece() {
        return lyricPiece;
    }

    public void setLyricPiece(String lyricPiece) {
        this.lyricPiece = lyricPiece;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMsec10() {
        return msec10;
    }

    public void setMsec10(int msec10) {
        this.msec10 = msec10;
    }

    /**
     * 获得毫秒数
     * @return 毫秒数
     */
    public int getMSecond() {
        return (min * 60 + sec) * 1000 + msec10 * 10;
    }

    /**
     * 获得秒数
     * @return 秒数
     */
    public int getSeconds() {
        return min * 60 + sec;
    }

}
