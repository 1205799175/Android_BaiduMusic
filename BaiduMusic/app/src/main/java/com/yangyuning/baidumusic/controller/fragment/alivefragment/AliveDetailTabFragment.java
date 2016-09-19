package com.yangyuning.baidumusic.controller.fragment.alivefragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.AliveRvBottomAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.AliveRvBottomBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.List;


/**
 * Created by dllo on 16/9/18.
 * 直播 上部Rv 二级界面子Fragment  复用
 */
public class AliveDetailTabFragment extends AbsBaseFragment {

    private AliveRvBottomAdapter aliveRvBottomAdapter;
    private RecyclerView rv;
    public static AliveDetailTabFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(BaiduMusicValues.THE_NEWINSTANCE_URL_KEY, url);
        AliveDetailTabFragment fragment = new AliveDetailTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_alive_detail_tab;
    }

    @Override
    protected void initView() {
        rv = byView(R.id.alive_detail_ta_rv);
    }

    @Override
    protected void initDatas() {
        //直播下部RecyClerView操作
        aliveRvBottomAdapter = new AliveRvBottomAdapter(context);
        rv.setAdapter(aliveRvBottomAdapter);
        //获取,解析上部数据
        getBottomNetDatas();
        //设置布局管理器
        rv.setLayoutManager(new GridLayoutManager(context, 2));
    }

    //获取,解析上部数据
    private void getBottomNetDatas() {
        Bundle bundle = getArguments();
        String url = bundle.getString(BaiduMusicValues.THE_NEWINSTANCE_URL_KEY);
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
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
}
