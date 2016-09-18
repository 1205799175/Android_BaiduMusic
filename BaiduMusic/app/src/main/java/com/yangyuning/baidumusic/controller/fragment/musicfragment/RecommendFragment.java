package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/9.
 * 乐库 推荐 Fragment
 */
public class RecommendFragment extends AbsBaseFragment {
    //轮播图
    private static final int TIME = 3000;

    private ViewPager viewPager;
    private List<MusicRecommendRotateBean.PicBean> datas;
    private LinearLayout pointLl;// 轮播状态改变的小圆点容器
    private RecommendRotateVpAdater recommendRotateVpAdater;
    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;

    //轮播图下方数据
    private MusicRecommendBean bean;
    private MusicRecommendBean.ResultBean resultBean;


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
        getRecommendDatas();
    }

    //获取推荐Fragment网络数据
    private void getRecommendDatas() {
        VolleyInstance.getInstance().startResult(BaiduMusicValues.MUSIC_RECOMMEND, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                bean = gson.fromJson(resultStr, MusicRecommendBean.class);
                //给组件设置数据
                setDatas();
            }

            @Override
            public void failure() {

            }
        });
    }

    //给组件设置数据
    private void setDatas() {
        resultBean = bean.getResult();
        //设置轮播图下方的分类按钮
        initKindIcon();
        //设置推荐Fragment所有的标题
        initTitles();
        //设置歌单推荐
        initSheetRecommend();
        //设置新碟上架
        initNewDisk();
        //设置热销专辑
        initHotSold();
        //设置场景电台
        initRadio();
        //设置推荐歌曲
        initRecommendSong();
        //设置原创音乐
        initOwnMusic();
        //设置最热MV推荐
        initHotMVRecommend();
        //设置乐播节目
        initHappyPro();
        //设置专栏
        initOwnColumn();
    }

    //设置专栏
    private void initOwnColumn() {
        MusicRecommendBean.ResultBean.Mod7Bean mod7Bean = bean.getResult().getMod_7();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_page_title_zero));
        views.add(byView(R.id.item_page_title_one));
        views.add(byView(R.id.item_page_title_two));
        views.add(byView(R.id.item_page_title_three));

        for (int i = 0; i < views.size(); i++) {
            ImageView iconImg = (ImageView) views.get(i).findViewById(R.id.mine_song_sheet_icon_img);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.mine_song_sheet_name_tv);
            TextView descTv = (TextView) views.get(i).findViewById(R.id.mine_song_sheet_piece_tv);
            LinearLayout ll = (LinearLayout) views.get(i).findViewById(R.id.item_mine_song_sheet_ll);
            int width = ll.getMeasuredWidth();
            Picasso.with(context).load(mod7Bean.getResult().get(i).getPic()).resize(width, width).into(iconImg);
            titleTv.setText(mod7Bean.getResult().get(i).getTitle());
            descTv.setText(mod7Bean.getResult().get(i).getDesc());
        }
    }

    //设置乐播节目
    private void initHappyPro() {
        MusicRecommendBean.ResultBean.RadioBean radioBean = bean.getResult().getRadio();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_happy_pro_zero));
        views.add(byView(R.id.item_happy_pro_one));
        views.add(byView(R.id.item_happy_pro_two));
        views.add(byView(R.id.item_happy_pro_three));
        views.add(byView(R.id.item_happy_pro_four));
        views.add(byView(R.id.item_happy_pro_five));

        for (int i = 0; i < views.size(); i++) {
            LinearLayout ll = (LinearLayout) views.get(i).findViewById(R.id.item_song_with_singer_bg_ll);
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_layout_with_singer_img);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_title_tv);
            TextView singerTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_singer_tv);
            //设置数据
            titleTv.setText(radioBean.getResult().get(i).getTitle());
            singerTv.setText("");
            // 调整大小
            int width = ll.getMeasuredWidth();
            Picasso.with(context).load(radioBean.getResult().get(i).getPic()).resize(width, width).into(img);
        }
    }

    //设置最热MV推荐
    private void initHotMVRecommend() {
        MusicRecommendBean.ResultBean.Mix5Bean mix5Bean = bean.getResult().getMix_5();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_original_zero));
        views.add(byView(R.id.item_original_one));
        views.add(byView(R.id.item_original_two));
        views.add(byView(R.id.item_original_three));
        views.add(byView(R.id.item_original_four));
        views.add(byView(R.id.item_original_five));

        for (int i = 0; i < views.size(); i++) {
            LinearLayout ll = (LinearLayout) views.get(i).findViewById(R.id.item_song_with_singer_bg_ll);
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_layout_with_singer_img);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_title_tv);
            TextView singerTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_singer_tv);
            //数据
            titleTv.setText(mix5Bean.getResult().get(i).getTitle());
//            Log.d("www","------" + mix5Bean.getResult().get(i).getTitle());
            singerTv.setText(mix5Bean.getResult().get(i).getAuthor());
            // 调整大小
            int width = ll.getMeasuredWidth();
            Picasso.with(context).load(mix5Bean.getResult().get(i).getPic()).resize(width, width).into(img);
        }
    }

    //设置原创音乐
    private void initOwnMusic() {
        // 删除多余的控件
        MusicRecommendBean.ResultBean.Mix9Bean mix9bean = bean.getResult().getMix_9();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_original_zero));
        views.add(byView(R.id.item_original_one));
        views.add(byView(R.id.item_original_two));

        for (int i = 0; i < views.size(); i++) {
            LinearLayout ll = (LinearLayout) views.get(i).findViewById(R.id.item_song_with_singer_bg_ll);
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_layout_with_singer_img);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_title_tv);
            TextView singerTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_singer_tv);
            // 设置新碟上架文本数据
            titleTv.setText(mix9bean.getResult().get(i).getTitle());
            singerTv.setText("");
            // 调整大小

            int width = ll.getMeasuredWidth();
            // 设置"新碟上架-图片背景"
            Picasso.with(context).load(mix9bean.getResult().get(i).getPic()).resize(width, width).into(img);

        }

    }

    //设置推荐歌曲
    private void initRecommendSong() {
        MusicRecommendBean.ResultBean.RecsongBean recsongBean = bean.getResult().getRecsong();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_today_recommend_song0));
        views.add(byView(R.id.item_today_recommend_song1));
        views.add(byView(R.id.item_today_recommend_song2));
        RelativeLayout rl = byView(R.id.today_recommend_rl);
        for (int i = 0; i < views.size(); i++) {
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.today_recommend_title_tv);
            TextView singerTv = (TextView) views.get(i).findViewById(R.id.today_recommend_singer_tv);
            CircleImageView cirImg = (CircleImageView) views.get(i).findViewById(R.id.today_recommend_cirimg);
            titleTv.setText(recsongBean.getResult().get(i).getTitle());
            singerTv.setText(recsongBean.getResult().get(i).getAuthor());
            int width = rl.getMeasuredWidth();
            Picasso.with(context).load(recsongBean.getResult().get(i).getPic_premium()).resize(width, width).into(cirImg);
        }
    }

    //设置场景电台
    private void initRadio() {
        List<MusicRecommendBean.ResultBean.SceneBean.ScenResultBean.ActionBean> actionBean = bean.getResult().getScene().getResult().getAction();
        int[] imgs = new int[]{R.mipmap.img_recommend_lebo_blue, R.mipmap.img_recommend_lebo_cyan, R.mipmap.img_recommend_lebo_green, R.mipmap.img_recommend_lebo_orange};
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_scene_zero));
        views.add(byView(R.id.item_scene_one));
        views.add(byView(R.id.item_scene_two));
        views.add(byView(R.id.item_scene_three));
        for (int i = 0; i < views.size(); i++) {
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_center);
            TextView tvBottom = (TextView) views.get(i).findViewById(R.id.item_song_center_bottom);
            ImageView imgListent = (ImageView) views.get(i).findViewById(R.id.item_song_listen_img);
            ImageView imgBack = (ImageView) views.get(i).findViewById(R.id.item_song_bg_img);
            imgListent.setVisibility(View.GONE);
            imgBack.setImageResource(imgs[i]);
            Picasso.with(context).load(actionBean.get(i).getIcon_android()).into(img);
            tvBottom.setText(actionBean.get(i).getScene_name());

        }

    }

    //设置热销专辑
    private void initHotSold() {
        MusicRecommendBean.ResultBean.Mix22Bean mix22Bean = bean.getResult().getMix_22();

        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_hot_zero));
        views.add(byView(R.id.item_hot_one));
        views.add(byView(R.id.item_hot_two));
        for (int i = 0; i < views.size(); i++) {
            LinearLayout ll = (LinearLayout) views.get(i).findViewById(R.id.item_song_with_singer_bg_ll);
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_layout_with_singer_img);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_title_tv);
            TextView singerTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_singer_tv);
            // 设置数据
            titleTv.setText(mix22Bean.getResult().get(i).getTitle());
            singerTv.setText(mix22Bean.getResult().get(i).getAuthor());
            // 调整大小
            int width = ll.getMeasuredWidth();
            // 设置图片背景"
            Picasso.with(context).load(mix22Bean.getResult().get(i).getPic()).resize(width, width).into(img);
        }

    }

    //设置新碟上架
    private void initNewDisk() {
        MusicRecommendBean.ResultBean.hanHongMix1Bean mix1Bean = resultBean.getMix_1();

        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_new_disk_zero));
        views.add(byView(R.id.item_new_disk_one));
        views.add(byView(R.id.item_new_disk_two));
        views.add(byView(R.id.item_new_disk_three));
        views.add(byView(R.id.item_new_disk_four));
        views.add(byView(R.id.item_new_disk_five));

        for (int i = 0; i < views.size(); i++) {
            LinearLayout ll = (LinearLayout) views.get(i).findViewById(R.id.item_song_with_singer_bg_ll);
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_layout_with_singer_img);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_title_tv);
            TextView singerTv = (TextView) views.get(i).findViewById(R.id.item_song_with_singer_singer_tv);
            // 设置数据
            titleTv.setText(mix1Bean.getResult().get(i).getTitle());
            singerTv.setText(mix1Bean.getResult().get(i).getAuthor());
            // 调整大小
            int width = ll.getMeasuredWidth();
            // 设置图片背景"
            Picasso.with(context).load(mix1Bean.getResult().get(i).getPic()).resize(width, width).into(img);
        }
    }

    //设置歌单推荐
    private void initSheetRecommend() {
        //获得歌单数据
        MusicRecommendBean.ResultBean.DiyBean diyBean = resultBean.getDiy();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_sheet_recommend_zero));
        views.add(byView(R.id.item_sheet_recommend_one));
        views.add(byView(R.id.item_sheet_recommend_two));
        views.add(byView(R.id.item_sheet_recommend_three));
        views.add(byView(R.id.item_sheet_recommend_four));
        views.add(byView(R.id.item_sheet_recommend_five));

        for (int i = 0; i < views.size(); i++) {
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_song_bg_img);
            TextView countTv = (TextView) views.get(i).findViewById(R.id.item_song_count_tv);
            TextView titleTv = (TextView) views.get(i).findViewById(R.id.item_song_disc_tv);
            // 设置标题
            String title = diyBean.getResult().get(i).getTitle();
            titleTv.setText(title);
            // 设置收听数
            int count = diyBean.getResult().get(i).getListenum();
            countTv.setText(String.valueOf(count));
            // 调整大小
            int width = img.getMeasuredWidth();
            // 设置图片背景"
            Picasso.with(context).load(diyBean.getResult().get(i).getPic()).resize(width, width).into(img);
        }
    }

    //设置推荐Fragment所有的标题
    private void initTitles() {
        List<View> list = new ArrayList<>();
        list.add(byView(R.id.sheet_recommend_title_layout));
        list.add(byView(R.id.new_disk_title_layout));
        list.add(byView(R.id.hot_sale_title_layout));
        list.add(byView(R.id.sence_radio_title_layout));
        list.add(byView(R.id.today_recommend_title_layout));
        list.add(byView(R.id.original_title_layout));
        list.add(byView(R.id.hot_mv_title_layout));
        list.add(byView(R.id.happy_play_title_layout));
        list.add(byView(R.id.page_title_layout));
        List<MusicRecommendBean.ModuleBean> moduleList = bean.getModule();
        // 删除多余的标题和图片
        for (int i = 0; i < moduleList.size(); i++) {
            if (moduleList.get(i).getPicurl().equals("") || moduleList.get(i).getTitle().equals("音乐导航")) {
                moduleList.remove(i);
                i = -1;
                continue;
            }
        }
        bean.setModule(moduleList);
        for (int i = 0; i < list.size(); i++) {
            ImageView iconImg = (ImageView) list.get(i).findViewById(R.id.item_icon_title_img);
            TextView titleTv = (TextView) list.get(i).findViewById(R.id.item_icon_title_title_tv);
            String path = bean.getModule().get(i).getPicurl();
            Picasso.with(context).load(path).resize(20, 20).into(iconImg);
            String title = bean.getModule().get(i).getTitle();
            titleTv.setText(title);
        }

    }

    //设置轮播图下方的分类按钮
    private void initKindIcon() {
        MusicRecommendBean.ResultBean.EntryBean entryBean = resultBean.getEntry();
        List<View> views = new ArrayList<>();
        views.add(byView(R.id.item_song_kinds));
        views.add(byView(R.id.item_all_kinds));
        views.add(byView(R.id.item_recommend_today));
        for (int i = 0; i < views.size(); i++) {
            ImageView img = (ImageView) views.get(i).findViewById(R.id.item_img);
            TextView tv = (TextView) views.get(i).findViewById(R.id.item_tv);
            tv.setText(entryBean.getResult().get(i).getTitle());
            int width = img.getMeasuredWidth();
            Picasso.with(context).load(entryBean.getResult().get(i).getIcon()).resize(width, width).into(img);
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
