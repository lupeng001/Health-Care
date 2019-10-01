package com.bawei.health_login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.health_login.R;
import com.bawei.health_login.R2;
import com.bawei.health_login.persenter.RuzhuPersenter;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;

public class ThirdlyActivity extends WDActivity {


    @BindView(R2.id.people)
    EditText people;
    @BindView(R2.id.max_people)
    TextView maxPeople;
    @BindView(R2.id.adept)
    EditText adept;
    @BindView(R2.id.max_adept)
    TextView maxAdept;
    @BindView(R2.id.region_button)
    Button regionButton;
    @BindView(R2.id.second_layout)
    RelativeLayout secondLayout;
    private String pwds;
    private String emails;
    private String code;
    private String names;
    private String regionHospital1s;
    private String healths;
    private String yiyuan;
    private RuzhuPersenter ruzhuPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thirdly;
    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ruzhuPersenter = new RuzhuPersenter(new ThirdlyCallBack());
        Intent intent = getIntent();
        pwds = intent.getStringExtra("pwds");
        emails = intent.getStringExtra("emails");
        code = intent.getStringExtra("code");
        names = intent.getStringExtra("names");
        regionHospital1s = intent.getStringExtra("regionHospital1s");
        healths = intent.getStringExtra("healths");
        yiyuan = intent.getStringExtra("yiyuan");

               regionButton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String perples = people.getText().toString();
                       String adepts = adept.getText().toString();
                       if (perples.equals("")){
                           Toast.makeText(ThirdlyActivity.this,"个人简历不能为空",Toast.LENGTH_SHORT).show();
                           return;
                       }
                       if (adepts.equals("")){
                           Toast.makeText(ThirdlyActivity.this,"擅长领域不能为空",Toast.LENGTH_SHORT).show();
                           return;
                       }
                       Map<String, String> map = new HashMap<>();//body  map

                       map.put("email", emails);

                       map.put("code", code);

                       map.put("pwd1", pwds);
                       map.put("pwd2", pwds);
                       map.put("name", names);
                       map.put("inauguralHospital", yiyuan);
                       map.put("departmentName", regionHospital1s);
                       map.put("jobTitle", healths);
                       map.put("personalProfile", perples);
                       map.put("goodField", adepts);
                       Gson gson = new Gson();
                       String toJson = gson.toJson(map);
                       Log.i("sdadada", "dasdad" + toJson);
                       RequestBody requestBody=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),toJson);
                       ruzhuPersenter.reqeust(requestBody);
                   }
               });
    }

    @Override
    protected void destoryData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class ThirdlyCallBack implements DataCall {

        @Override
        public void success(Object data, Object... args) {
            finish();
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(ThirdlyActivity.this,"邮箱已存在",Toast.LENGTH_SHORT).show();
        }
    }
}
