package com.yangyuning.baidumusic.utils.interfaces;

import com.yangyuning.baidumusic.model.bean.MusicBean;

/**
 * Created by dllo on 16/10/3.
 * 切换歌曲监听
 */
public interface OnChangeMusicListener {
    void OnChangeMuisc(MusicBean musicBean, int position);
}
