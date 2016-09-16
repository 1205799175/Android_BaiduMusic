package com.yangyuning.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/16.
 * K歌 轮播图
 */
public class KRotareBean {

    /**
     * error_code : 22000
     * result : [{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_edc100852c4f79bed6363f128864aed2.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1171955899201418a2b661040d5b9bad.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160830"},{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_ce2b6222e716d52830db0fb5f6be0338.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_960f3e467323f702c759f9c72e68b33a.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160806"},{"type":"learn","picture":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_c384a96b3c9a60470b8f5dfa1d5c34c3.jpg","picture_iphone6":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_3c7bdcb1915d176ddd1899cf7b80b85f.jpg","web_url":"http://music.baidu.com/cms/webview/ktv_activity/20160617/index.html"}]
     */

    private int error_code;
    /**
     * type : learn
     * picture : http://business.cdn.qianqian.com/qianqian/pic/bos_client_edc100852c4f79bed6363f128864aed2.jpg
     * picture_iphone6 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_1171955899201418a2b661040d5b9bad.jpg
     * web_url : http://music.baidu.com/cms/webview/ktv_activity/20160830
     */

    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String type;
        private String picture;
        private String picture_iphone6;
        private String web_url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPicture_iphone6() {
            return picture_iphone6;
        }

        public void setPicture_iphone6(String picture_iphone6) {
            this.picture_iphone6 = picture_iphone6;
        }

        public String getWeb_url() {
            return web_url;
        }

        public void setWeb_url(String web_url) {
            this.web_url = web_url;
        }
    }
}
