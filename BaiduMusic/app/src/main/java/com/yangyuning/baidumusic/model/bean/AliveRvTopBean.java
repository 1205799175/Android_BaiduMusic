package com.yangyuning.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/15.
 * 直播 上部 RecyclerView实体类
 */
public class AliveRvTopBean {

    /**
     * error_code : 22000
     * data : [{"category_id":"hot","category_name":"全部","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_all.png?v=1693395271","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_all_normal.png?v=1914967670","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_all_press.png?v=867587724"},{"category_id":"4","category_name":"女神","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_goddess.png?v=1965148136","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_goddess_normal.png?v=917505715","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_goddess_press.png?v=464232724"},{"category_id":"3","category_name":"好声音","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_voice.png?v=492436081","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_voice_normal.png?v=1042414847","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_voice_press.png?v=1461553629"},{"category_id":"new","category_name":"新秀","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_rookie.png?v=790805988","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_rookie_normal.png?v=1214596681","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_rookie_press.png?v=658343906"},{"category_id":"2","category_name":"劲爆","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_madden.png?v=2115691791","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_madden_normal.png?v=1836114806","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_madden_press.png?v=1386700457"},{"category_id":"15","category_name":"搞笑","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_happy.png?v=813813453","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_happy_normal.png?v=278548558","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_happy_press.png?v=623794979"},{"category_id":"1","category_name":"萌妹","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_sister.png?v=1000924650","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_sister_normal.png?v=1584951519","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_sister_press.png?v=308746771"},{"category_id":"recommend","category_name":"推荐","img_url":"http://img.static.9xiu.com/public/yy_category/ic_live_recommend.png?v=1918150448","img_url_normal":"http://img.static.9xiu.com/public/yy_category/ic_live_recommend_normal.png?v=848636766","img_url_press":"http://img.static.9xiu.com/public/yy_category/ic_live_recommend_press.png?v=249727"}]
     */

    private int error_code;
    /**
     * category_id : hot
     * category_name : 全部
     * img_url : http://img.static.9xiu.com/public/yy_category/ic_live_all.png?v=1693395271
     * img_url_normal : http://img.static.9xiu.com/public/yy_category/ic_live_all_normal.png?v=1914967670
     * img_url_press : http://img.static.9xiu.com/public/yy_category/ic_live_all_press.png?v=867587724
     */

    private List<DataBean> data;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String category_id;
        private String category_name;
        private String img_url;
        private String img_url_normal;
        private String img_url_press;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getImg_url_normal() {
            return img_url_normal;
        }

        public void setImg_url_normal(String img_url_normal) {
            this.img_url_normal = img_url_normal;
        }

        public String getImg_url_press() {
            return img_url_press;
        }

        public void setImg_url_press(String img_url_press) {
            this.img_url_press = img_url_press;
        }
    }
}
