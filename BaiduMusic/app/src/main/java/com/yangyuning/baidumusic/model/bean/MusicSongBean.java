package com.yangyuning.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/12.
 */
public class MusicSongBean {
    private String title;
    private String area;

    public MusicSongBean() {
    }

    public MusicSongBean(String title, String area) {
        this.title = title;
        this.area = area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
