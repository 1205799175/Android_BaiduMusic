package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.activity.MainActivity;
import com.yangyuning.baidumusic.controller.adapter.MusicRankingLvAdapter;
import com.yangyuning.baidumusic.controller.adapter.RankingDetailRvAdapter;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.MyFavoriteSongBean;
import com.yangyuning.baidumusic.model.bean.RankingDetailRvBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;
import com.yangyuning.baidumusic.model.net.DownLoadInstance;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.interfaces.OnChangeMusicListener;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/27.
 * 乐库 排行 详情
 */
public class RankingDetailFragment extends AbsBaseFragment {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView toolBarBgImg;    //顶部背景
    private TextView totalSongNum;  //歌曲数
    private ImageView backImg, downImg, shareImg;      //返回键, 下载, 分享
    private RecyclerView recyclerView;
    private RankingDetailRvAdapter rankingDetalAdapter;
    private List<RankingDetailRvBean.SongListBean> datas;
    private TextView updateData;    //更新日期
    private List<MusicBean> dataList;
    private PopupWindow popupWindow;

    public static RankingDetailFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        RankingDetailFragment fragment = new RankingDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_ranking_detail;
    }

    @Override
    protected void initView() {
        collapsingToolbarLayout = byView(R.id.rank_detail_toolbar_layout);
        toolBarBgImg = byView(R.id.rank_detail_toolbar_layout_bg);
        backImg = byView(R.id.rank_detail_back_btn);
        downImg = byView(R.id.rank_detail_down_load_all);
        shareImg = byView(R.id.rank_detail_share_song_list);
        totalSongNum = byView(R.id.rank_detail_total_songs);
        recyclerView = byView(R.id.rank_detail_rv);
        updateData = byView(R.id.music_ranking_detail_date);
    }

    @Override
    protected void initDatas() {
        dataList = new ArrayList<>();
        rankingDetalAdapter = new RankingDetailRvAdapter(context);
        //获取网络数据
        getNetData();
        recyclerView.setAdapter(rankingDetalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        initListener();
    }

    //获取网络数据
    private void getNetData() {
        datas = new ArrayList<>();
        final RankingDetailRvBean[] rankBean = {null};
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        String url = BaiduMusicValues.MUSIC_RANKING_DETAIL_HEAD + type + BaiduMusicValues.MUSIC_RANKING_DETAIL_BOTTOM;
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                rankBean[0] = gson.fromJson(resultStr, RankingDetailRvBean.class);
                datas = rankBean[0].getSong_list();
                List<String> songIds = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    songIds.add(datas.get(i).getSong_id());
                }
                getRankMusicBySongId(songIds);
                rankingDetalAdapter.setDatas(dataList);
                Picasso.with(context).load(rankBean[0].getBillboard().getPic_s210()).into(toolBarBgImg);
                if (rankBean[0].getBillboard().getBillboard_songnum() != null && !rankBean[0].getBillboard().getBillboard_songnum().equals("")){
                    totalSongNum.setText(context.getResources().getString(R.string.total) + rankBean[0].getBillboard().getBillboard_songnum() + context.getResources().getString(R.string.song));
                } else {
                    totalSongNum.setText(context.getResources().getString(R.string.total) + 2 + context.getResources().getString(R.string.song));

                }
                updateData.setText(BaiduMusicValues.UPDATE_DATA + rankBean[0].getBillboard().getUpdate_date());
            }

            @Override
            public void failure() {

            }
        });
    }

    //根据songId获取歌曲
    private void getRankMusicBySongId(List<String> songIds) {
        for (int i = 0; i < songIds.size(); i++) {
            String url = BaiduMusicValues.SONG_ULR_HEAD + songIds.get(i) + BaiduMusicValues.SONG_URL_FOOT;
            VolleyInstance.getInstance().startResult(url, new VolleyResult() {
                @Override
                public void success(String resultStr) {
                    resultStr = MainActivity.fixJsonData(resultStr);
                    // 获得歌曲实体类
                    Gson gson = new Gson();
                    MusicBean musicBean = gson.fromJson(resultStr, MusicBean.class);
                    if (musicBean.getBitrate() != null) {
                        int duration = musicBean.getBitrate().getFile_duration();
                        musicBean.getBitrate().setFile_duration(duration * 1000);
                        // 将单条数据添加到适配器数据中
                        dataList.add(musicBean);
                        rankingDetalAdapter.setDatas(dataList);
                    }
                }

                @Override
                public void failure() {

                }
            });
        }
    }

    private void initListener() {
        //点击退出详情
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        rankingDetalAdapter.setOnRvMoreClickListener(new OnRvItemClick<MusicBean>() {
            @Override
            public void onRvItemClickListener(int position, MusicBean musicBean) {
                showMorePop(musicBean, position);
            }
        });

        //播放网络歌曲
        rankingDetalAdapter.setOnRvItemClickListener(new OnRvItemClick<MusicBean>() {
            @Override
            public void onRvItemClickListener(int position, MusicBean musicBean) {
                MusicService.setDatas(dataList);
                MusicService.musicBinder.playMusicByMode(position);
                onChangeMusicListener.OnChangeMuisc(dataList.get(position), position);
            }
        });
        onChangeMusicListener = (OnChangeMusicListener) context;
    }

    private void showMorePop(final MusicBean musicBean, int position) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.pop_detail_more, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView title = (TextView) contentView.findViewById(R.id.pop_detail_more_title);
        ImageView nextIv = (ImageView) contentView.findViewById(R.id.pop_detail_next_iv);
        final ImageView likeIv = (ImageView) contentView.findViewById(R.id.pop_detail_like_iv);
        ImageView downLoadIv = (ImageView) contentView.findViewById(R.id.pop_detail_download_iv);
        ImageView shareIv = (ImageView) contentView.findViewById(R.id.pop_detail_share_iv);
        ImageView addIv = (ImageView) contentView.findViewById(R.id.pop_detail_add_iv);
        FrameLayout space = (FrameLayout) contentView.findViewById(R.id.pop_detail_space);

        //判断是否已经添加到我喜欢的单曲
        if (LiteOrmInstance.getLiteOrmInstance().queryBySongIds(MyFavoriteSongBean.class, musicBean.getSonginfo().getSong_id()).size() != 0){
            likeIv.setImageResource(R.mipmap.ic_listmore_love_hl);
        }

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        title.setText(musicBean.getSonginfo().getTitle());

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
                if (LiteOrmInstance.getLiteOrmInstance().queryBySongIds(MyFavoriteSongBean.class, musicBean.getSonginfo().getSong_id()).size() == 0) {
                    LiteOrmInstance.getLiteOrmInstance().insertOne(new MyFavoriteSongBean(musicBean.getSonginfo().getSong_id(), musicBean.getSonginfo().getTitle(), musicBean.getSonginfo().getAuthor(), musicBean.getSonginfo().getPic_radio()));
                    Toast.makeText(BaiduMusicApp.getContext(), BaiduMusicValues.FAV_SONG_ADD, Toast.LENGTH_SHORT).show();
                    likeIv.setImageResource(R.mipmap.ic_listmore_love_hl);
                } else {
                    LiteOrmInstance.getLiteOrmInstance().deleteBySongId(musicBean.getSonginfo().getSong_id());
                    Toast.makeText(BaiduMusicApp.getContext(), BaiduMusicValues.FAV_SONG_CANCEL, Toast.LENGTH_SHORT).show();
                    likeIv.setImageResource(R.mipmap.ic_listmore_love_normal);
                }
            }
        });

        //下载歌曲
        downLoadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadInstance.getSingleDownload().DownLoad(musicBean.getSonginfo().getSong_id());
            }
        });
        popupWindow.showAtLocation(recyclerView, Gravity.BOTTOM, 0, 0);
    }

}
