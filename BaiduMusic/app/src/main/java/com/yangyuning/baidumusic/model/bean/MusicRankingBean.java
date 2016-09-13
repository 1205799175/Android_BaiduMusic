package com.yangyuning.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class MusicRankingBean {

    private String title;   //例如 新歌榜
    private String songOne, songTwo, songThree;
//    private int imgId;

    public MusicRankingBean() {
    }

    public MusicRankingBean(String title, String songOne, String songTwo, String songThree) {
        this.title = title;
        this.songOne = songOne;
        this.songTwo = songTwo;
        this.songThree = songThree;
//        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSongOne() {
        return songOne;
    }

    public void setSongOne(String songOne) {
        this.songOne = songOne;
    }

    public String getSongTwo() {
        return songTwo;
    }

    public void setSongTwo(String songTwo) {
        this.songTwo = songTwo;
    }

    public String getSongThree() {
        return songThree;
    }

    public void setSongThree(String songThree) {
        this.songThree = songThree;
    }




}
