package com.yangyuning.baidumusic.controller.fragment.musicfragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yangyuning.baidumusic.controller.adapter.SongDetailRvAdapter;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.controller.fragment.AbsBaseFragment;
import com.yangyuning.baidumusic.controller.services.MusicService;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.bean.MusicSongBean;
import com.yangyuning.baidumusic.model.bean.MyFavoriteSongBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;
import com.yangyuning.baidumusic.model.net.DownLoadInstance;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;
import com.yangyuning.baidumusic.utils.interfaces.OnChangeMusicListener;
import com.yangyuning.baidumusic.utils.interfaces.OnRvItemClick;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/9/29.
 * 歌单 详情
 */
public class SongDetailFragment extends AbsBaseFragment {

    private ImageView backImg, bgImg, favList;//退出, 背景图
    private TextView styleTv, countTv, favTv, songNumTv, titleTv;
    private ImageView favImg, downImg, shareImg;
    private RecyclerView rv;
    private SongDetailRvAdapter songDetailRvAdapter;
    private List<MusicBean> dataList;
    private OnChangeMusicListener onChangeMusicListener;
    private PopupWindow popupWindow;
    private MusicBean musicBean;

    public static SongDetailFragment newInstance(Bundle bundle) {
        SongDetailFragment fragment = new SongDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_song_detail;
    }

    @Override
    protected void initView() {
        titleTv = byView(R.id.music_song_detail_title);
        bgImg = byView(R.id.song_detail_toolbar_layout_bg);
        backImg = byView(R.id.music_song_detail_back_btn);
        styleTv = byView(R.id.music_song_detail_style);
        countTv = byView(R.id.music_song_detail_count);
        favTv = byView(R.id.music_song_detail_fav_tv);
        songNumTv = byView(R.id.msuic_song_detail_total_songs);
        favImg = byView(R.id.music_song_detail_fav);
        downImg = byView(R.id.music_song_detail_down_load_all);
        shareImg = byView(R.id.music_song_detail_share_song_list);
        rv = byView(R.id.music_song_detail_rv);
        favList = byView(R.id.music_song_detail_fav);
    }

    @Override
    protected void initDatas() {
        songDetailRvAdapter = new SongDetailRvAdapter(context);
        //解析网络数据
        getNetDatas();
        initListener();
        rv.setAdapter(songDetailRvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(context));
    }

    //获取网络数据
    private void getNetDatas() {
        Bundle bundle = getArguments();
        final MusicSongBean.ContentBean contentBean = (MusicSongBean.ContentBean) bundle.getSerializable("contentBean");
        List<String> songIds = contentBean.getSongIds();
        dataList = new ArrayList<>();
        if (contentBean != null) {
            for (int i = 0; i < songIds.size(); i++) {
                String url = BaiduMusicValues.SONG_ULR_HEAD + songIds.get(i) + BaiduMusicValues.SONG_URL_FOOT;
                VolleyInstance.getInstance().startResult(url, new VolleyResult() {
                    @Override
                    public void success(String resultStr) {
                        resultStr = MainActivity.fixJsonData(resultStr);
                        Gson gson = new Gson();
                        musicBean = gson.fromJson(resultStr, MusicBean.class);
                        if (musicBean.getBitrate() != null) {
                            int duration = musicBean.getBitrate().getFile_duration();
                            musicBean.getBitrate().setFile_duration(duration * 1000);
                        }
                        dataList.add(musicBean);
                        songDetailRvAdapter.setDatas(dataList);
                    }

                    @Override
                    public void failure() {

                    }
                });
            }
        }
        songDetailRvAdapter.setDatas(dataList);
        // 设置顶部背景图片以及文字内容
        if (contentBean.getPic_w300() != null && !contentBean.getPic_w300().equals("")) {
            Picasso.with(context).load(contentBean.getPic_w300()).into(bgImg);
        } else if (contentBean.getPic_300() != null && !contentBean.getPic_300().equals("")) {
            Picasso.with(context).load(contentBean.getPic_300()).into(bgImg);
        }
        titleTv.setText(contentBean.getTitle());
        styleTv.setText(contentBean.getTag());
        countTv.setText(contentBean.getListenum());
        favTv.setText(contentBean.getCollectnum());
        songNumTv.setText(context.getResources().getString(R.string.total) + songIds.size() + context.getResources().getString(R.string.song));

    }

    private void initListener() {
        //返回按钮
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        //收藏全部
        favList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //显示PopWindow
        songDetailRvAdapter.setOnRvMoreClickListener(new OnRvItemClick<MusicBean>() {
            @Override
            public void onRvItemClickListener(int position, MusicBean musicBean) {
                showMorePop(musicBean, position);
            }
        });

        //播放网络歌曲
        songDetailRvAdapter.setOnRvItemClickListener(new OnRvItemClick<MusicBean>() {
            @Override
            public void onRvItemClickListener(int position, MusicBean musicBean) {
                MusicService.setDatas(dataList);
                MusicService.musicBinder.playMusicByMode(position);
                onChangeMusicListener.OnChangeMuisc(dataList.get(position), position);
            }
        });
        onChangeMusicListener = (OnChangeMusicListener) context;
    }

    //显示PopWindow
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

        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("Baidu Music");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl(musicBean.getSonginfo().getTitle());
                // text是分享文本，所有平台都需要这个字段
                oks.setText(musicBean.getSonginfo().getTitle());
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("ShareSDK");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://www.baidu.com");

                // 启动分享GUI
                oks.show(context);
            }
        });

        //下载歌曲
        downLoadIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadInstance.getSingleDownload().DownLoad(musicBean.getSonginfo().getSong_id());
            }
        });
        popupWindow.showAtLocation(rv, Gravity.BOTTOM, 0, 0);
    }
}
