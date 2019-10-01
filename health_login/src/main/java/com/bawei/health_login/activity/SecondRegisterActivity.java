package com.bawei.health_login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.health_login.R;
import com.bawei.health_login.R2;
import com.bawei.health_login.adapter.ZhichengAdapter;
import com.bawei.health_login.persenter.ZhiChengPersenter;
import com.dingtao.common.bean.Zhicheng;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondRegisterActivity extends WDActivity {


    @BindView(R2.id.region_name)
    EditText regionName;
    @BindView(R2.id.region_hospital)
    EditText regionHospital;
//    @BindView(R2.id.region_health)
//    TextView regionHealth;
    @BindView(R2.id.health)
    Spinner health;
//    @BindView(R2.id.region_doctor)
//    TextView regionDoctor;
    @BindView(R2.id.spinner_doctor)
    Spinner spinnerDoctor;
    @BindView(R2.id.region_next_one)
    Button regionNextOne;
    @BindView(R2.id.region_layout_next)
    LinearLayout regionLayoutNext;
    private ZhiChengPersenter zhiChengPersenter;
    private String zhicheng;
    private String pwd;
    private String email;
    private String code;
    private String name;
    private String regionHospital1;
    private String keshi;
    private Object o;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second_register;
    }

    @Override
    protected void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        zhiChengPersenter = new ZhiChengPersenter(new zhichengCallBack());
        ButterKnife.bind(this);
        final Intent intent = getIntent();
        pwd = intent.getStringExtra("pwd");
        email = intent.getStringExtra("email");
        code = intent.getStringExtra("code");

        zhiChengPersenter.reqeust();

        regionNextOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = regionName.getText().toString();
                regionHospital1 = regionHospital.getText().toString();
                if (name.equals("")){
                    Toast.makeText(SecondRegisterActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (regionHospital1.equals("")){
                    Toast.makeText(SecondRegisterActivity.this,"医院名称不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (health.equals("")){
                    Toast.makeText(SecondRegisterActivity.this,"职称不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spinnerDoctor.equals("")){
                    Toast.makeText(SecondRegisterActivity.this,"医院不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent1 =new Intent(SecondRegisterActivity.this,ThirdlyActivity.class);
                intent1.putExtra("pwds",pwd);
                intent1.putExtra("emails",email);
                intent1.putExtra("codes",code);
                intent1.putExtra("names",name);
                intent.putExtra("yiyuan",regionHospital1);
                intent1.putExtra("regionHospital1s",zhicheng);
                intent1.putExtra("healths",keshi);
                startActivity(intent1);
                finish();
            }
        });
        keshi();















    }
//科室
    private void keshi() {
        final String[] spinnerItems = {"小儿科","精神科","皮肤科"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                SecondRegisterActivity.this,
                android.R.layout.simple_spinner_item,

                spinnerItems);
        keshi = "小儿科";


//        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(
//                this,
//                R.array.cities,
//                android.R.layout.simple_spinner_item);
        //String类型是CharSequence接口的实现
        //构建适配器为ListView绑定数据
        //设置Spinner的下拉列表显示样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter添加到spinner中
        health.setAdapter(adapter);

        //设置Spinner的一些属性

        health.setSelection(0,true);

        //添加Spinner事件监听
        health.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
//方式一                  text.setText("你所在的城市是："+cities[arg2]);
                //arg0是适配器视图对象，这里指下拉列表视图。AdapterView 是内容由适配器来决定的视图类，<?>是适配器里内容的类型。
                //arg1是适配器视图里的被点击的对象(即被选中的那一项)，arg2被选中项的位置，arg3选中项所在行的行ID号
                zhicheng = arg0.getItemAtPosition(arg2).toString();
                //设置显示当前选择的项
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    @Override
    protected void destoryData() {

    }
class zhichengCallBack implements DataCall<List <Zhicheng>>{

    @Override
    public void success(List<Zhicheng> data, Object... args) {
        final List spinnerItems = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            String title = data.get(i).getJobTitle();
            spinnerItems.add(title);
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                SecondRegisterActivity.this,
                android.R.layout.simple_spinner_item,
                spinnerItems);
//        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(
//                this,
//                R.array.cities,
//                android.R.layout.simple_spinner_item);
        //String类型是CharSequence接口的实现
        //构建适配器为ListView绑定数据
        //设置Spinner的下拉列表显示样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter添加到spinner中
        spinnerDoctor.setAdapter(adapter);

        //设置Spinner的一些属性
        keshi = spinnerItems.get(0).toString();

        spinnerDoctor.setSelection(0,true);

        //添加Spinner事件监听
        spinnerDoctor.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
//方式一                  text.setText("你所在的城市是："+cities[arg2]);
                //arg0是适配器视图对象，这里指下拉列表视图。AdapterView 是内容由适配器来决定的视图类，<?>是适配器里内容的类型。
                //arg1是适配器视图里的被点击的对象(即被选中的那一项)，arg2被选中项的位置，arg3选中项所在行的行ID号

                keshi = arg0.getItemAtPosition(arg2).toString();
                //设置显示当前选择的项
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });



}

    @Override
    public void fail(ApiException data, Object... args) {

    }
}
}
