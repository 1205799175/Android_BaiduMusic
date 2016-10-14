package com.yangyuning.baidumusic.model.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.yangyuning.baidumusic.controller.app.BaiduMusicApp;
import com.yangyuning.baidumusic.model.bean.DownLoadBean;
import com.yangyuning.baidumusic.model.bean.MyFavoriteSongBean;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

import java.util.List;

/**
 * Created by dllo on 16/10/8.
 * LiteOrm单例类
 */
public class LiteOrmInstance {
    private static LiteOrmInstance liteOrmInstance;
    private LiteOrm liteOrm;

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    private LiteOrmInstance() {
        liteOrm = LiteOrm.newSingleInstance(BaiduMusicApp.getContext(), BaiduMusicValues.DB_NAME);
    }

    public static LiteOrmInstance getLiteOrmInstance() {
        if (liteOrmInstance == null) {
            synchronized (LiteOrmInstance.class) {
                if (liteOrmInstance == null) {
                    liteOrmInstance = new LiteOrmInstance();
                }
            }
        }
        return liteOrmInstance;
    }

    /**
     * 插入一条数据
     */
    public void insertOne(MyFavoriteSongBean myFavoriteSongBean) {
        if (liteOrm != null) {
            liteOrm.insert(myFavoriteSongBean);
        }
    }

    /**
     * 插入集合
     */
    public void insertList(List<MyFavoriteSongBean> myFavoriteSongBeanList) {
        if (liteOrm != null) {
            liteOrm.insert(myFavoriteSongBeanList);
        }
    }

    /**
     * 查询全部
     */
    public List<MyFavoriteSongBean> queryAll() {
        return liteOrm.query(MyFavoriteSongBean.class);
    }

    /**
     * 如果有多张表
     */
    public <T> List<T> queryData(Class<T> clz){
        return liteOrm.query(clz);
    }

    /**
     * 按条件查询
     */
    public List<MyFavoriteSongBean> queryBySongId(String songId) {
        QueryBuilder<MyFavoriteSongBean> queryBuilder = new QueryBuilder<>(MyFavoriteSongBean.class);
        queryBuilder.where("songId = ?", new Object[] {songId});
        return liteOrm.query(queryBuilder);
    }

    /**
     * 多张表查询
     * @param clz
     * @param songId
     * @param <T>
     * @return
     */
    public <T> List<T> queryBySongIds(Class<T> clz, String songId){
        QueryBuilder<T> queryBuilder = new QueryBuilder<>(clz);
        queryBuilder.where("songId = ?", new Object[]{songId});
        return liteOrm.query(queryBuilder);
    }

    /**
     * 删除全部
     */
    public void deleteAll(){
        liteOrm.deleteAll(MyFavoriteSongBean.class);
    }

    /**
     * 按条件删除
     */
    public void deleteBySongId(String songId){
        WhereBuilder whereBuilder = new WhereBuilder(MyFavoriteSongBean.class, "songId = ?", new String[] {songId});
        liteOrm.delete(whereBuilder);
    }
    public void deleteBySongIdDown(String songId){
        WhereBuilder whereBuilder = new WhereBuilder(DownLoadBean.class, "songId = ?", new String[] {songId});
        liteOrm.delete(whereBuilder);
    }
}
