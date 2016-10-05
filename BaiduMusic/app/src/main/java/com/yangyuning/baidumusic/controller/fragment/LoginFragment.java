package com.yangyuning.baidumusic.controller.fragment;

import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;

/**
 * Created by dllo on 16/10/5.
 * 登录Fragment
 */
public class LoginFragment extends AbsBaseFragment implements View.OnClickListener {
    private TextView backTv, skinImg, soundImg, scanImg, importImg, vipImg, ktvImg;
    private Button loginBtn;
    private boolean setipautoFlag = true, desktopFlag = true, locksrcFlag = true;
    private ImageView wifiImg, setipautoImg, deskTopImg, lockscrImg;
    private LinearLayout settingLl, activityLl, appLl;
    private String url = "http://wappass.baidu.com/passport/?login&tpl=wimn&ssid%3D0%26amp%3Bfrom%3D1001703y%26amp%3Buid%3D%26amp%3Bpu%3Dsz%2540320_1001%252Cta%2540iphone_2_5.0_3_537%26amp%3Bbd_page_type%3D1&tn=&regtype=1&u=https%3A%2F%2Fm.baidu.com";
    private WifiManager wifiManager;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        backTv = byView(R.id.login_back);
        loginBtn = byView(R.id.login_login);
        wifiImg = byView(R.id.login_wifi);
        setipautoImg = byView(R.id.login_setipauto);
        deskTopImg = byView(R.id.login_desktop);
        lockscrImg = byView(R.id.login_lockscreen);
        skinImg = byView(R.id.login_tool_skin);
        soundImg = byView(R.id.login_tool_setup);
        scanImg = byView(R.id.login_tool_scan);
        importImg = byView(R.id.login_tool_import);
        vipImg = byView(R.id.login_tool_vip);
        ktvImg = byView(R.id.login_tool_ktv);
        settingLl = byView(R.id.login_setting);
        activityLl = byView(R.id.login_activity);
        appLl = byView(R.id.login_app);

        backTv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        wifiImg.setOnClickListener(this);
        setipautoImg.setOnClickListener(this);
        deskTopImg.setOnClickListener(this);
        lockscrImg.setOnClickListener(this);
        skinImg.setOnClickListener(this);
        soundImg.setOnClickListener(this);
        scanImg.setOnClickListener(this);
        importImg.setOnClickListener(this);
        vipImg.setOnClickListener(this);
        ktvImg.setOnClickListener(this);
        settingLl.setOnClickListener(this);
        activityLl.setOnClickListener(this);
        appLl.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        wifiManager = (WifiManager) context.getSystemService(BaiduMusicApp.getContext().WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()){
            wifiImg.setSelected(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                getFragmentManager().popBackStack();
                break;
            case R.id.login_login:
                WebView webView = new WebView(context);
                webView.loadUrl(url);
                break;
            case R.id.login_wifi:
                if (wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);
                    wifiImg.setSelected(false);
                } else {
                    wifiImg.setSelected(true);
                    wifiManager.setWifiEnabled(true);
                }
                break;
            case R.id.login_setipauto:
                if (setipautoFlag) {
                    setipautoImg.setSelected(true);
                    setipautoFlag = false;
                } else {
                    setipautoImg.setSelected(false);
                    setipautoFlag = true;
                }
                break;
            case R.id.login_desktop:
                if (desktopFlag) {
                    deskTopImg.setSelected(true);
                    desktopFlag = false;
                } else {
                    deskTopImg.setSelected(false);
                    desktopFlag = true;
                }
                break;
            case R.id.login_lockscreen:
                if (locksrcFlag) {
                    lockscrImg.setSelected(true);
                    locksrcFlag = false;
                } else {
                    lockscrImg.setSelected(false);
                    locksrcFlag = true;
                }
                break;
            case R.id.login_tool_skin:
                break;
            case R.id.login_tool_setup:
                break;
            case R.id.login_tool_scan:
                break;
            case R.id.login_tool_import:
                break;
            case R.id.login_tool_vip:
                break;
            case R.id.login_tool_ktv:
                break;
            case R.id.login_setting:
                break;
            case R.id.login_activity:
                break;
            case R.id.login_app:
                break;
        }
    }
}
