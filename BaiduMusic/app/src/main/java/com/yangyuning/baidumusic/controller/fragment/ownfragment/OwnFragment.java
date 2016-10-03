package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.OwnLvBottomAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.OwnLvBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
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

    public static OwnFragment newInstance() {
        
        Bundle args = new Bundle();
        
        OwnFragment fragment = new OwnFragment();
        fragment.setArguments(args);
        return fragment;
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
        //获取本地歌曲数据
        List<MusicBean> datas = MusicService.getLocalMusicInfo();
        //我的Fragment上面的ListView
        topDatas = new ArrayList<>();
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_local_normal, getString(R.string.own_lv_local_music), datas.size() + "首", R.mipmap.bt_artist_item_play_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_play_normal, getString(R.string.own_lv_recently_play), getString(R.string.own_lv_song_number), R.mipmap.ic_songlist_detail_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_download_normal, getString(R.string.own_lv_download_manage), getString(R.string.own_lv_song_number), R.mipmap.ic_songlist_detail_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_ktv_normal, getString(R.string.own_lv_my_k), getString(R.string.own_lv_song_number), R.mipmap.ic_songlist_detail_nor));
        ownLvAdapter = new OwnLvAdapter(context, topDatas);
        topLv.setAdapter(ownLvAdapter);

        //我的Fragment下面的ListView
        bottomDatas = new ArrayList<>();
        bottomDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_like, getString(R.string.own_lv_my_like), getString(R.string.own_lv_song_number), R.mipmap.ic_songlist_detail_nor));
        bottomDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_add_nor, getString(R.string.own_lv_new_create), "", 0));
        ownLvBottomAdapter = new OwnLvBottomAdapter(context, bottomDatas);
        bottomLv.setAdapter(ownLvBottomAdapter);

        //登录TextView字体
        SpannableString span = new SpannableString(getString(R.string.own_lv_login));
        span.setSpan(new ForegroundColorSpan(Color.argb(255, 45, 208, 242)),0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ownLoginTv.setText(span);

        //ListView点击事件进入详情页
        topLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //发送广播
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_OWN_LOCAL);
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, position);
                context.sendBroadcast(intent);
            }
        });

    }
}
