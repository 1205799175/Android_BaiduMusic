package com.yangyuning.baidumusic.controller.fragment.kfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.KLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.KRotateAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendRotateVpAdater;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.KLvBean;
import com.yangyuning.baidumusic.model.bean.KRotareBean;
import com.yangyuning.baidumusic.model.bean.MusicRecommendRotateBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * K歌Fragment
 */
public class KFragment extends AbsBaseFragment implements View.OnClickListener {
    //轮播图
    private static final int TIME = 3000;
    private ViewPager viewPager;
    private List<KRotareBean.ResultBean> datas;
    private LinearLayout pointLl;// 轮播状态改变的小圆点容器
    private KRotateAdapter kRotateAdapter;
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;
    //ListView设置数据
    private MyListView myListView;
    private KLvAdapter kLvAdapter;

    //上部分类
    private LinearLayout ktv, china, usa, man, woman, team;

    public static KFragment newInstance() {
        Bundle args = new Bundle();
        KFragment fragment = new KFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_k;
    }

    @Override
    protected void initView() {
        viewPager = byView(R.id.k_rotate_vp);
        pointLl = byView(R.id.k_rotate_point_container);
        myListView = byView(R.id.k_lv);
        ktv = byView(R.id.k_ktv);
        china = byView(R.id.k_china);
        usa = byView(R.id.k_usa);
        man = byView(R.id.k_man);
        woman = byView(R.id.k_woman);
        team = byView(R.id.k_team);
        ktv.setOnClickListener(this);
        china.setOnClickListener(this);
        usa.setOnClickListener(this);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
        team.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        //初始化适配器
        kRotateAdapter = new KRotateAdapter(context);
        //获取轮播图数据
        buildDatas();
        viewPager.setAdapter(kRotateAdapter);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        viewPager.setCurrentItem(datas.size() * 100);
        // 开始轮播
        handler = new Handler();
        startRotate();

        //给ListView设置数据
        kLvAdapter = new KLvAdapter(context);
        myListView.setAdapter(kLvAdapter);
        //获取ListView网络数据
        getLisViewDatas();
    }

    //获取ListView网络数据
    private void getLisViewDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.K, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                KLvBean bean = gson.fromJson(resultStr, KLvBean.class);
                List<KLvBean.ResultBean.ItemsBean> listDatas = bean.getResult().getItems();
                kLvAdapter.setDatas(listDatas);
            }

            @Override
            public void failure() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(BaiduMusicValues.THE_ACTION_K_TO_DETAIL);
        switch (v.getId()){
            case R.id.k_ktv:
                intent.putExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_ONE);
                break;
            case R.id.k_china:
                intent.putExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_TWO);
                break;
            case R.id.k_usa:
                intent.putExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_THREE);
                break;
            case R.id.k_man:
                intent.putExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_FOUR);
                break;
            case R.id.k_woman:
                intent.putExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_FIVE);
                break;
            case R.id.k_team:
                intent.putExtra(BaiduMusicValues.K_KEY, BaiduMusicValues.MAIN_RECEIVER_POSITION_SIX);
                break;
        }
        context.sendBroadcast(intent);
    }


    //随着轮播改变小点
    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有小点设置为白色
                    for (int i = 0; i < datas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.ic_dot_unselected);
                    }
                    // 设置当前位置小点为灰色
                    ImageView iv = (ImageView) pointLl.getChildAt(position % datas.size());
                    iv.setImageResource(R.mipmap.ic_dot_selected);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // 添加轮播小点
    private void addPoints() {
        // 有多少张图加载多少个小点
        for (int i = 0; i < datas.size(); i++) {
            ImageView pointIv = new ImageView(context);
            pointIv.setPadding(5,5,5,5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            pointIv.setLayoutParams(params);

            // 设置第0页小点的为灰色
            if (i == 0) {
                pointIv.setImageResource(R.mipmap.ic_dot_selected);
            } else {
                pointIv.setImageResource(R.mipmap.ic_dot_unselected);
            }
            pointLl.addView(pointIv);
        }
    }

    // 开始轮播
    private void startRotate() {
        rotateRunnable = new Runnable() {
            @Override
            public void run() {
                int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
                if (isRotate) {
                    handler.postDelayed(rotateRunnable, TIME);
                }
            }
        };
        handler.postDelayed(rotateRunnable, TIME);
    }

    //轮播图数据
    private void buildDatas() {
        datas = new ArrayList<>();
        //获取解析轮播图网络图片
        VolleyInstance.getInstance().startResult(BaiduMusicValues.K_ROTATE, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                KRotareBean bean = gson.fromJson(resultStr, KRotareBean.class);
                datas = bean.getResult();
                //设置数据
                kRotateAdapter.setDatas(datas);

                // 添加轮播小点
                addPoints();
                // 随着轮播改变小点
                changePoints();
            }

            @Override
            public void failure() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        rotateRunnable = null;
        isRotate = false;
    }

}
