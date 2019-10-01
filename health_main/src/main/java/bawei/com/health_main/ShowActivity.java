package bawei.com.health_main;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.DoctorMessage;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.WDApplication;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;

import bawei.com.health_main.activity.MyActivity;
import bawei.com.health_main.persenter.DoctorMessagePersenter;
import bawei.com.health_main.persenter.DoctorPushTokenPersenter;
import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = Constant.ACTIVITY_URL_SSS)
public class ShowActivity extends WDActivity {


    @BindView(R2.id.img_text)
    ImageView imgText;
    @BindView(R2.id.tv_homepage_inquiry)
    TextView tvHomepageInquiry;
    @BindView(R2.id.tv_homepage_question)
    TextView tvHomepageQuestion;
    @BindView(R2.id.image_people)
    ImageView imagePeople;
    @BindView(R2.id.people_name)
    TextView peopleName;
    @BindView(R2.id.people_hospital)
    TextView peopleHospital;
    @BindView(R2.id.people_doctor)
    TextView peopleDoctor;
    @BindView(R2.id.people_health)
    TextView peopleHealth;
    @BindView(R2.id.tv_homepage_manage)
    TextView tvHomepageManage;
    @BindView(R2.id.zixun)
    RelativeLayout zixun;
    @BindView(R2.id.wenzhen)
    RelativeLayout wenzhen;
    @BindView(R2.id.wode)
    RelativeLayout wode;
    private DoctorMessagePersenter doctorMessagePersenter;
    private String token;
    private DoctorPushTokenPersenter doctorPushTokenPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void initView() {

        token = WDApplication.getToken();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Long id = LOGIN_USER.getId();
        String sessionId = LOGIN_USER.getSessionId();

        doctorMessagePersenter = new DoctorMessagePersenter(new doctorCallBack());
        doctorMessagePersenter.reqeust(LOGIN_USER.getId(), sessionId);
        doctorPushTokenPersenter = new DoctorPushTokenPersenter(new doctorpush());
        doctorPushTokenPersenter.reqeust(id, sessionId, token);
        ButterKnife.bind(this);
        peopleName.setText(LOGIN_USER.getDepartmentName());
        peopleHealth.setText(LOGIN_USER.getInauguralHospital());
        peopleDoctor.setText(LOGIN_USER.getJobTitle());
        peopleHospital.setText(LOGIN_USER.getInauguralHospital());
        tvHomepageManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(MyActivity.class);
            }
        });

        tvHomepageInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_IM);
            }
        });
        wenzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentByRouter(Constant.ACTIVITY_URL_ASK);
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

    class doctorCallBack implements DataCall<DoctorMessage> {


        @Override
        public void success(DoctorMessage data, Object... args) {
            imagePeople.setImageURI(Uri.parse(data.getImagePic()));
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }

    }

    class doctorpush implements DataCall<Result> {

        @Override
        public void success(Result data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
