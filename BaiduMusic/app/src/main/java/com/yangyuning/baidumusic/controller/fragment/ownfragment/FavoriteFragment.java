package com.yangyuning.baidumusic.controller.fragment.ownfragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.FavoriteLvAdapter;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.MyFavoriteSongBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;
import com.yangyuning.baidumusic.model.net.DownLoadInstance;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * 我喜欢的单曲 二级 Fragment
 */
public class FavoriteFragment extends AbsBaseFragment {
    private ListView favLv;
    private ImageView backImg, bgOne, bgTwo, bgThree;
    private TextView songNum;
    private List<MyFavoriteSongBean> datas;
    private FavoriteLvAdapter favAdapter;
    private PopupWindow popupWindow;

    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void initView() {
        favLv = byView(R.id.fav_lv);
        backImg = byView(R.id.fav_back);
        songNum = byView(R.id.fav_song_num);
        bgOne = byView(R.id.fav_bg_one);
        bgTwo = byView(R.id.fav_bg_two);
        bgThree = byView(R.id.fav_bg_three);
    }

    @Override
    protected void initDatas() {
        datas = new ArrayList<>();
        favAdapter = new FavoriteLvAdapter(context);
        datas = LiteOrmInstance.getLiteOrmInstance().getLiteOrm().query(MyFavoriteSongBean.class);
        songNum.setText(context.getResources().getString(R.string.total) + datas.size() + context.getResources().getString(R.string.piece));
        favAdapter.setDatas(datas);
        favLv.setAdapter(favAdapter);
        initListener();
    }

    private void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        favAdapter.setGetMoreListener(new FavoriteLvAdapter.OnFavGetMoreListener() {
            @Override
            public void onGetMore(int position) {
                showMorePop(position);
            }
        });

    }

    //弹出PopWindow
    private void showMorePop(final int position) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_detail_more, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView title = (TextView) contentView.findViewById(R.id.pop_detail_more_title);
        ImageView nextIv = (ImageView) contentView.findViewById(R.id.pop_detail_next_iv);
        final ImageView likeIv = (ImageView) contentView.findViewById(R.id.pop_detail_like_iv);
        ImageView downLoadIv = (ImageView) contentView.findViewById(R.id.pop_detail_download_iv);
        ImageView shareIv = (ImageView) contentView.findViewById(R.id.pop_detail_share_iv);
        ImageView mvIv = (ImageView) contentView.findViewById(R.id.pop_detail_mv_iv);
        TextView mvTv = (TextView) contentView.findViewById(R.id.pop_detail_mv_tv);
        ImageView addIv = (ImageView) contentView.findViewById(R.id.pop_detail_add_iv);
        FrameLayout space = (FrameLayout) contentView.findViewById(R.id.pop_detail_space);

        mvIv.setVisibility(View.VISIBLE);
        mvTv.setVisibility(View.VISIBLE);
        likeIv.setImageResource(R.mipmap.ic_listmore_love_hl);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        title.setText(datas.get(position).getTitle());

        // 点击空白
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //添加到我喜欢的单曲
        likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToLocal(position);
                songNum.setText(context.getResources().getString(R.string.total) + LiteOrmInstance.getLiteOrmInstance().queryAll().size() + context.getResources().getString(R.string.piece));
                favAdapter.setDatas(LiteOrmInstance.getLiteOrmInstance().queryAll());
                popupWindow.dismiss();
            }
        });

        //下载歌曲
        downLoadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadInstance.getSingleDownload().DownLoad(datas.get(position).getSongId());
            }
        });
        popupWindow.showAtLocation(favLv, Gravity.BOTTOM, 0, 0);
    }

    // 加入本地数据库
    public void addToLocal(int position) {
        LiteOrmInstance.getLiteOrmInstance().deleteBySongId(datas.get(position).getSongId());
        Toast.makeText(BaiduMusicApp.getContext(), BaiduMusicValues.FAV_SONG_CANCEL, Toast.LENGTH_SHORT).show();
    }
}
