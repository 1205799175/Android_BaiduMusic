package com.yangyuning.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/16.
 * 乐库 推荐 轮播图 实体类
 */
public class MusicRecommendRotateBean {

    /**
     * pic : [{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14739919991edc043c9d68ff3f54fc36b3aa832d0c.jpg","randpic_ios5":"","randpic_desc":"新歌榜","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147399201822a8d3c4634be7abf0e5684ac41c2915.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473992027f6ab9bf9d0707cd79e1987d9ce02fd73.jpg","randpic_2":"bos_client_1473992009555c08f13553cb0b69223d25ef67d7cc","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473992009555c08f13553cb0b69223d25ef67d7cc.jpg","special_type":0,"ipad_desc":"新歌榜","is_publish":"111101","mo_type":"5","type":7,"code":"354429321"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473836700b5d93f49b7c9c1264cb19e3df70a65b1.jpg","randpic_ios5":"","randpic_desc":"一周音乐热vol.47","randpic_ipad":"","randpic_qq":"","randpic_2":"bos_client_1473836737e028bf66a162ee55ff176304a70e1226","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473836737e028bf66a162ee55ff176304a70e1226.jpg","special_type":0,"ipad_desc":"一周音乐热vol.47","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/topic_activity/mobile-tmp-v51/"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473827317fe35e92b1b80a19e137327aad2325e19.jpg","randpic_ios5":"","randpic_desc":"T榜专题","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147382732688a80e1be31582ba653a05eb47b400a0.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14738273384d5df45c8748b7242f3585ea8fe168a5.jpg","randpic_2":"bos_client_14738273219bb36e7c02475ac28d739f313cd10d76","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14738273219bb36e7c02475ac28d739f313cd10d76.jpg","special_type":0,"ipad_desc":"T榜专题","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/TbangInterview23/index.html"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14738484267df4571b5433e10299197c2cd49db787.jpg","randpic_ios5":"","randpic_desc":"王俊凯","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_147384848443721b1b3e1d460fc57b824abd28e390.jpg","randpic_qq":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473848497c1610fa4cb390c605a82a913566744aa.jpg","randpic_2":"bos_client_1473848430d529ba6ad4bbe208afc1ff6315256b73","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473848430d529ba6ad4bbe208afc1ff6315256b73.jpg","special_type":0,"ipad_desc":"王俊凯","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/bigwig/junkai"},{"randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_14738241188dac1f151e1171f2fb58ae293a1279d0.jpg","randpic_ios5":"","randpic_desc":"中秋VIP促销活动","randpic_ipad":"","randpic_qq":"","randpic_2":"bos_client_1473756858a8fb02716ca904d7b979f683d175bff5","randpic_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473756858a8fb02716ca904d7b979f683d175bff5.jpg","special_type":0,"ipad_desc":"中秋VIP促销活动","is_publish":"111100","mo_type":"4","type":6,"code":"http://music.baidu.com/cms/webview/bigwig/zhongqiu_vip/index.html"}]
     * error_code : 22000
     */

    private int error_code;
    /**
     * randpic : http://business.cdn.qianqian.com/qianqian/pic/bos_client_14739919991edc043c9d68ff3f54fc36b3aa832d0c.jpg
     * randpic_ios5 :
     * randpic_desc : 新歌榜
     * randpic_ipad : http://business.cdn.qianqian.com/qianqian/pic/bos_client_147399201822a8d3c4634be7abf0e5684ac41c2915.jpg
     * randpic_qq : http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473992027f6ab9bf9d0707cd79e1987d9ce02fd73.jpg
     * randpic_2 : bos_client_1473992009555c08f13553cb0b69223d25ef67d7cc
     * randpic_iphone6 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_1473992009555c08f13553cb0b69223d25ef67d7cc.jpg
     * special_type : 0
     * ipad_desc : 新歌榜
     * is_publish : 111101
     * mo_type : 5
     * type : 7
     * code : 354429321
     */

    private List<PicBean> pic;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public static class PicBean {
        private String randpic;
        private String randpic_ios5;
        private String randpic_desc;
        private String randpic_ipad;
        private String randpic_qq;
        private String randpic_2;
        private String randpic_iphone6;
        private int special_type;
        private String ipad_desc;
        private String is_publish;
        private String mo_type;
        private int type;
        private String code;

        public String getRandpic() {
            return randpic;
        }

        public void setRandpic(String randpic) {
            this.randpic = randpic;
        }

        public String getRandpic_ios5() {
            return randpic_ios5;
        }

        public void setRandpic_ios5(String randpic_ios5) {
            this.randpic_ios5 = randpic_ios5;
        }

        public String getRandpic_desc() {
            return randpic_desc;
        }

        public void setRandpic_desc(String randpic_desc) {
            this.randpic_desc = randpic_desc;
        }

        public String getRandpic_ipad() {
            return randpic_ipad;
        }

        public void setRandpic_ipad(String randpic_ipad) {
            this.randpic_ipad = randpic_ipad;
        }

        public String getRandpic_qq() {
            return randpic_qq;
        }

        public void setRandpic_qq(String randpic_qq) {
            this.randpic_qq = randpic_qq;
        }

        public String getRandpic_2() {
            return randpic_2;
        }

        public void setRandpic_2(String randpic_2) {
            this.randpic_2 = randpic_2;
        }

        public String getRandpic_iphone6() {
            return randpic_iphone6;
        }

        public void setRandpic_iphone6(String randpic_iphone6) {
            this.randpic_iphone6 = randpic_iphone6;
        }

        public int getSpecial_type() {
            return special_type;
        }

        public void setSpecial_type(int special_type) {
            this.special_type = special_type;
        }

        public String getIpad_desc() {
            return ipad_desc;
        }

        public void setIpad_desc(String ipad_desc) {
            this.ipad_desc = ipad_desc;
        }

        public String getIs_publish() {
            return is_publish;
        }

        public void setIs_publish(String is_publish) {
            this.is_publish = is_publish;
        }

        public String getMo_type() {
            return mo_type;
        }

        public void setMo_type(String mo_type) {
            this.mo_type = mo_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
