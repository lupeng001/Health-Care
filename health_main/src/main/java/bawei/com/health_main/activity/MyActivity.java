package bawei.com.health_main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dingtao.common.core.WDActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import bawei.com.health_main.R;
import bawei.com.health_main.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends WDActivity {


    @BindView(R2.id.people_image)
    SimpleDraweeView peopleImage;
    @BindView(R2.id.my_back)
    ImageView myBack;
    @BindView(R2.id.system_message)
    ImageView systemMessage;

    @BindView(R2.id.my_material)
    ImageView myMaterial;
    @BindView(R2.id.my_history)
    LinearLayout myHistory;
    @BindView(R2.id.my_money)
    LinearLayout myMoney;
    @BindView(R2.id.my_message_sure)
    LinearLayout myMessageSure;
    @BindView(R2.id.my_message)
    LinearLayout myMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        myMessageSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(JianyiActivity.class);
            }
        });
        myMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(MyMoneyActivity.class);
            }
        });
    }

    @Override
    protected void destoryData() {

    }

}
