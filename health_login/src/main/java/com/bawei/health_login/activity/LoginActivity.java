package com.bawei.health_login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.health_login.MainActivity;
import com.bawei.health_login.R;
import com.bawei.health_login.R2;
import com.bawei.health_login.persenter.LoginPersenter;
import com.dingtao.common.bean.Login;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.WDApplication;
import com.dingtao.common.core.db.DaoMaster;
import com.dingtao.common.core.db.LoginDao;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.UIUtils;
import com.dingtao.common.util.view.RsaCoder;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends WDActivity {
    private boolean pasVisibile = false;
    @BindView(R2.id.youxiang)
    ImageView youxiang;
    @BindView(R2.id.suo)
    ImageView suo;
    @BindView(R2.id.wangjimima)
    TextView wangjimima;
    @BindView(R2.id.zhuce)
    TextView zhuce;
    private EditText pwd;
    private Button commit;
    private EditText email;
    private LoginPersenter loginPersenter;
    private String s;
    private ImageView eye;
    private LoginDao login;
    private SharedPreferences sp;
    private Login data1;
    private String token;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        sp = getSharedPreferences("First", MODE_PRIVATE);

        boolean b = sp.getBoolean("one", false);
        if (b){
            intentByRouter(Constant.ACTIVITY_URL_SSS);
            finish();
        }

        login = DaoMaster.newDevSession(this, LoginDao.TABLENAME).getLoginDao();
        loginPersenter = new LoginPersenter(new loginCaallBack());
        email = findViewById(R.id.login_email);
        pwd = findViewById(R.id.login_pwd);
        eye = findViewById(R.id.login_eye);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        commit = findViewById(R.id.login_button);


        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pasVisibile) {//密码显示，则隐藏
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pasVisibile = false;
                    eye.setBackgroundResource(R.mipmap.login_icon_show_password);
                } else {//密码隐藏则显示
                    eye.setBackgroundResource(R.mipmap.login_icon_hide_password_n);
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pasVisibile = true;
                }
            }
        });
        wangjimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            intent(ForgetpasswordActivity.class);
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwds = pwd.getText().toString();
                String email = LoginActivity.this.email.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    UIUtils.showToastSafe("请输入邮箱");
                    return;
                }
                if (TextUtils.isEmpty(pwds)|| pwds.length()<6) {
                    UIUtils.showToastSafe("请输入密码,或者密码最小为6位");
                    return;
                }
                try {
                    s = RsaCoder.encryptByPublicKey(pwds);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loginPersenter.reqeust(email,s);
            }
        });


    }

    @Override
    protected void destoryData() {

    }

    @OnClick(R2.id.zhuce)
    public void onViewClicked() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
class loginCaallBack implements DataCall<Login>{

    @Override
    public void success(Login data, Object... args) {


        data1 = data;
        Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
    if (data.getWhetherHaveImagePic()==2){
        intentByRouter(Constant.ACTIVITY_URL_Xingxiang);
    }else {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("one", true);
        edit.commit();
        intnData();
        intentByRouter(Constant.ACTIVITY_URL_SSS);
    }
        data.setStatus(1);//设置登录状态，保存到数据库
        LoginDao loginDao = DaoMaster.newDevSession(getBaseContext(), LoginDao.TABLENAME).getLoginDao();
        loginDao.insertOrReplace(data);//保存用户数据

       finish();
    }

    @Override
    public void fail(ApiException data, Object... args) {
        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
    }
}

    private void intnData() {

    }

}
