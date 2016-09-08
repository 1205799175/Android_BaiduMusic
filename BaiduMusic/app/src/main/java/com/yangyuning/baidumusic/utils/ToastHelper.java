package com.yangyuning.baidumusic.utils;

import android.widget.Toast;

import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;

/**
 * Created by dllo on 16/9/8.
 * Toast工具类
 * @author yangyuning
 * @time 9-8
 * @version 1.0
 */
public final class ToastHelper {

    private ToastHelper(){}

    private static boolean isToast = true;

    public static void shortToast(String msg){
        if (isToast){
            Toast.makeText(BaiduMusicApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    public static void longToast(String msg){
        if (isToast){
            Toast.makeText(BaiduMusicApp.getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
