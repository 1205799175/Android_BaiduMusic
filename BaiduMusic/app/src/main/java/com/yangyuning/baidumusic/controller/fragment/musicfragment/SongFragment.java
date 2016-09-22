package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.MusicSongRvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 歌单 Fragment
 */
public class SongFragment extends AbsBaseFragment {
    private RecyclerView rv;
    private MusicSongRvAdapter musicSongRvAdapter;

    private ImageView imgPopWindow;
    private LinearLayout rootView;

    public static SongFragment newInstance() {

        Bundle args = new Bundle();

        SongFragment fragment = new SongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_song;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.music_song_rv);
        imgPopWindow = byView(R.id.music_song_popwindow);
        rootView = byView(R.id.root_view);
    }

    @Override
    protected void initDatas() {
        //初始化适配器
        musicSongRvAdapter = new MusicSongRvAdapter(context);
        //获得并解析网络数据
        getNetDatas();
        //绑定适配器
        rv.setAdapter(musicSongRvAdapter);
        //设置布局管理器
        rv.setLayoutManager(new GridLayoutManager(context, BaiduMusicValues.MV_RECYCLERVIEW_ROW_NUM));
        //弹出PopWindow
        setPopWindow();

    }

    //弹出PopWindow
    private void setPopWindow() {
        imgPopWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow pw = new PopupWindow(context);
                //设置Popwindow的宽高
                pw.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                int height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 2;
                Log.d("www", "height:" + height);
                pw.setHeight(height);
                //显示内容
                View view = LayoutInflater.from(context).inflate(R.layout.fragment_music_song_popwindow, null);
                pw.setContentView(view);
                //点击外界popWindow消失
                pw.setFocusable(true);
                pw.setOutsideTouchable(true);
                //显示
                pw.showAtLocation(rootView, Gravity.BOTTOM, 0, 100);
            }
        });
    }

    //获得并解析网络数据
    private void getNetDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_SONG, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicSongBean musicSongBean = gson.fromJson(resultStr, MusicSongBean.class);
                List<MusicSongBean.ContentBean> datas = musicSongBean.getContent();
                musicSongRvAdapter.setDatas(datas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
