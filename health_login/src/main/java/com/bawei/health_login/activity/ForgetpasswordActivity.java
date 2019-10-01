package com.bawei.health_login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.health_login.R;
import com.bawei.health_login.R2;
import com.bawei.health_login.persenter.RegisterPersenter;
import com.bawei.health_login.persenter.VerifyPersenter;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetpasswordActivity extends WDActivity {


    @BindView(R2.id.forget_email)
    EditText forgetEmail;

    @BindView(R2.id.forget_codes)
    EditText forgetCodes;

    @BindView(R2.id.forget_commit)
    Button forgetCommit;
    private Button yanzhneg;
    private RegisterPersenter registerPersenter;
    private int time = 60;
    private Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    time--;
                    yanzhneg.setText("已发送(" + String.valueOf(time) + ")");
                    yanzhneg.setEnabled(false);
                    if (time <= 0) {
                        yanzhneg.setEnabled(true);
                        yanzhneg.setText("重新获取验证码");
                    }
                }
            });
        }
    };

    private String email;
    private VerifyPersenter verifyPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgetpassword;
    }

    @Override
    protected void initView() {
yanzhneg = findViewById(R.id.yanzhneg);
        registerPersenter=new RegisterPersenter(new yanemail());
        verifyPersenter = new VerifyPersenter(new verify());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    yanzhneg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            email = forgetEmail.getText().toString();
            if (!email.equals("")) {
                registerPersenter.reqeust(email);
            } else {
                Toast.makeText(ForgetpasswordActivity.this, "邮箱不能为空", Toast.LENGTH_LONG).show();
            }
        }
    });
forgetCommit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String code = forgetCodes.getText().toString();
        if (!code.equals("")) {
            verifyPersenter.reqeust(email, code);
        } else {
            Toast.makeText(ForgetpasswordActivity.this, "邮箱不能为空", Toast.LENGTH_LONG).show();
        }

    }
});



    }

    @Override
    protected void destoryData() {

    }

    private class yanemail implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            timer.schedule(task, time, 1000);

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class verify implements DataCall {
        @Override
        public void success(Object data, Object... args) {
            Toast.makeText(ForgetpasswordActivity .this,"验证成功",Toast.LENGTH_LONG).

                    show();
            Intent intent = new Intent(ForgetpasswordActivity.this, SecondForgetpassword.class);
            intent.putExtra("email",email);
            startActivity(intent);
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
