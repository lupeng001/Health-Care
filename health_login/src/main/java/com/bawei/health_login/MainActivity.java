package com.bawei.health_login;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import com.bawei.health_login.activity.LoginActivity;
import com.dingtao.common.core.WDActivity;

import butterknife.ButterKnife;

public class MainActivity extends WDActivity {
    private Handler mhandler = new Handler();
    private SharedPreferences sp;
    private int time = 5;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time == 0) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();



            } else {
                mhandler.postDelayed(runnable, 500);
            }
        }
    };



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        mhandler.postDelayed(runnable, 500);
    }

    @Override
    protected void destoryData() {

    }




}
