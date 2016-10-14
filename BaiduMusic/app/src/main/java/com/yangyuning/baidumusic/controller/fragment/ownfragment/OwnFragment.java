package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.OwnLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.OwnLvBottomAdapter;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.DownLoadBean;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.MyFavoriteSongBean;
import com.yangyuning.baidumusic.model.bean.OwnLvBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.view.MyListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/9/9.
 * 我的Fragment
 */
public class OwnFragment extends AbsBaseFragment {
    private List<OwnLvBean> topDatas;
    private List<OwnLvBean> bottomDatas;
    private OwnLvAdapter ownLvAdapter;
    private OwnLvBottomAdapter ownLvBottomAdapter;
    private MyListView topLv, bottomLv;
    private TextView ownLoginTv;
    private ImageView imageView;

    public static OwnFragment newInstance() {
        Bundle args = new Bundle();
        OwnFragment fragment = new OwnFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_own;
    }

    @Override
    protected void initView() {
        topLv = byView(R.id.own_top_lv);
        bottomLv = byView(R.id.own_bottom_lv);
        ownLoginTv = byView(R.id.own_login_tv);
        imageView = byView(R.id.own_login_iv);
    }

    @Override
    protected void initDatas() {
        initListView();
        initListener();

        //登录TextView字体
        SpannableString span = new SpannableString(getString(R.string.own_lv_login));
        span.setSpan(new ForegroundColorSpan(Color.argb(255, 45, 208, 242)),0, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ownLoginTv.setText(span);
    }

    private void initListView() {
        //获取本地歌曲数据
        List<MusicBean> datas = MusicService.getLocalMusicInfo();
        //我的Fragment上面的ListView
        topDatas = new ArrayList<>();
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_local_normal, getString(R.string.own_lv_local_music), datas.size() + context.getResources().getString(R.string.piece), R.mipmap.bt_artist_item_play_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_play_normal, getString(R.string.own_lv_recently_play), datas.size() + context.getResources().getString(R.string.piece), R.mipmap.ic_songlist_detail_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_download_normal, getString(R.string.own_lv_download_manage), LiteOrmInstance.getLiteOrmInstance().queryData(DownLoadBean.class).size() + context.getResources().getString(R.string.piece), R.mipmap.ic_songlist_detail_nor));
        topDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_ktv_normal, getString(R.string.own_lv_my_k), datas.size() + context.getResources().getString(R.string.piece), R.mipmap.ic_songlist_detail_nor));
        ownLvAdapter = new OwnLvAdapter(context, topDatas);
        topLv.setAdapter(ownLvAdapter);

        //我的Fragment下面的ListView
        bottomDatas = new ArrayList<>();

        bottomDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_like, getString(R.string.own_lv_my_like), LiteOrmInstance.getLiteOrmInstance().getLiteOrm().query(MyFavoriteSongBean.class).size() + context.getResources().getString(R.string.piece), R.mipmap.ic_songlist_detail_nor));
        bottomDatas.add(new OwnLvBean(R.mipmap.ic_mymusic_add_nor, getString(R.string.own_lv_new_create), "0" + context.getResources().getString(R.string.songlist_num), 0));
        ownLvBottomAdapter = new OwnLvBottomAdapter(context, bottomDatas);
        bottomLv.setAdapter(ownLvBottomAdapter);
    }

    private void initListener() {
        //ListView点击事件进入详情页
        topLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //发送广播
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_OWN_LOCAL);
                intent.putExtra(BaiduMusicValues.THE_ACTION_KEY_POAITION, position);
                context.sendBroadcast(intent);
            }
        });

        bottomLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction(BaiduMusicValues.THE_ACTION_OWN_FAV);
                intent.putExtra("fav", position);
                context.sendBroadcast(intent);
            }
        });

        ownLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取第三方平台
                Platform platform = ShareSDK.getPlatform(context, QQ.NAME);
                //授权
                platform.authorize();
                //获取用户信息
                platform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        //获取QQ的头像和名字
                        PlatformDb db = platform.getDb();
                        String name = db.getUserName();
                        String icon = db.getUserIcon();
                        ownLoginTv.setText(name);
                        Picasso.with(context).load(icon).into(imageView);
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                    }
                });
            }
        });

    }
}
