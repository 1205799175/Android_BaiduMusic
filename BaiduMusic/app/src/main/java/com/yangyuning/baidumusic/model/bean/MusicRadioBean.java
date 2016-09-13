package com.yangyuning.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/12.
 * 电台Fragment RecyclerView实体类
 */
public class MusicRadioBean {
    private String title;   //推荐电台名称
    private int imgId;    //推荐电台图标

    public MusicRadioBean() {
    }

    public MusicRadioBean(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
