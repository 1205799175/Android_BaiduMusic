package com.yangyuning.baidumusic.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 16/9/12.
 * 乐库 歌单 实体类
 */
public class MusicSongBean implements Serializable{
    /**
     * error_code : 22000
     * total : 6826
     * havemore : 1
     * content : [{"listid":"354430868","listenum":"832","collectnum":"6","title":"沉溺在自己的次元","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14738369780acf6a74cc0be6c24b69268c298923de.jpg","tag":"华语,流行,摇滚","desc":"华语乐队组合，每人一首代表作","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14738369780acf6a74cc0be6c24b69268c298923de.jpg","width":"300","height":"300","songIds":["1591584","74100888","85616933","84996045","1136345","1394326","10322999","1802805","86772502","15825093"]},{"listid":"354430964","listenum":"271","collectnum":"2","title":"和你共度一个五彩斑斓的梦","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837742c6877cdcb520209c086c61ab393417c2.jpg","tag":"浪漫,韩语,流行","desc":"数那些CP感爆棚的韩语对唱","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837742c6877cdcb520209c086c61ab393417c2.jpg","width":"300","height":"300","songIds":["115253323","265365988","26479283","19023193","263240573","106187634","266817637","33944468","124501952","107183347"]},{"listid":"354430896","listenum":"390","collectnum":"1","title":"致郁音符，百无一用是深情","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837234e1c93d001462ea5d074e029932d6aae9.jpg","tag":"华语,伤感,流行","desc":"故事的开始，适逢其会，猝不及防；故事的结尾，花开两朵，天各一方","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837234e1c93d001462ea5d074e029932d6aae9.jpg","width":"300","height":"300","songIds":["242078437","23444316","2074920","106131719","20723793","11386715","2085009","241838066","35593565","929048"]},{"listid":"354430928","listenum":"195","collectnum":"3","title":"日系民谣，与三味线一起吟唱","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837433cd19edcc23ae6df1f9edcdcc1630b272.jpg","tag":"日语,民谣,流行","desc":"在这些浓郁日本风情的曲调里，感受独特的音乐风格","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837433cd19edcc23ae6df1f9edcdcc1630b272.jpg","width":"300","height":"300","songIds":["55776358","55627032","1108380","56324174","70432914","14448136","18653123","25477252","56323706","58968776"]},{"listid":"354430994","listenum":"1235","collectnum":"0","title":"那些年，90后追过的国产剧","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837996621f2c40c6d725df744f1bb5488a57f7.jpg","tag":"影视原声,华语,经典","desc":"满满的都是回忆，一名90后必看的电视剧主题曲收录","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473837996621f2c40c6d725df744f1bb5488a57f7.jpg","width":"300","height":"300","songIds":["819377","999104","23692930","892329","1064045","590837","1074143","58904955","58904768","14308106"]},{"listid":"354431151","listenum":"288","collectnum":"2","title":"秋意渐浓，适合听乡村的季节","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473838925b12c84e2ffe289d5d26a559e2489dc9f.jpg","tag":"欧美,流行,乡村","desc":"天高云淡，秋高气爽，何不来几首乡村听","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473838925b12c84e2ffe289d5d26a559e2489dc9f.jpg","width":"300","height":"300","songIds":["28542769","2155238","71282532","7996308","28467163","19121497","9896908","1194796","118750572","1177593"]},{"listid":"354408709","listenum":"1061","collectnum":"1","title":"时代之声里的金属光泽","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473667300c3e40a7dd09021930a81f01a2722aeed.jpg","tag":"金属,摇滚,夜店","desc":"嗨翻整晚，尽情释放","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473667300c3e40a7dd09021930a81f01a2722aeed.jpg","width":"300","height":"300","songIds":["1004781","10443974","1726144","105040167","491006","1774067","1137865","443969","10546671","105724248"]},{"listid":"354343826","listenum":"7998","collectnum":"10","title":"对你的思念如热气球，渐行渐远","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14732166220dcfe8cc4abc8654044e0e67f5f32c75.jpg","tag":"华语,思念,流行","desc":"歌声很轻，思念却重","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14732166220dcfe8cc4abc8654044e0e67f5f32c75.jpg","width":"300","height":"300","songIds":["135903264","13902577","1193388","268416617","24340283","218687","266415281","1668799","300826","5738289"]},{"listid":"354334596","listenum":"15409","collectnum":"19","title":"花树下的想念，飘落一地忧伤","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14731449099cc0c19091cc901186ce8d7575587f0d.jpg","tag":"华语,清新,伤感","desc":"每个人都有心痛的回忆，不会向人倾诉，只能等待时间的治愈。","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14731449099cc0c19091cc901186ce8d7575587f0d.jpg","width":"300","height":"300","songIds":["1267392","73987460","101496666","5960133","74041503","2118080","241855701","116207064","258517580","23218998"]},{"listid":"5489","listenum":"157649","collectnum":"736","title":"世纪经典对唱大全","pic_300":"http://a.hiphotos.baidu.com/ting/pic/item/d833c895d143ad4bab681e4c87025aafa50f06f3.jpg","tag":"经典","desc":"人生何其短暂，如流星之于夜空；人要拿得起，也要放得下。拿得起是生存，放得下是生活；拿得起是能力，放得下是智慧。","pic_w300":"http://a.hiphotos.baidu.com/ting/pic/item/d833c895d143ad4bab681e4c87025aafa50f06f3.jpg","width":"300","height":"300","songIds":["7319923","1329239","2113203","308865","7331207","313985","293547","2131293","2062402","121852416"]},{"listid":"354368210","listenum":"26156","collectnum":"40","title":"经典老歌点唱机","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473401746ad20e6c555a89288675b284653d030cc.jpg","tag":"华语,经典,怀旧","desc":"时光中沉淀的老歌依然首首动人","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473401746ad20e6c555a89288675b284653d030cc.jpg","width":"300","height":"300","songIds":["1262598","13125209","7338336","620023","307171","1175705","490468","7317587","453793","276766"]},{"listid":"354419583","listenum":"15958","collectnum":"19","title":"不甘朋友,不敢情人","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473751664ad5d64840d68d3d783bc260347b39638.jpg","tag":"华语,流行,伤感","desc":"友情以上，恋人以下，你是我藏在心口最深处的秘密","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473751664ad5d64840d68d3d783bc260347b39638.jpg","width":"300","height":"300","songIds":["946215","360662","18265030","44897031","8595804","38215176","5974550","215550","13870943","211123"]},{"listid":"354369001","listenum":"20617","collectnum":"6","title":"教诲如春风,师恩似海深","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473406900571f505bc0ed8b63a06506d62be349b3.jpg","tag":"华语,美好,流行","desc":"白色的粉笔末一阵阵飘落，染白了您的发，却将您青春的绿映衬得更加浓郁","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473406900571f505bc0ed8b63a06506d62be349b3.jpg","width":"300","height":"300","songIds":["25662163","937284","51858298","2092175","7331713","58703968","269018201","21442896","107356506","246750691"]},{"listid":"354409820","listenum":"1027","collectnum":"6","title":"让弛放切断身体与精神绷紧的弦","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736744638405c4b3e9175b0fc5121042ff862a97.jpg","tag":"新世纪,放松,驾驶","desc":"氛围弛放令人深陷其中","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736744638405c4b3e9175b0fc5121042ff862a97.jpg","width":"300","height":"300","songIds":["680845","681141","84963487","85009024","15365282","15365313","15365374","15365394","15365413","930804"]},{"listid":"354409762","listenum":"2350","collectnum":"6","title":"新生的乡村流行绝唱","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473674204ea460d84072ad4b11ca11a9574873878.jpg","tag":"乡村,流行,KTV","desc":"ktv必备的欧美乡村歌曲","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473674204ea460d84072ad4b11ca11a9574873878.jpg","width":"300","height":"300","songIds":["499240","1281961","1281996","1282016","1282192","1471897","2825899","2910718","3486856","3486859"]},{"listid":"354406938","listenum":"5908","collectnum":"11","title":"未来很远，遗忘很长","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473655936a6c441a819c4216002b547d9cb445882.jpg","tag":"华语,流行,伤感","desc":"我很爱你，但只是不在喜欢你了。","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473655936a6c441a819c4216002b547d9cb445882.jpg","width":"300","height":"300","songIds":["263874","347247","231283","286526","470583","353445","2062525","809157","239199","405868"]},{"listid":"354370581","listenum":"7930","collectnum":"16","title":"心微动，奈何情已远","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_147341654422c43d1c2d365ae12f0e6df0cc87afad.jpg","tag":"华语,古风,清新","desc":"凄凉别后两应同，最是不胜清怨月明中","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_147341654422c43d1c2d365ae12f0e6df0cc87afad.jpg","width":"300","height":"300","songIds":["73876333","73874047","56046207","18186353","73978850","74098692","31785987","85046917","74159047","73912686"]},{"listid":"354419683","listenum":"7631","collectnum":"10","title":"民歌精选，释放最纯粹的情感","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_147375230046a9028036dfb044b76f7a7e0f01d365.jpg","tag":"世界音乐,华语","desc":"聆听最纯粹的音乐，一字一句都源自山河土地间最醇厚的味道。","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_147375230046a9028036dfb044b76f7a7e0f01d365.jpg","width":"300","height":"300","songIds":["10235767","10287989","1120780","1235840","1374130","14565826","19027825","18947656","1969083","2041129"]},{"listid":"5843","listenum":"241794","collectnum":"1744","title":"潮人必备 欧美high歌","pic_300":"http://b.hiphotos.baidu.com/ting/pic/item/9d82d158ccbf6c810f6f0c69ba3eb13532fa40d2.jpg","tag":"流行,欧美,兴奋","desc":"在音乐中，我们发泄心中的积怨，音乐如同一个知心好朋友，耐心的倾听我们对生活的不满，点燃你的high点。","pic_w300":"http://b.hiphotos.baidu.com/ting/pic/item/9d82d158ccbf6c810f6f0c69ba3eb13532fa40d2.jpg","width":"300","height":"300","songIds":["122823665","123969281","14841270","13844864","14907195","1038302","120971131","35595406","7386676","1037883"]},{"listid":"354406251","listenum":"3190","collectnum":"10","title":"和风纯净旋律，聆听万籁窸窣","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473650755483a0f0b5697fceb48b97009ef44f5d7.jpg","tag":"纯音乐,ACG,美好","desc":"夏天已逝，秋意翩然，在耳机里与美好相遇","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473650755483a0f0b5697fceb48b97009ef44f5d7.jpg","width":"300","height":"300","songIds":["53546972","27995907","19178033","16648142","16648472","16648665","28646911","19167174","28708155","18150628"]},{"listid":"354408463","listenum":"7956","collectnum":"5","title":"恢复五官美学，从微笑开始","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736655406cd9e751a5bea47b3033f213b7f933e0.jpg","tag":"华语,治愈,流行","desc":"爱笑的女孩运气不会差，那么就从每天微笑开始吧","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736655406cd9e751a5bea47b3033f213b7f933e0.jpg","width":"300","height":"300","songIds":["33467378","124469893","73992160","1611595","12312517","24154083","606000","74100890","5738291","115997294"]},{"listid":"354343886","listenum":"11331","collectnum":"14","title":"得不到，就学会承受失去","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473217085d8d6122738f3b3302a7f623649118414.jpg","tag":"粤语,伤感,流行","desc":"人总需要勇敢生存，不求天长地久，只求曾经拥有","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473217085d8d6122738f3b3302a7f623649118414.jpg","width":"300","height":"300","songIds":["10561847","466135","59088899","235081","7333337","7316923","19146071","14444008","23447375","19122881"]},{"listid":"354406916","listenum":"8124","collectnum":"10","title":"一首歌带你追忆学生时代","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473655775925c1ee7393b4ab4a16406f504c00723.jpg","tag":"华语,怀旧,校园","desc":"看着眼前的孩子都一个个开学了，听首歌来缅怀一下自己曾经的校园生活吧","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473655775925c1ee7393b4ab4a16406f504c00723.jpg","width":"300","height":"300","songIds":["14430747","8248175","2045588","10494344","1404326","14471964","5838263","2122996","5919772","84948595"]},{"listid":"354409719","listenum":"5342","collectnum":"17","title":"清新摇滚，抓耳又挠心","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736740371c9b86cc7cf73acb05fe8f13b5c8a05f.jpg","tag":"欧美,摇滚,驾驶","desc":"有这样一种摇滚，不静也不吵，就喜欢这种刚刚好","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736740371c9b86cc7cf73acb05fe8f13b5c8a05f.jpg","width":"300","height":"300","songIds":["257263500","8966998","2613356","118694733","20503492","2185913","119811239","257544142","20464914","423682"]},{"listid":"5765","listenum":"235299","collectnum":"801","title":"奇葩入院必听神曲！","pic_300":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_489aff1af88469ca6363fe7f14dd9b97.jpg","tag":"治愈,网络歌曲","desc":"男人不奇葩，哪来么么哒？女人不奇葩，活该犬夜叉！在这个全民奇葩的时代，没听过点奇葩歌曲还好意思跟别人说你是奇葩？","pic_w300":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_489aff1af88469ca6363fe7f14dd9b97.jpg","width":"300","height":"300","songIds":["247041101","120125029","121269522","5837361","39462038","23473715","123797502","538052","14709412","242137130"]},{"listid":"354345385","listenum":"3330","collectnum":"3","title":"来人！把朕的狗粮拿来","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473226945593378e3a7670fcf8b7bf3b66ca1b9b5.jpg","tag":"华语,流行,浪漫","desc":"这年头，一个不小心随时可能会被塞一口狗粮，比如这个歌单...","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473226945593378e3a7670fcf8b7bf3b66ca1b9b5.jpg","width":"300","height":"300","songIds":["1268254","7313936","242605387","128709281","83590931","7338916","1329239","99751993","241666238","14431076"]},{"listid":"6427","listenum":"99755","collectnum":"746","title":"不止眼前的苟且还有你和远方","pic_300":"http://a.hiphotos.baidu.com/ting/pic/item/08f790529822720ee46b87b77ccb0a46f31fabd2.jpg","tag":"流行","desc":"妈妈坐在门前，哼着花儿与少年，虽已时隔多年，记得她泪水涟涟。那些幽暗的时光，那些坚持与慌张，在临别的门前，妈妈望着我说,生活不止眼前的苟且，还有诗和远方的田野。","pic_w300":"http://a.hiphotos.baidu.com/ting/pic/item/08f790529822720ee46b87b77ccb0a46f31fabd2.jpg","width":"300","height":"300","songIds":["247696","14950800","265073","247530052","73837565","233212","392535","73992164","73837619","256033604"]},{"listid":"354407479","listenum":"8426","collectnum":"8","title":"谎言说给耳朵听，却让眼睛动了情","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736592338a881876373b82d5197ccb1608d7041e.jpg","tag":"粤语,流行,感动","desc":"粤语情深，温灯暖酒与君酌","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14736592338a881876373b82d5197ccb1608d7041e.jpg","width":"300","height":"300","songIds":["18608630","10561185","227424","7320512","7317702","13125209","2735413","624053","338127","261245"]},{"listid":"354408452","listenum":"1993","collectnum":"6","title":"风光正好，生命永不止步","pic_300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473665238e75ac26ac4e8d68e91b9e78b79f42282.jpg","tag":"欧美,运动,流行","desc":"健身房运动歌单，欢迎收藏","pic_w300":"http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1473665238e75ac26ac4e8d68e91b9e78b79f42282.jpg","width":"300","height":"300","songIds":["54920940","85809760","20859938","108223456","266832333","72646367","264747211","242120639","1790591","59051081"]},{"listid":"6412","listenum":"63784","collectnum":"628","title":"论优雅的撩妹技巧","pic_300":"http://b.hiphotos.baidu.com/ting/pic/item/a8773912b31bb0518c90096f317adab44bede0c5.jpg","tag":"华语,粤语,浪漫","desc":"少年，带上这个歌单去撩妹吧，记得献上一剂摸头杀哦！","pic_w300":"http://b.hiphotos.baidu.com/ting/pic/item/a8773912b31bb0518c90096f317adab44bede0c5.jpg","width":"300","height":"300","songIds":["73883841","13684609","244079","239816959","74094008","7322227","257121423","7320396","52648014","246486639"]}]
     */

    private int error_code;
    private int total;
    private int havemore;
    /**
     * listid : 354430868
     * listenum : 832
     * collectnum : 6
     * title : 沉溺在自己的次元
     * pic_300 : http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14738369780acf6a74cc0be6c24b69268c298923de.jpg
     * tag : 华语,流行,摇滚
     * desc : 华语乐队组合，每人一首代表作
     * pic_w300 : http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_14738369780acf6a74cc0be6c24b69268c298923de.jpg
     * width : 300
     * height : 300
     * songIds : ["1591584","74100888","85616933","84996045","1136345","1394326","10322999","1802805","86772502","15825093"]
     */

    private List<ContentBean> content;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHavemore() {
        return havemore;
    }

    public void setHavemore(int havemore) {
        this.havemore = havemore;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        private String listid;
        private String listenum;
        private String collectnum;
        private String title;
        private String pic_300;
        private String tag;
        private String desc;
        private String pic_w300;
        private String width;
        private String height;
        private List<String> songIds;

        public String getListid() {
            return listid;
        }

        public void setListid(String listid) {
            this.listid = listid;
        }

        public String getListenum() {
            return listenum;
        }

        public void setListenum(String listenum) {
            this.listenum = listenum;
        }

        public String getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(String collectnum) {
            this.collectnum = collectnum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_300() {
            return pic_300;
        }

        public void setPic_300(String pic_300) {
            this.pic_300 = pic_300;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPic_w300() {
            return pic_w300;
        }

        public void setPic_w300(String pic_w300) {
            this.pic_w300 = pic_w300;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public List<String> getSongIds() {
            return songIds;
        }

        public void setSongIds(List<String> songIds) {
            this.songIds = songIds;
        }
    }

}
