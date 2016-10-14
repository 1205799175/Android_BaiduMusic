package com.yangyuning.baidumusic.model.net;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.yangyuning.baidumusic.controller.activity.MainActivity;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.model.bean.DownLoadBean;
import com.yangyuning.baidumusic.model.bean.MusicBean;
import com.yangyuning.baidumusic.model.db.LiteOrmInstance;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

/**
 * Created by dllo on 16/10/9.
 * 下载单例类
 */
public class DownLoadInstance {
    private static DownLoadInstance downLoadInstance;

    private DownloadManager downloadManager;

    private DownLoadInstance() {
        downloadManager = (DownloadManager) BaiduMusicApp.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public static DownLoadInstance getSingleDownload() {
        if (downLoadInstance == null) {
            synchronized (DownLoadInstance.class) {
                if (downLoadInstance == null) {
                    downLoadInstance = new DownLoadInstance();
                }
            }
        }
        return downLoadInstance;
    }

    public void DownLoad(String songId) {
        String url = BaiduMusicValues.SONG_ULR_HEAD + songId + BaiduMusicValues.SONG_URL_FOOT;
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                resultStr = MainActivity.fixJsonData(resultStr);
                Gson gson = new Gson();
                MusicBean musicBean = gson.fromJson(resultStr, MusicBean.class);
                String downUrl = musicBean.getBitrate().getFile_link();
                String songId = musicBean.getSonginfo().getSong_id();
                String title = musicBean.getSonginfo().getTitle();
                String author = musicBean.getSonginfo().getAuthor();
                if (LiteOrmInstance.getLiteOrmInstance().queryBySongIds(DownLoadBean.class, songId).size() != 0){
                    Toast.makeText(BaiduMusicApp.getContext(), BaiduMusicValues.DOWNLOAD, Toast.LENGTH_SHORT).show();
                } else {
                    // 开始下载
                    Uri resource = Uri.parse(downUrl);
                    //调用downloadManager的enqueue接口进行下载
                    DownloadManager.Request request = new DownloadManager.Request(resource);
                    //setAllowedNetworkTypes方法可以用来限定在WiFi还是手机网络下进行下载
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    //setAllowedOverRoaming方法可以用来阻止手机在漫游状态下下载
                    request.setAllowedOverRoaming(false);
                    // 设置文件类型
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    String mimiString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downUrl));
                    request.setMimeType(mimiString);
                    //在通知栏中显示
                    request.setShowRunningNotification(true);
                    request.setVisibleInDownloadsUi(true);
                    request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    //sdcard的目录下的Music文件夹
                    request.setDestinationInExternalPublicDir("/song/mp3", title + ".mp3");
                    //调用downloadManager的enqueue接口进行下载，返回唯一的id
                    long id = downloadManager.enqueue(request);
                    LiteOrmInstance.getLiteOrmInstance().getLiteOrm().insert(new DownLoadBean(title, author, songId));
                }
            }

            @Override
            public void failure() {

            }
        });
    }
}
