package com.yangyuning.baidumusic.controller.activity;

import android.content.Intent;
import android.os.AsyncTask;

import com.yangyuning.baidumusic.R;

/**
 * Created by dllo on 16/10/6.
 * 欢迎页
 */
public class WelcomeActivity extends AbsBaseActivity {
    private CountTask countTask;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {}

    @Override
    protected void initDatas() {
        countTask = new CountTask();
        countTask.execute(0);
    }

    //倒计时
    public class CountTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            int all = params[0];
            int now = 3;
            while (now > all) {
                publishProgress(now);
                now--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
