package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.RecommendDiyRvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendEntryRvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendMix1RvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendMix22RvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendMix5RvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendMix9RvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendMod7RvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendRadioRvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendRecsongRvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RecommendRotateVpAdater;
import com.yangyuning.baidumusic.controller.adapter.RecommendScenRcAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicRecommendBean;
import com.yangyuning.baidumusic.model.bean.MusicRecommendRotateBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.ScreenSizeUtil;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/9.
 * 乐库 推荐 Fragment
 */
public class RecommendFragment extends AbsBaseFragment implements View.OnClickListener {
    //轮播图
    private static final int TIME = 3000;
    private ViewPager viewPager;
    private List<MusicRecommendRotateBean.PicBean> datas;
    private LinearLayout pointLl;// 轮播状态改变的小圆点容器
    private RecommendRotateVpAdater recommendRotateVpAdater;
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    private FrameLayout frameLayout;
    //图标
    private ImageView diyIcon, mix1Icon, mix22Icon, scenIcon, recsongIcon, mix9Icon, mix5Icon, radioIcon, mod7Icon;

    //数据
    List<MusicRecommendBean.ResultBean.EntryBean.entryResultBean> entryResultBeen;//轮播图下三个图标
    List<MusicRecommendBean.ResultBean.DiyBean.diyResultBean> diyResultBeen;//歌单推荐
    List<MusicRecommendBean.ResultBean.hanHongMix1Bean.mResultBean> mix1Been;//新碟上架
    List<MusicRecommendBean.ResultBean.Mix22Bean.mix22ResultBean> mix22ResultBeen; //热销专辑
    List<MusicRecommendBean.ResultBean.SceneBean.ScenResultBean.ActionBean> scenBeen; //场景电台
    List<MusicRecommendBean.ResultBean.RecsongBean.recsongResultBean> recsongResultBeen; //今日推荐歌曲
    List<MusicRecommendBean.ResultBean.Mix9Bean.mix9ResultBean> mix9ResultBeen; //原创音乐
    List<MusicRecommendBean.ResultBean.Mix5Bean.mix5ResultBean> mix5ResultBeen; //最热MV推荐
    List<MusicRecommendBean.ResultBean.RadioBean.radioResultBean> radioResultBeen; //乐播节目
    List<MusicRecommendBean.ResultBean.Mod7Bean.mod7ResultBean> mod7ResultBeen; //专栏

    //定义RV
    private RecyclerView recommendEntryRv, recommendDiyRv, recommendMix1Rv, recommendMix22Rv,
            recommendScenRv, recommendRecsongRv, recommendmix9Rv,
            recommendMix5Rv, recommendRadioRv, recommendMod7Rv;

    //适配器
    private RecommendEntryRvAdapter entryAdapter;
    private RecommendDiyRvAdapter diyRvAdapter;
    private RecommendMix1RvAdapter mix1RvAdapter;
    private RecommendMix22RvAdapter mix22RvAdapter;
    private RecommendScenRcAdapter scenRcAdapter;
    private RecommendRecsongRvAdapter recsongRvAdapter;
    private RecommendMix9RvAdapter mix9RvAdapter;
    private RecommendMix5RvAdapter mix5RvAdapter;
    private RecommendRadioRvAdapter radioRvAdapter;
    private RecommendMod7RvAdapter mod7RvAdapter;

    //歌单推荐 更多跳转
    private TextView diyMore;

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
        recommendEntryRv = byView(R.id.recommend_entry_recyclerView);
        recommendDiyRv = byView(R.id.recommend_diy_recyclerview);
        recommendMix1Rv = byView(R.id.recommend_mix1_recyclerview);
        recommendMix22Rv = byView(R.id.recommend_mix22_recyclerview);
        recommendScenRv = byView(R.id.recommend_scene_recyclerview);
        recommendRecsongRv = byView(R.id.recommend_recsong_recyclerview);
        recommendmix9Rv = byView(R.id.recommend_mix9_recyclerview);
        recommendMix5Rv = byView(R.id.recommend_mix5_recyclerview);
        recommendRadioRv = byView(R.id.recommend_radio_recyclerview);
        recommendMod7Rv = byView(R.id.recommend_mod7_recyclerview);

        frameLayout = byView(R.id.recommend_frame_rotate);

        diyIcon = byView(R.id.recommend_diy_icon);
        mix1Icon = byView(R.id.recommend_mix1_icon);
        mix22Icon = byView(R.id.recommend_mix22_icon);
        scenIcon = byView(R.id.recommend_scene_icon);
        recsongIcon = byView(R.id.recommend_recsong_icon);
        mix9Icon = byView(R.id.recommend_mix9_icon);
        mix5Icon = byView(R.id.recommend_mix5_icon);
        radioIcon = byView(R.id.recommend_radio_icon);
        mod7Icon = byView(R.id.recommend_mod7_icon);

        diyMore = byView(R.id.recommend_diy_more);
        diyMore.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        // 重新设置轮播图的高度
        ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
        params.height = ScreenSizeUtil.getScreenSize(ScreenSizeUtil.ScreenState.HEIGHT) / 4;
        frameLayout.setLayoutParams(params);
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

        //获取网络数据, 适配器操作
        //初始化适配器
        initAdapter();
        getNetDatas();
        doAdapter();
        //点击事件
        initListener();
    }

    private void initListener() {
        entryAdapter.setOnRvItemClick(new OnRvItemClick<MusicRecommendBean.ResultBean.EntryBean.entryResultBean>() {
            @Override
            public void onRvItemClickListener(int position, MusicRecommendBean.ResultBean.EntryBean.entryResultBean entryResultBean) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_RECOMMEND_SONGER);
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, position);
                context.sendBroadcast(intent);
            }
        });
    }

    private void initAdapter() {
        entryAdapter = new RecommendEntryRvAdapter(context);
        diyRvAdapter = new RecommendDiyRvAdapter(context);
        mix1RvAdapter = new RecommendMix1RvAdapter(context);
        mix22RvAdapter = new RecommendMix22RvAdapter(context);
        scenRcAdapter = new RecommendScenRcAdapter(context);
        recsongRvAdapter = new RecommendRecsongRvAdapter(context);
        mix9RvAdapter = new RecommendMix9RvAdapter(context);
        mix5RvAdapter = new RecommendMix5RvAdapter(context);
        radioRvAdapter = new RecommendRadioRvAdapter(context);
        mod7RvAdapter = new RecommendMod7RvAdapter(context);
    }

    private void doAdapter() {
        recommendEntryRv.setAdapter(entryAdapter);
        recommendEntryRv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendDiyRv.setAdapter(diyRvAdapter);
        recommendDiyRv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendMix1Rv.setAdapter(mix1RvAdapter);
        recommendMix1Rv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendMix22Rv.setAdapter(mix22RvAdapter);
        recommendMix22Rv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendScenRv.setAdapter(scenRcAdapter);
        recommendScenRv.setLayoutManager(new GridLayoutManager(context, 4));

        recommendRecsongRv.setAdapter(recsongRvAdapter);
        recommendRecsongRv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));

        recommendmix9Rv.setAdapter(mix9RvAdapter);
        recommendmix9Rv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendMix5Rv.setAdapter(mix5RvAdapter);
        recommendMix5Rv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendRadioRv.setAdapter(radioRvAdapter);
        recommendRadioRv.setLayoutManager(new GridLayoutManager(context, 3));

        recommendMod7Rv.setAdapter(mod7RvAdapter);
        recommendMod7Rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    //获取网络数据.适配器操作, 设置布局管理器
    private void getNetDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_RECOMMEND, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                MusicRecommendBean bean = gson.fromJson(resultStr, MusicRecommendBean.class);
                //轮播图下三个图标
                entryResultBeen = bean.getResult().getEntry().getResult();
                entryAdapter.setDatas(entryResultBeen);

                //歌单推荐
                diyResultBeen = bean.getResult().getDiy().getResult();
                diyRvAdapter.setDatas(diyResultBeen);

                //新碟上架
                mix1Been = bean.getResult().getMix_1().getResult();
                mix1RvAdapter.setDatas(mix1Been);

                //热销专辑
                mix22ResultBeen = bean.getResult().getMix_22().getResult();
                mix22RvAdapter.setDatas(mix22ResultBeen);

                //场景电台
                scenBeen = bean.getResult().getScene().getResult().getAction();
                scenRcAdapter.setDatas(scenBeen);

                //今日推荐歌曲
                recsongResultBeen = bean.getResult().getRecsong().getResult();
                recsongRvAdapter.setDatas(recsongResultBeen);

                //原创音乐
                mix9ResultBeen = bean.getResult().getMix_9().getResult();
                mix9RvAdapter.setDatas(mix9ResultBeen);

                //最热MV推荐
                mix5ResultBeen = bean.getResult().getMix_5().getResult();
                mix5RvAdapter.setDatas(mix5ResultBeen);

                //乐播节目
                radioResultBeen = bean.getResult().getRadio().getResult();
                radioRvAdapter.setDatas(radioResultBeen);

                //专栏
                mod7ResultBeen = bean.getResult().getMod_7().getResult();
                mod7RvAdapter.setDatas(mod7ResultBeen);

                //标题图标
                List<MusicRecommendBean.ModuleBean> Iconbean = bean.getModule();
                Picasso.with(context).load(Iconbean.get(3).getPicurl()).resize(30,30).into(diyIcon);
                Picasso.with(context).load(Iconbean.get(5).getPicurl()).resize(30,30).into(mix1Icon);
                Picasso.with(context).load(Iconbean.get(6).getPicurl()).resize(30,30).into(mix22Icon);
                Picasso.with(context).load(Iconbean.get(8).getPicurl()).resize(30,30).into(scenIcon);
                Picasso.with(context).load(Iconbean.get(9).getPicurl()).resize(30,30).into(recsongIcon);
                Picasso.with(context).load(Iconbean.get(10).getPicurl()).resize(30,30).into(mix9Icon);
                Picasso.with(context).load(Iconbean.get(11).getPicurl()).resize(30,30).into(mix5Icon);
                Picasso.with(context).load(Iconbean.get(12).getPicurl()).resize(30,30).into(radioIcon);
                Picasso.with(context).load(Iconbean.get(13).getPicurl()).resize(30,30).into(mod7Icon);
            }

            @Override
            public void failure() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recommend_diy_more:
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_RECOMMEND_TO_SONG);
                context.sendBroadcast(intent);
                break;
        }
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
            pointIv.setPadding(5, 5, 5, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
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
