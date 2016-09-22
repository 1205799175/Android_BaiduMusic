package com.yangyuning.baidumusic.controller.fragment.alivefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.AliveRvBottomAdapter;
import com.yangyuning.baidumusic.controller.adapter.AliveRvTopAdpter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.AliveRvBottomBean;
import com.yangyuning.baidumusic.model.bean.AliveRvTopBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 直播Fragment
 */
public class AliveFragment extends AbsBaseFragment {

    private AliveRvTopAdpter aliveRvTopAdpter;
    private AliveRvBottomAdapter aliveRvBottomAdapter;
    private RecyclerView topRv, bottomRv;
    private int topRvSize = 0, bottomRvSize = 0;

    public static AliveFragment newInstance() {
        Bundle args = new Bundle();
        AliveFragment fragment = new AliveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_alive;
    }

    @Override
    protected void initView() {
        topRv = byView(R.id.alive_top_rv);
        bottomRv = byView(R.id.alive_bottom_rv);
    }

    @Override
    protected void initDatas() {
        //直播上部RecyClerView操作
        aliveRvTopAdpter = new AliveRvTopAdpter(context);
        //获取,解析数据
        getTopNetDatas();
        topRv.setAdapter(aliveRvTopAdpter);
        //设置布局管理器
        topRv.setLayoutManager(new GridLayoutManager(context, 4));
        //点击事件, 进入二级页面
        addTopRvListener();

        //直播下部RecyClerView操作
        aliveRvBottomAdapter = new AliveRvBottomAdapter(context);
        //获取,解析上部数据
        getBottomNetDatas();
        bottomRv.setAdapter(aliveRvBottomAdapter);
        //设置布局管理器
        bottomRv.setLayoutManager(new GridLayoutManager(context, 2));

    }

    //上部Rv点击事件, 进入二级页面
    private void addTopRvListener() {
        aliveRvTopAdpter.setOnRvItemClick(new OnRvItemClick<AliveRvTopBean.DataBean>() {
            @Override
            public void onRvItemClickListener(int position, AliveRvTopBean.DataBean dataBean) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_ALIVE_RV);
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, position);
                //长度
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, topRvSize);
                context.sendBroadcast(intent);
            }
        });
    }

    //获取,解析下部数据
    private void getBottomNetDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.ALIVE_BOTTOM_RECYCLERVIEW, new VolleyResult() {

            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                AliveRvBottomBean aliveRvBottomBean = gson.fromJson(resultStr, AliveRvBottomBean.class);
                List<AliveRvBottomBean.DataBean.mDataBean> bottomDatas =  aliveRvBottomBean.getData().getmData();
                aliveRvBottomAdapter.setDatas(bottomDatas);
            }

            @Override
            public void failure() {

            }
        });
    }

    //获取,解析上部数据
    private void getTopNetDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.ALIVE_TOP_RECYCLERVIEW, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                AliveRvTopBean aliveRvTopBean = gson.fromJson(resultStr, AliveRvTopBean.class);
                List<AliveRvTopBean.DataBean> topDatas = aliveRvTopBean.getData();
                //获得数据的长度用广播传给MainActivity, Fragment跳转判断用
                topRvSize = topDatas.size();
                aliveRvTopAdpter.setDatas(topDatas);
            }

            @Override
            public void failure() {

            }
        });
    }
}
