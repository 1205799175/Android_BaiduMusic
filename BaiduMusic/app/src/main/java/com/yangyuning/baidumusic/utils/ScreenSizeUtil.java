package com.yangyuning.baidumusic.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;

/**
 * Created by dllo on 16/9/16.
 * 屏幕大小工具类
 */
public class ScreenSizeUtil {
    //枚举:整型常量, 一般用枚举规定一些特殊状态, 如横屏,竖屏,窗口化,  如播放, 暂停, 停止,  如宽高
    //关键字: enum 也是一种数据类型, 就是一个类
    public enum ScreenState{
        WIDTH,HEIGHT
    }
    public static int getScreenSize(ScreenState state){
        WindowManager manager = (WindowManager) BaiduMusicApp.getContext().getSystemService(BaiduMusicApp.getContext().WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        if (state.equals(ScreenState.WIDTH)){
            return metrics.widthPixels;
        } else if (state.equals(ScreenState.HEIGHT)){
            return metrics.heightPixels;
        }
        return metrics.heightPixels;
    }


}
