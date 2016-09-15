package com.yangyuning.baidumusic.utils;

/**
 * Created by dllo on 16/9/14.
 */
public class BaiduMusicValues {
    public BaiduMusicValues() {}

    //我的Fragment本地歌曲到二级页面 广播 getAction
    public static final String THE_ACTION_OWN_LOCAL = "com.yangyuning.baidumusic.controller.fragment.ownfragmet.OwnFragment";
    public static final String THE_ACTION_KEY_POAITION = "position";
    //MainActivity广播接收者  position
    public static final int MAIN_RECEIVER_POSITION_ZREO = 0;
    public static final int MAIN_RECEIVER_POSITION_ONE = 1;
    public static final int MAIN_RECEIVER_POSITION_TWO = 2;
    public static final int MAIN_RECEIVER_POSITION_THREE = 3;

    //MVFragment的RecyclerView列数
    public static final int MV_RECYCLERVIEW_ROW_NUM = 2;

    //沉浸式状态栏的颜色
    public static final String STATUSBARCOLOR = "#08ACE0";

    //乐库-歌单 接口
    public static final String MUSIC_SONG = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.diy.gedan&page_size=30&page_no=1\n";
    //乐库-排行 接口
    public static final String MUSIC_RANKING = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";
    //乐库-电台 接口
    public static final String MUSIC_RADIO = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=vivo&operator=0&method=baidu.ting.scene.getCategoryScene&category_id=0";
    //乐库-MV 接口
    public static final String MUSIC_MV = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    //直播 上部 ListView接口数据
    public static final String ALIVE_TOP_RECYCLERVIEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.category";
    //直播 下部 RecyclerView接口数据
    public static final String ALIVE_BOTTOM_RECYCLERVIEW = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.8.2.0&channel=875b&operator=0&method=baidu.ting.show.live&page_no=1&page_size=40";
 }
