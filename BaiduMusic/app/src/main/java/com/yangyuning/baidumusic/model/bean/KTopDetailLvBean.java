package com.yangyuning.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 * K歌 KTV 华语金曲, 欧美经典 详情页
 */
public class KTopDetailLvBean {

    /**
     * error_code : 22000
     * result : {"total":73039,"have_more":1,"items":[{"song_id":"121889450","song_title":"青春修炼手册","album_title":"青春修炼手册","artist_name":"TFBOYS"},{"song_id":"14945107","song_title":"泡沫","album_title":"Xposed(曝光)","artist_name":"G.E.M.邓紫棋"},{"song_id":"89308173","song_title":"爸爸去哪儿","album_title":"爸爸去哪儿","artist_name":"林志颖,Kimi,张亮,张悦轩,郭涛,郭子睿,王岳伦,王诗龄"},{"song_id":"290008","song_title":"我只在乎你","album_title":"我只在乎你","artist_name":"邓丽君"},{"song_id":"14944589","song_title":"逆战","album_title":"One Chance 新歌+精选","artist_name":"张杰"},{"song_id":"7313941","song_title":"月亮代表我的心","album_title":"传奇再续","artist_name":"邓丽君"},{"song_id":"124380645","song_title":"匆匆那年","album_title":"匆匆那年","artist_name":"王菲"},{"song_id":"7323637","song_title":"星语心愿","album_title":"张柏芝","artist_name":"张柏芝"},{"song_id":"7313948","song_title":"甜蜜蜜","album_title":"传奇再续","artist_name":"邓丽君"},{"song_id":"1725130","song_title":"虫儿飞","album_title":"童年的歌谣 CD1","artist_name":"少儿歌曲"},{"song_id":"1081156","song_title":"风中有朵雨做的云","album_title":"24Kgold_A","artist_name":"孟庭苇"},{"song_id":"7329148","song_title":"小城故事","album_title":"小城经典-邓丽君","artist_name":"邓丽君"},{"song_id":"238995074","song_title":"宠爱","album_title":"宠爱","artist_name":"TFBOYS"},{"song_id":"123304515","song_title":"幸运符号","album_title":"幸运符号","artist_name":"TFBOYS"},{"song_id":"213508","song_title":"阳光总在风雨后","album_title":"都是夜归人","artist_name":"许美静"},{"song_id":"86772502","song_title":"夜空中最亮的星","album_title":"夜空中最亮的星(Remix版)","artist_name":"逃跑计划"},{"song_id":"1184585","song_title":"路边野花不要采","album_title":"邓丽君3合1","artist_name":"邓丽君"},{"song_id":"7320911","song_title":"独角戏","album_title":"单身日记","artist_name":"许茹芸"},{"song_id":"124169979","song_title":"信仰之名","album_title":"信仰之名","artist_name":"TFBOYS"}]}
     */

    private int error_code;
    /**
     * total : 73039
     * have_more : 1
     * items : [{"song_id":"121889450","song_title":"青春修炼手册","album_title":"青春修炼手册","artist_name":"TFBOYS"},{"song_id":"14945107","song_title":"泡沫","album_title":"Xposed(曝光)","artist_name":"G.E.M.邓紫棋"},{"song_id":"89308173","song_title":"爸爸去哪儿","album_title":"爸爸去哪儿","artist_name":"林志颖,Kimi,张亮,张悦轩,郭涛,郭子睿,王岳伦,王诗龄"},{"song_id":"290008","song_title":"我只在乎你","album_title":"我只在乎你","artist_name":"邓丽君"},{"song_id":"14944589","song_title":"逆战","album_title":"One Chance 新歌+精选","artist_name":"张杰"},{"song_id":"7313941","song_title":"月亮代表我的心","album_title":"传奇再续","artist_name":"邓丽君"},{"song_id":"124380645","song_title":"匆匆那年","album_title":"匆匆那年","artist_name":"王菲"},{"song_id":"7323637","song_title":"星语心愿","album_title":"张柏芝","artist_name":"张柏芝"},{"song_id":"7313948","song_title":"甜蜜蜜","album_title":"传奇再续","artist_name":"邓丽君"},{"song_id":"1725130","song_title":"虫儿飞","album_title":"童年的歌谣 CD1","artist_name":"少儿歌曲"},{"song_id":"1081156","song_title":"风中有朵雨做的云","album_title":"24Kgold_A","artist_name":"孟庭苇"},{"song_id":"7329148","song_title":"小城故事","album_title":"小城经典-邓丽君","artist_name":"邓丽君"},{"song_id":"238995074","song_title":"宠爱","album_title":"宠爱","artist_name":"TFBOYS"},{"song_id":"123304515","song_title":"幸运符号","album_title":"幸运符号","artist_name":"TFBOYS"},{"song_id":"213508","song_title":"阳光总在风雨后","album_title":"都是夜归人","artist_name":"许美静"},{"song_id":"86772502","song_title":"夜空中最亮的星","album_title":"夜空中最亮的星(Remix版)","artist_name":"逃跑计划"},{"song_id":"1184585","song_title":"路边野花不要采","album_title":"邓丽君3合1","artist_name":"邓丽君"},{"song_id":"7320911","song_title":"独角戏","album_title":"单身日记","artist_name":"许茹芸"},{"song_id":"124169979","song_title":"信仰之名","album_title":"信仰之名","artist_name":"TFBOYS"}]
     */

    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int total;
        private int have_more;
        /**
         * song_id : 121889450
         * song_title : 青春修炼手册
         * album_title : 青春修炼手册
         * artist_name : TFBOYS
         */

        private List<ItemsBean> items;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getHave_more() {
            return have_more;
        }

        public void setHave_more(int have_more) {
            this.have_more = have_more;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private String song_id;
            private String song_title;
            private String album_title;
            private String artist_name;

            public String getSong_id() {
                return song_id;
            }

            public void setSong_id(String song_id) {
                this.song_id = song_id;
            }

            public String getSong_title() {
                return song_title;
            }

            public void setSong_title(String song_title) {
                this.song_title = song_title;
            }

            public String getAlbum_title() {
                return album_title;
            }

            public void setAlbum_title(String album_title) {
                this.album_title = album_title;
            }

            public String getArtist_name() {
                return artist_name;
            }

            public void setArtist_name(String artist_name) {
                this.artist_name = artist_name;
            }
        }
    }
}
