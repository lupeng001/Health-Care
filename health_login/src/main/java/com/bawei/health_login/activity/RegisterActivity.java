package com.bawei.health_login.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.health_login.R;
import com.bawei.health_login.persenter.RegisterPersenter;
import com.bawei.health_login.persenter.VerifyPersenter;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.UIUtils;
import com.dingtao.common.util.view.RsaCoders;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class RegisterActivity extends WDActivity {

    EditText registerYanzhengma;
    EditText registerPwd;
    EditText registerPwds;
    Button regsiterCommit;
    private EditText email;
    private Button yanzhengma;
    private int time = 60;
    private Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    time--;
                    yanzhengma.setText("已发送(" + String.valueOf(time) + ")");
                    yanzhengma.setEnabled(false);
                    if (time <= 0) {
                        yanzhengma.setEnabled(true);
                        yanzhengma.setText("重新获取验证码");
                    }
                }
            });
        }
    };
    private RegisterPersenter registerPersenter;
    private String s;
    private VerifyPersenter verifyPersenter;
    private String pwd2;
    private String pwd1;
    private String diyicipwd;
    private String diercipwds;
    private String code;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        registerPersenter = new RegisterPersenter(new getEmail());
        verifyPersenter = new VerifyPersenter(new verify());
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //控件
        registerYanzhengma = findViewById(R.id.register_yanzhengma);
        registerPwd = findViewById(R.id.register_pwd);
        registerPwds = findViewById(R.id.register_pwds);
        regsiterCommit = findViewById(R.id.regsiter_commit);
        yanzhengma = findViewById(R.id.register_yanzhengmas);
        email = findViewById(R.id.register_email);

        yanzhengma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = email.getText().toString();
                if (!s.equals("")) {
                    registerPersenter.reqeust(s);
                } else {
                    Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        regsiterCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = registerYanzhengma.getText().toString();
                diyicipwd = registerPwd.getText().toString();
                diercipwds = registerPwds.getText().toString();

                if (TextUtils.isEmpty(s)) {
                    UIUtils.showToastSafe("邮箱不能为空");
                    return;
                }
                if (TextUtils.isEmpty(s)) {
                    UIUtils.showToastSafe("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(diyicipwd) || diyicipwd.length() < 6) {
                    UIUtils.showToastSafe("请输入密码,或者密码最小为6位");
                    return;
                }
                if (TextUtils.isEmpty(diercipwds) || !diyicipwd.equals(diercipwds)) {
                    UIUtils.showToastSafe("两次输入的密码不一致，请重新输入");
                    return;
                }
                try {
                    pwd1 = RsaCoders.encryptByPublicKey(diyicipwd);
                    pwd2 = RsaCoders.encryptByPublicKey(diercipwds);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                verifyPersenter.reqeust(s, code);
            }
        });

    }

    @Override
    protected void destoryData() {

    }


    class getEmail implements DataCall {

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
            Toast.makeText(RegisterActivity .this,"验证成功",Toast.LENGTH_LONG).

                    show();

            Intent intent = new Intent(RegisterActivity.this, SecondRegisterActivity.class);
            intent.putExtra("pwd",pwd1);
            intent.putExtra("email",s);
            intent.putExtra("code",code);
            startActivity(intent);

            finish();

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
