package com.yangyuning.baidumusic.model.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/8.
 * 我喜欢的单曲 实体类
 */
public class MyFavoriteSongBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String userName;
    private String songId;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String bgImg;

    public MyFavoriteSongBean() {
    }

    public MyFavoriteSongBean(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public MyFavoriteSongBean(String songId, String title, String author, String bgImg) {
        this.songId = songId;
        this.title = title;
        this.author = author;
        this.bgImg = bgImg;

    }

    public MyFavoriteSongBean(int id, String userName, String songId, String title, String author, String bgImg) {
        this.id = id;
        this.userName = userName;
        this.songId = songId;
        this.title = title;
        this.author = author;
        this.bgImg = bgImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }
}
