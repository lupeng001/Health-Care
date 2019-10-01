package bawei.com.health_main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingtao.common.bean.Jianyi;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import bawei.com.health_main.R;
import bawei.com.health_main.R2;
import bawei.com.health_main.persenter.JianyiPersenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JianyiActivity extends WDActivity {


    @BindView(R2.id.jianyi_back)
    ImageView jianyiBack;
    @BindView(R2.id.jianyi_message)
    ImageView jianyiMessage;
    @BindView(R2.id.jianyi_recycler)
    RecyclerView jianyiRecycler;
    @BindView(R2.id.jianyi_image)
    ImageView jianyiImage;
    private JianyiPersenter jianyiPersenter;
    private TextView jianyi_text;
    private int page = 1;
    private int count = 10;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_jianyi;
    }

    @Override
    protected void initView() {
        jianyi_text = findViewById(R.id.jianyi_text);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        jianyiPersenter = new JianyiPersenter(new JianyiCallback());
        jianyiPersenter.reqeust(LOGIN_USER.getId(),LOGIN_USER.getSessionId(),page,count);
    }

    @Override
    protected void destoryData() {

    }



    class JianyiCallback implements DataCall<List <Jianyi>> {


        @Override
        public void success(List<Jianyi> data, Object... args) {
            if (data == null) {
                jianyiRecycler.setVisibility(View.INVISIBLE);

            }else {
                jianyiImage.setVisibility(View.VISIBLE);
                jianyi_text.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
