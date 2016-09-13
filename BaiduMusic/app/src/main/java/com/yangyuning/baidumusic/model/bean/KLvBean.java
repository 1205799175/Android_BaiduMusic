package com.yangyuning.baidumusic.model.bean;

/**
 * Created by dllo on 16/9/13.
 * K Fragment的ListView行布局实体类
 */
public class KLvBean {
    private String name;
    private String tiem;

    public KLvBean() {
    }

    public KLvBean(String name, String tiem) {
        this.name = name;
        this.tiem = tiem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTiem() {
        return tiem;
    }

    public void setTiem(String tiem) {
        this.tiem = tiem;
    }
}
