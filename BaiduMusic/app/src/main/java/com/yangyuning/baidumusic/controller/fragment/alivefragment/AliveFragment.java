package com.yangyuning.baidumusic.controller.fragment.alivefragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 直播Fragment
 */
public class AliveFragment extends AbsBaseFragment {

    private AliveRvTopAdpter aliveRvTopAdpter;
    private AliveRvBottomAdapter aliveRvBottomAdapter;
    private RecyclerView topRv, bottomRv;
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
        topRv.setAdapter(aliveRvTopAdpter);
        VolleyInstance.getInstance().startResult(BaiduMusicValues.ALIVE_TOP_RECYCLERVIEW, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                AliveRvTopBean aliveRvTopBean = gson.fromJson(resultStr, AliveRvTopBean.class);
                List<AliveRvTopBean.DataBean> topDatas = aliveRvTopBean.getData();
                aliveRvTopAdpter.setDatas(topDatas);
            }

            @Override
            public void failure() {

            }
        });
        topRv.setLayoutManager(new GridLayoutManager(context, 4));

        //直播下部RecyClerView操作
        aliveRvBottomAdapter = new AliveRvBottomAdapter(context);
        bottomRv.setAdapter(aliveRvBottomAdapter);
        VolleyInstance.getInstance().startResult(BaiduMusicValues.ALIVE_BOTTOM_RECYCLERVIEW, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                AliveRvBottomBean aliveRvBottomBean = gson.fromJson(resultStr, AliveRvBottomBean.class);
                List<AliveRvBottomBean.DataBean.mDataBean> bottomDatas = aliveRvBottomBean.getData().getmData();
                aliveRvBottomAdapter.setDatas(bottomDatas);
            }

            @Override
            public void failure() {

            }
        });
        bottomRv.setLayoutManager(new GridLayoutManager(context, 2));
    }
}
