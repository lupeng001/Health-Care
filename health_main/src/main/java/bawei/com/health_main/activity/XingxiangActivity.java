package bawei.com.health_main.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.XiTong;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;

import java.util.List;

import bawei.com.health_main.R;
import bawei.com.health_main.R2;
import bawei.com.health_main.adapter.CinemaFlowAdapter;
import bawei.com.health_main.persenter.XiTongPersenter;
import bawei.com.health_main.persenter.XuanzeXitongPersenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

@Route(path = Constant.ACTIVITY_URL_Xingxiang)
public class XingxiangActivity extends WDActivity {

    private RelativeLayout xingxiang;
    @BindView(R2.id.image_pic)
    ImageView imagePic;
    @BindView(R2.id.text_people)
    TextView textPeople;
    @BindView(R2.id.textView)
    TextView textView;
    @BindView(R2.id.people_button)
    Button peopleButton;
    private XiTongPersenter xiTongPersenter;
    private RecyclerCoverFlow recyclerCoverFlow;
    private XuanzeXitongPersenter xuanzeXitongPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xingxiang;
    }

    @Override
    protected void initView() {
        xuanzeXitongPersenter = new XuanzeXitongPersenter(new xuanzexitongCallBack());
        recyclerCoverFlow = findViewById(R.id.rcf_cinema_flow);
        recyclerCoverFlow.setVisibility(View.GONE);
        xingxiang = findViewById(R.id.xingxiang);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        xiTongPersenter = new XiTongPersenter(new xitongCallBack());
        imagePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(XingxiangActivity.this).inflate(R.layout.pop_picture, null, false);
                Button xitong = inflate.findViewById(R.id.system_picture);
                xitong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        xiTongPersenter.reqeust(LOGIN_USER.getId(),LOGIN_USER.getSessionId());

                    }
                });

                PopupWindow pop =new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
               pop.setTouchable(true);
               pop.setOutsideTouchable(true);
               pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
               pop.showAtLocation(inflate,Gravity.BOTTOM,0,0);
            }
        });
    }

    @Override
    protected void destoryData() {

    }
class xitongCallBack implements DataCall<List<XiTong>>{

    @Override
    public void success(final List<XiTong> data, Object... args) {
        xingxiang.setVisibility(View.GONE);
        recyclerCoverFlow.setVisibility(View.VISIBLE);
        CinemaFlowAdapter cinemaFlowAdapter = new CinemaFlowAdapter(XingxiangActivity.this,data);
        recyclerCoverFlow.setAdapter(cinemaFlowAdapter);

                peopleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        recyclerCoverFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {//滑动监听
                            @Override
                            public void onItemSelected(int position) {
                                xuanzeXitongPersenter.reqeust(LOGIN_USER.getId(),LOGIN_USER.getSessionId(),data.get(position).getImagePic());

                    }
                });
            }
        });

    }

    @Override
    public void fail(ApiException data, Object... args) {

    }
}
class xuanzexitongCallBack implements DataCall{
    @Override
    public void success(Object data, Object... args) {
        intentByRouter(Constant.ACTIVITY_URL_SSS);
        finish();
    }

    @Override
    public void fail(ApiException data, Object... args) {

    }
}
}
