package com.yangyuning.baidumusic.controller.app;

import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/9/8.
 * BaiduMusic Application
 * @author yangyuning
 * @time 9-8
 * @version 1.0
 */
public class BaiduMusicApp extends Application {
   private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ShareSDK.initSDK(this);
    }

    public static Context getContext(){
        return context;
    }
}
