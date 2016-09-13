package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.OwnLvBottomAdapter;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.model.bean.OwnLvBean;
import com.yangyuning.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 我的Fragment
 */
public class OwnFragment extends AbsBaseFragment {
    private List<OwnLvBean> topDatas;
    private List<OwnLvBean> bottomDatas;
    private OwnLvAdapter ownLvAdapter;
    private OwnLvBottomAdapter ownLvBottomAdapter;
    private MyListView topLv, bottomLv;
    private TextView ownLoginTv;
    private Context context;

    private static final String THE_ACTION = "com.yangyuning.baidumusic.controller.fragment.OwnFragment";

    public static OwnFragment newInstance() {
        
        Bundle args = new Bundle();
        
        OwnFragment fragment = new OwnFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own;
    }

    @Override
    protected void initView() {
        topLv = byView(R.id.own_top_lv);
        bottomLv = byView(R.id.own_bottom_lv);
        ownLoginTv = byView(R.id.own_login_tv);
    }

    @Override
    protected void initDatas() {
        FragmentManager manager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = manager.beginTransaction();

        //我的Fragment上面的ListView
        topDatas = new ArrayList<>();
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_local_normal, "本地歌曲", "10首", R.mipmap.bt_artist_item_play_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_play_normal, "最近播放", "20首", R.mipmap.ic_songlist_detail_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_download_normal, "下载管理", "30首", R.mipmap.ic_songlist_detail_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_ktv_normal, "我的K歌", "40首", R.mipmap.ic_songlist_detail_nor));
        ownLvAdapter = new OwnLvAdapter(context, topDatas);
        topLv.setAdapter(ownLvAdapter);

        //我的Fragment下面的ListView
        bottomDatas = new ArrayList<>();
        bottomDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_like, "我最喜欢的单曲", "50首", R.mipmap.ic_songlist_detail_nor));
        bottomDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_add_nor, "新建歌单", "", 0));
        ownLvBottomAdapter = new OwnLvBottomAdapter(context, bottomDatas);
        bottomLv.setAdapter(ownLvBottomAdapter);

        //登录TextView字体
        SpannableString span = new SpannableString("立即登录, 让音乐跟着你走");
        span.setSpan(new ForegroundColorSpan(Color.argb(255, 45, 208, 242)),0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ownLoginTv.setText(span);

        //ListView点击事件进入详情页
        topLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //发送广播
                Intent intent = new Intent();
                intent.setAction(THE_ACTION);
                intent.putExtra("frame", R.id.main_frame_layout);
                intent.putExtra("id", position);
                context.sendBroadcast(intent);
            }
        });

    }
}
