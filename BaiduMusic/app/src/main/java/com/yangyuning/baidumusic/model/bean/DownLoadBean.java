package com.yangyuning.baidumusic.model.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/9.
 * 下载实体类
 */
public class DownLoadBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String title;
    private String author;
    private String songId;

    public DownLoadBean() {
    }

    public DownLoadBean(String title, String author, String songId) {
        this.title = title;
        this.author = author;
        this.songId = songId;
    }

    public DownLoadBean(int id, String title, String author, String songId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
