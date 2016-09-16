package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.RecommendRotateVpAdater;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.model.bean.MusicRecommendRotateBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/9.
 * 乐库 推荐 Fragment
 */
public class RecommendFragment extends AbsBaseFragment {
    private static final int TIME = 3000;

    private ViewPager viewPager;
    private List<MusicRecommendRotateBean.PicBean> datas;
    private LinearLayout pointLl;// 轮播状态改变的小圆点容器
    private RecommendRotateVpAdater recommendRotateVpAdater;
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    private LayoutInflater inflater;
    //轮播图下方的分类
    private List<MusicRecommendBean.ResultBean.EntryBean> hotSoldDatas;


    public static RecommendFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        viewPager = byView(R.id.recommend_rotate_vp);
        pointLl = byView(R.id.recommend_rotate_point_container);
    }

    @Override
    protected void initDatas() {
        inflater = LayoutInflater.from(context);

        //初始化适配器
        recommendRotateVpAdater = new RecommendRotateVpAdater(context);
        //获取轮播图数据
        buildDatas();
        viewPager.setAdapter(recommendRotateVpAdater);
        // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
        // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
        viewPager.setCurrentItem(datas.size() * 100);
        // 开始轮播
        handler = new Handler();
        startRotate();

        //获取推荐Fragment网络数据
//        getRecommendDatas();
        //给组件设置数据
        setDatas();
    }

    //获取推荐Fragment网络数据
//    private void getRecommendDatas() {
//        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_RECOMMEND, new VolleyResult() {
//            @Override
//            public void success(String resultStr) {
//                Gson gson = new Gson();
//                MusicRecommendBean recommendBean = gson.fromJson(resultStr, MusicRecommendBean.class);
//                List<MusicRecommendBean> datas = (List<MusicRecommendBean>) recommendBean;
//            }
//            @Override
//            public void failure() {
//
//            }
//        });
//    }
    //给组件设置数据
    private void setDatas() {

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
        VolleyInstance.getInstance().startResult(BaiduMusicValues.RECOMMEND_ROTATE, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicRecommendRotateBean bean = gson.fromJson(resultStr, MusicRecommendRotateBean.class);
                datas = bean.getPic();
                //设置数据
                recommendRotateVpAdater.setDatas(datas);

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
        isRotate = false;
    }
}
