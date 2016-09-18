package com.yangyuning.baidumusic.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangyuning.baidumusic.R;

/**
 * Created by dllo on 16/9/8.
 * Fragment基类
 * @author yangyuning
 * @time 9-8
 * @version 1.0
 */
public abstract class AbsBaseFragment extends Fragment {

    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();//初始化组件
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();//初始化数据
    }

    /**
     * 设置布局
     */
    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 简化FindViewById
     */
    protected <T extends View> T byView(int resId){
        return (T) getView().findViewById(resId);
    }

    /**
     * 不传值跳转
     */
    protected void goTo(Class<? extends AbsBaseFragment> to){
        startActivity(new Intent(context, to));
    }

    /**
     * 传值跳转
     */
    protected void goTo(Class<? extends AbsBaseFragment> to, Bundle extras){
        Intent intent = new Intent(context, to);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}
