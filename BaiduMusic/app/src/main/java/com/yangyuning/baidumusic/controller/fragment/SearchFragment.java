package com.yangyuning.baidumusic.controller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.yangyuning.baidumusic.R;
import com.yangyuning.baidumusic.controller.adapter.SearchLvAdapter;
import com.yangyuning.baidumusic.model.bean.SearchBean;
import com.yangyuning.baidumusic.model.net.VolleyInstance;
import com.yangyuning.baidumusic.model.net.VolleyResult;
import com.yangyuning.baidumusic.utils.BaiduMusicValues;

/**
 * Created by dllo on 16/10/9.
 * 搜索Fragment
 */
public class SearchFragment extends AbsBaseFragment {
    private ImageView backImg, searchImg;
    private EditText editText;
    private ListView listView;
    private String url;
    private SearchBean.ResultBean datas;
    private SearchLvAdapter searchAdapter;

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        backImg = byView(R.id.search_back_btn);
        searchImg = byView(R.id.search_btn);
        editText = byView(R.id.search_et);
        listView = byView(R.id.search_listview);
    }

    @Override
    protected void initDatas() {
        searchAdapter = new SearchLvAdapter(context);
        initListener();
    }

    private void initListener() {
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() != null) {
                    search();
                }
            }
        });
    }

    private void search() {
        String query = editText.getText().toString();
        url = BaiduMusicValues.SEARCH_HEAD + query + BaiduMusicValues.SEARCH_FOOT;
        VolleyInstance.getInstance().startResult(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                SearchBean bean = gson.fromJson(resultStr, SearchBean.class);
                datas = bean.getResult();
                searchAdapter.setDatas(datas);
                listView.setAdapter(searchAdapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
