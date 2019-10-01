package bawei.com.health_main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingtao.common.core.WDActivity;

import androidx.recyclerview.widget.RecyclerView;
import bawei.com.health_main.R;
import bawei.com.health_main.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMoneyActivity extends WDActivity {
    @BindView(R2.id.money_image)
    ImageView moneyImage;
    @BindView(R2.id.money_bind)
    TextView moneyBind;
    @BindView(R2.id.money_rmb)
    TextView moneyRmb;
    @BindView(R2.id.Text)
    TextView Text;
    @BindView(R2.id.package_recycler)
    RecyclerView recyclerView;
    @BindView(R2.id.hide_layout)
    LinearLayout hideLayout;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_my_money;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        moneyBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(BIndBankActivity.class);
            }
        });
    }

    @Override
    protected void destoryData() {

    }

}
