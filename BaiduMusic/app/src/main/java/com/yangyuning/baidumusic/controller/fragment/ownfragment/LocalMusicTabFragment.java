package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLocalMusicLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.OwnLvBottomAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.OwnLocalMusicLvBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.interfaces.OnChangeMusicListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/13.
 * 本地音乐 二级页面中嵌套的Fragment
 */
public class LocalMusicTabFragment extends AbsBaseFragment {

    private ListView listView;
    private OwnLocalMusicLvAdapter ownLocalMusicLvAdapter;
    private List<MusicBean> datas;
    private OnChangeMusicListener onChangeMusicListener;
    private TextView countTv;

    public static LocalMusicTabFragment newInstance() {
        Bundle args = new Bundle();
        LocalMusicTabFragment fragment = new LocalMusicTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own_local_music_tab;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.local_music_lv);
        onChangeMusicListener = (OnChangeMusicListener) context;
    }

    @Override
    protected void initDatas() {
        //初始化适配器, 设置数据
        datas = MusicService.getLocalMusicInfo();
        ownLocalMusicLvAdapter = new OwnLocalMusicLvAdapter(datas, context);
        listView.setAdapter(ownLocalMusicLvAdapter);
        //ListView行点击事件播放歌曲
        playMusic();
        initListView();
    }

    private void initListView() {
        //添加头布局
        View headerView = LayoutInflater.from(context).inflate(R.layout.item_header_local_music_lv, null);
        listView.setHeaderDividersEnabled(false);
        listView.addHeaderView(headerView);

        //添加尾布局
        View footView = LayoutInflater.from(context).inflate(R.layout.item_foot_local_music_lv, null);
        countTv = (TextView) footView.findViewById(R.id.local_music_count_tv);
        countTv.setText(datas.size() + context.getResources().getString(R.string.music));
        listView.addFooterView(footView);
    }

    //ListView行点击事件播放歌曲
    private void playMusic() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 点击本地音乐
                if (position > 0 && position <= datas.size()) {
                    MusicService.setDatas(datas);
                    onChangeMusicListener.OnChangeMuisc(datas.get(position - 1), position - 1);
                    //通知适配器刷新数据, 改变当前播放歌曲的颜色
                    ownLocalMusicLvAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
