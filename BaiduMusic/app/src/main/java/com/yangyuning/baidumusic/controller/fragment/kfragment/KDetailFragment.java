package com.yangyuning.baidumusic.controller.fragment.kfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.KTopDetailLvAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.KTopDetailLvBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class KDetailFragment extends AbsBaseFragment {

    private TextView backImg;
    private ListView lv;
    private KTopDetailLvAdapter kTopAdapter;
    public static KDetailFragment newInstance(String url, String title) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("title", title);
        KDetailFragment fragment = new KDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.fragment_k_top_detail;
    }

    @Override
    protected void initView() {
        backImg = byView(R.id.k_detail_ktv_china_usa);
        lv = byView(R.id.k_detail_ktv_china_usa_lv);
    }

    @Override
    protected void initDatas() {
        kTopAdapter = new KTopDetailLvAdapter(context);
        //网络数据
        getNetData();
        lv.setAdapter(kTopAdapter);
        initListener();
    }

    private void getNetData() {
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        final String title = bundle.getString("title");
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                KTopDetailLvBean bean = gson.fromJson(resultStr, KTopDetailLvBean.class);
                List<KTopDetailLvBean.ResultBean.ItemsBean> datas = bean.getResult().getItems();
                kTopAdapter.setDatas(datas);
                backImg.setText(title);
            }

            @Override
            public void failure() {

            }
        });
    }

    private void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_K_TO_DETAIL);
                intent.putExtra(BaiduMusicValues.K_KEY,BaiduMusicValues.MAIN_RECEIVER_POSITION_MINUS_ONE);
                context.sendBroadcast(intent);
            }
        });
    }
}
