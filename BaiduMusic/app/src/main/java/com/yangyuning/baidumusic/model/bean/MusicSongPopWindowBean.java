package com.yangyuning.baidumusic.model.bean;

import java.util.List;

/**
 * Created by dllo on 16/9/18.
 * 乐库 歌单 popWindow 实体类
 */
public class MusicSongPopWindowBean {

    /**
     * error_code : 22000
     * content : [{"tags":[{"type":"zhuanti","tag":"音乐专题"}],"title":"精选推荐","num":1},{"tags":[{"type":"gedan","tag":"华语"},{"type":"gedan","tag":"欧美"},{"type":"gedan","tag":"粤语"},{"type":"gedan","tag":"日语"},{"type":"gedan","tag":"韩语"},{"type":"gedan","tag":"纯音乐"},{"type":"gedan","tag":"小语种"}],"title":"语种","num":7},{"tags":[{"type":"gedan","tag":"流行"},{"type":"gedan","tag":"摇滚"},{"type":"gedan","tag":"民谣"},{"type":"gedan","tag":"电子"},{"type":"gedan","tag":"影视原声"},{"type":"gedan","tag":"ACG"},{"type":"gedan","tag":"轻音乐"},{"type":"gedan","tag":"新世纪"},{"type":"gedan","tag":"爵士"},{"type":"gedan","tag":"古典"},{"type":"gedan","tag":"乡村"},{"type":"gedan","tag":"说唱"},{"type":"gedan","tag":"世界音乐"},{"type":"gedan","tag":"古风"},{"type":"gedan","tag":"儿歌"},{"type":"gedan","tag":"朋克"},{"type":"gedan","tag":"布鲁斯"},{"type":"gedan","tag":"RnB/Soul"},{"type":"gedan","tag":"金属"},{"type":"gedan","tag":"雷鬼"},{"type":"gedan","tag":"英伦"},{"type":"gedan","tag":"民族"},{"type":"gedan","tag":"后摇"},{"type":"gedan","tag":"拉丁"}],"title":"风格","num":24},{"tags":[{"type":"gedan","tag":"快乐"},{"type":"gedan","tag":"美好"},{"type":"gedan","tag":"安静"},{"type":"gedan","tag":"伤感"},{"type":"gedan","tag":"寂寞"},{"type":"gedan","tag":"思念"},{"type":"gedan","tag":"孤独"},{"type":"gedan","tag":"怀旧"},{"type":"gedan","tag":"悲伤"},{"type":"gedan","tag":"感动"},{"type":"gedan","tag":"治愈"},{"type":"gedan","tag":"放松"},{"type":"gedan","tag":"清新"},{"type":"gedan","tag":"浪漫"},{"type":"gedan","tag":"兴奋"},{"type":"gedan","tag":"性感"},{"type":"gedan","tag":"励志"}],"title":"情感","num":17},{"tags":[{"type":"gedan","tag":"运动"},{"type":"gedan","tag":"驾驶"},{"type":"gedan","tag":"学习"},{"type":"gedan","tag":"工作"},{"type":"gedan","tag":"清晨"},{"type":"gedan","tag":"夜晚"},{"type":"gedan","tag":"午后"},{"type":"gedan","tag":"游戏"},{"type":"gedan","tag":"旅行"},{"type":"gedan","tag":"散步"},{"type":"gedan","tag":"酒吧"},{"type":"gedan","tag":"夜店"},{"type":"gedan","tag":"咖啡厅"},{"type":"gedan","tag":"地铁"},{"type":"gedan","tag":"校园"},{"type":"gedan","tag":"婚礼"},{"type":"gedan","tag":"约会"},{"type":"gedan","tag":"休息"}],"title":"场景","num":18},{"tags":[{"type":"gedan","tag":"经典"},{"type":"gedan","tag":"翻唱"},{"type":"gedan","tag":"榜单"},{"type":"gedan","tag":"现场"},{"type":"gedan","tag":"KTV"},{"type":"gedan","tag":"DJ"},{"type":"gedan","tag":"网络歌曲"},{"type":"gedan","tag":"器乐"}],"title":"主题","num":8}]
     */

    private int error_code;
    /**
     * tags : [{"type":"zhuanti","tag":"音乐专题"}]
     * title : 精选推荐
     * num : 1
     */

    private List<ContentBean> content;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private String title;
        private int num;
        /**
         * type : zhuanti
         * tag : 音乐专题
         */

        private List<TagsBean> tags;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            private String type;
            private String tag;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }
        }
    }
}
