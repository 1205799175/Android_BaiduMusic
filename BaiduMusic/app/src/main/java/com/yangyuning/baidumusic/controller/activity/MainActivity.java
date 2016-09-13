package com.yangyuning.baidumusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.LocalMusicDetailsFragment;
import com.yangyuning.baidumusic.controller.fragment.MainFragment;

public class MainActivity extends AbsBaseActivity {
    private FrameLayout frameLayout;

    private FrameReceiver frameReceiver;
    private static final String THE_ACTION = "com.yangyuning.baidumusic.controller.fragment.OwnFragment";

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        frameLayout = byView(R.id.main_frame_layout);
    }

    @Override
    protected void initDatas() {
        //注册
        frameReceiver = new FrameReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(THE_ACTION);
        registerReceiver(frameReceiver, filter);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout, MainFragment.newInstance());
        ft.commit();

    }

    //广播接收者
    class FrameReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int i = intent.getIntExtra("id", 1);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (i){
                case 0:
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame_layout, LocalMusicDetailsFragment.newInstance());
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(frameReceiver);
    }
}
