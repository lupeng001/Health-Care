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
import com.bawei.health_login.persenter.ResetUserPwdPersenter;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.view.RsaCoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondForgetpassword extends WDActivity {


    @BindView(R2.id.forget_pwd)
    EditText forgetPwd;
    @BindView(R2.id.forget_eye)
    ImageView forgetEye;
    @BindView(R2.id.forget_pwds)
    EditText forgetPwds;
    @BindView(R2.id.forget_eyes)
    ImageView forgetEyes;
    @BindView(R2.id.forget_commit)
    Button forgetCommit;
    private ResetUserPwdPersenter resetUserPwdPersenter;
    private String email;
    private String s;
    private String pwd;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_second_forgetpassword;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        resetUserPwdPersenter = new ResetUserPwdPersenter(new resetCallBack());
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    forgetCommit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pwd = forgetPwd.getText().toString();
            String pwds = forgetPwds.getText().toString();
            if (pwd.equals("")||pwds.equals("")){
                Toast.makeText(SecondForgetpassword.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                return;
            }
            if (pwd.equals(pwds)){
                try {
                    s = RsaCoder.encryptByPublicKey(pwds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resetUserPwdPersenter.reqeust(email,s,s);
            }
        }
    });
    }

    @Override
    protected void destoryData() {

    }
class resetCallBack implements DataCall{

    @Override
    public void success(Object data, Object... args) {
        Toast.makeText(SecondForgetpassword.this,"充值成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail(ApiException data, Object... args) {

    }
}
}
