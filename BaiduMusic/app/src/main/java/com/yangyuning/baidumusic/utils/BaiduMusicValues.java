package com.yangyuning.baidumusic.utils;

/**
 * Created by dllo on 16/9/14.
 * 常量类
 */
public class BaiduMusicValues {
    public BaiduMusicValues() {
    }

    /**
     * 我的Fragment本地歌曲到二级页面 广播 getAction
     */
    public static final String THE_ACTION_OWN_LOCAL = "com.yangyuning.baidumusic.controller.fragment.ownfragmet.OwnFragment";
    /**
     * 直播FragmentRv到二级页面 广播
     */
    public static final String THE_ACTION_ALIVE_RV = "com.yangyuning.baidumusic.controller.fragment.alivefragment.AliveFragment";
    public static final String PLAY_MUSIC_SONG = "title";
    public static final String PLAY_MUSIC_SINGER = "singer";

    /**
     * 我的Fragment本地歌曲二级页面 到Activity 广播
     */
    public static final String THE_ACTION_PLAY_MUSIC = "com.yangyuning.baidumusic.controller.fragment.ownfragment.LocalMusicTabFragment";
    public static final String THE_ACTION_KEY_POAITION = "position";

    /**
     * 点击进入排行详情页 广播
     */
    public static final String THE_ACTION_RANKING_DETAIL = "com.yangyuning.baidumusic.controller.fragment.musicfragment.RankingFragment";
    public static final String RANKING_DETAIL_KET_POSITION = "position";
    public static final String RANKING_DETAIL_KET_TYPE = "type";

    /**
     * 点击排行详情行布局播放网络歌曲  向MainActivity发广播 songId
     */
    public static final String THE_ACTION_RANKING_DETAIL_PLAY_MUSIC = "com.yangyuning.baidumusic.controller.fragment.musicfragment.RankingDetailFragment";
    public static final String RANKING_DETAIL_PLAY_MUSIC_SONGID = "songId";

    /**
     * 各个页面发送网络歌曲链接广播到服务
     */
    public static final String THE_ACTION_MUSICSETVICE = "com.yangyuning.baidumusic.controller.services.MusicService";

    /**
     * 播放界面点击播放 在歌词Fragment上方显示歌名歌歌手 广播
     */
    public static final String THE_ACTION_PLAY_PAGE_PLAY = "com.yangyuning.baidumusic.controller.activity.PlayMusicPageActivity";
    public static final String THE_ACTION_PLAY_PAGE_SONG = "song";
    public static final String THE_ACTION_PLAY_PAGE_SINGER = "singer";

    /**
     * Fragment复用传网址的key
     */
    public static final String THE_NEWINSTANCE_URL_KEY = "key";

    /**
     * MainActivity广播接收者  position
     */
    public static final int MAIN_RECEIVER_POSITION_MINUS_ONE = -1;
    public static final int MAIN_RECEIVER_POSITION_ZREO = 0;
    public static final int MAIN_RECEIVER_POSITION_ONE = 1;
    public static final int MAIN_RECEIVER_POSITION_TWO = 2;
    public static final int MAIN_RECEIVER_POSITION_THREE = 3;
    public static final int MAIN_RECEIVER_POSITION_FOUR = 4;
    public static final int MAIN_RECEIVER_POSITION_FIVE = 5;
    public static final int MAIN_RECEIVER_POSITION_SIX = 6;
    public static final int MAIN_RECEIVER_POSITION_SEVEN = 7;
    public static final int MAIN_RECEIVER_POSITION_EIGHT = 8;

    /**
     * MVFragment的RecyclerView列数
     */
    public static final int MV_RECYCLERVIEW_ROW_NUM = 2;

    /**
     * 沉浸式状态栏的颜色
     */
    public static final String STATUSBARCOLOR = "#00B4FF";
    public static final String TRANSPORT = "#00000000";

    /********************************接口相关**************************************/
    /**
     * 乐库-推荐-轮播图 接口
     */
    public static final String RECOMMEND_ROTATE = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.plaza.getFocusPic&format=json&from=ios&version=5.2.3&from=ios&channel=appstore";

    /**
     * 乐库-推荐 接口
     */
    public static final String MUSIC_RECOMMEND = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.plaza.index&cuid=D39E874BD13170332B889C3E2F9F6C0B";

    /**
     * 乐库-歌单 接口
     */
    public static final String MUSIC_SONG = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.diy.gedan&page_size=30&page_no=1\n";

    /**
     * 乐库-歌单 POpWindow接口
     */
    public static final String MUSIC_SONG_POPWINDOW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.diy.gedanCategory";

    /**
     * 乐库-排行 接口
     */
    public static final String MUSIC_RANKING = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";

    /**
     * 乐库--排行--详情
     */
    public static final String MUSIC_RANKING_DETAIL_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";
    public static final String MUSIC_RANKING_DETAIL_BOTTOM = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    /**
     * 乐库-电台 接口
     */
    public static final String MUSIC_RADIO = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.scene.getCategoryScene&category_id=0";

    /**
     * 乐库-MV 最新 接口
     */
    public static final String MUSIC_NEW_MV = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    /**
     * 乐库-MV 最热 接口
     */
    public static final String MUSIC_HOT_MV = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    /**
     * K歌 轮播图 接口
     */
    public static final String K_ROTATE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.active.showList";

    /**
     * K歌 接口
     */
    public static final String K = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.learn.now&page_size=50";

    /**
     * 直播 上部 RecyclerView接口数据
     */
    public static final String ALIVE_TOP_RECYCLERVIEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.category";

    /**
     * 直播 下部 RecyclerView接口数据
     */
    public static final String ALIVE_BOTTOM_RECYCLERVIEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";

    /**
     * 直播 上部 RV 二级页面 全部, 女神
     */
    public static final String ALIVE_TOP_RV_DETAIL_ALL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=hot";
    public static final String ALIVE_TOP_RV_DETAIL_WOMAN = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=4";
    public static final String ALIVE_TOP_RV_DETAIL_GOODVOICE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=3";
    public static final String ALIVE_TOP_RV_DETAIL_NEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=new";
    public static final String ALIVE_TOP_RV_DETAIL_BOOM = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=2";
    public static final String ALIVE_TOP_RV_DETAIL_JOY = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=15";
    public static final String ALIVE_TOP_RV_DETAIL_SISTER = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=1";
    public static final String ALIVE_TOP_RV_DETAIL_RECOMMEND = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.item&page_size=30&page_no=1&category=recommend";

    /**
     * 网址拼接 播放网络歌曲
     * SONG_ULR_HEAD : URL 头
     * SONG_ULR_FOOT : URL 尾
     */
    public static final String SONG_ULR_HEAD = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    public static final String SONG_URL_FOOT = "&_=1413017198449";

}
