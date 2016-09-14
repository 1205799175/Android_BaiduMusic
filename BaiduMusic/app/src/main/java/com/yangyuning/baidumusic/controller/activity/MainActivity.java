package com.yangyuning.baidumusic.controller.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.LocalMusicDetailsFragment;
import com.yangyuning.baidumusic.controller.fragment.MainFragment;
import com.yangyuning.baidumusic.controller.fragment.ownfragment.OwnFragment;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

public class MainActivity extends AbsBaseActivity {
    private FrameLayout frameLayout;

    private FrameReceiver frameReceiver;

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
        filter.addAction(BaiduMusicValues.THE_ACTION_OWN_LOCAL);
        registerReceiver(frameReceiver, filter);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_frame_layout, MainFragment.newInstance());
        ft.commit();
    }

    //广播接收者  从OwnFragment发送
    class FrameReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int i = intent.getIntExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (i){
                case -1:
                    fragmentManager.popBackStack();
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ZREO:
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.main_frame_layout, LocalMusicDetailsFragment.newInstance());
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE:
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_TWO:
                    break;
                case BaiduMusicValues.MAIN_RECEIVER_POSITION_THREE:
                    break;
            }
            fragmentTransaction.commit();
        }
    }

    //广播接收者取消注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(frameReceiver);
    }
}
