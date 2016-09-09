package com.yangyuning.baidumusic.model.bean;

import android.widget.ImageView;

/**
 * Created by dllo on 16/9/9.
 */
public class OwnLvBean {
    private int imgId;
    private String name;
    private String number;

    public OwnLvBean() {
    }

    public OwnLvBean(int imgId, String name, String number) {
        this.imgId = imgId;
        this.name = name;
        this.number = number;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
