package bawei.com.health_patientscircle.activity;

import androidx.appcompat.app.AppCompatActivity;
import bawei.com.health_patientscircle.persenter.CircleInfoPresenter;
import bawei.com.health_patientscircle.persenter.PinglunPersenter;
import bawei.com.health_patientscircle.persenter.PublishPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.utils.TextUtils;
import com.bumptech.glide.Glide;
import com.dingtao.common.bean.CircleInfoBean;
import com.dingtao.common.bean.Login;
import com.dingtao.common.bean.MyAdoptedBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.db.DaoMaster;
import com.dingtao.common.core.db.LoginDao;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.DateUtils;
import com.dingtao.common.util.UIUtils;
import com.facebook.drawee.view.SimpleDraweeView;


import java.text.ParseException;
import java.util.List;

@Route(path = Constant.ACTIVITY_URL_CIRINFO)
public class CircleInfoActivity extends AppCompatActivity {

    private Intent intent;
    private ImageButton back;
    private CircleInfoPresenter circleInfoPresenter;
    private TextView tiYop, namej, bingz, eshij, xiangqj, zldizhi, timej,timej2, jingl;
    private ImageView tu;
    private TextView qianj;
    private Button jied;
    private ImageButton imgKknow;
    private RelativeLayout fuceng;
    private LinearLayout gone;
    private LinearLayout input_y;
    private EditText input_jd;
    private ImageButton smaile, send;
    private LinearLayout over_ok;
    private TextView overContent;
    private PublishPresenter publishPresenter;
    private Long id;
    private String sessionId;
    private String inputjd;
    private TextView quanzi_pinglun;
    boolean f  = false;
    private int sickCircleIds;
    private PinglunPersenter pinglunPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_info);
        initView();
    }




    private void initView() {
        quanzi_pinglun = findViewById(R.id.quanzi_pinglun);
        back = findViewById(R.id.back_j);
        tiYop = findViewById(R.id.bti_top);
        namej = findViewById(R.id.name_j);
        bingz = findViewById(R.id.bingz_j);
        eshij = findViewById(R.id.keshi_j);
        xiangqj = findViewById(R.id.xiangq_j);
        zldizhi = findViewById(R.id.zladdres_j);
        timej = findViewById(R.id.timee_j);
        jingl = findViewById(R.id.jingl_j);
        tu = findViewById(R.id.tup_j);
        qianj = findViewById(R.id.qian_j);
        jied = findViewById(R.id.jied_j);
        imgKknow = findViewById(R.id.imageView3);
        fuceng = findViewById(R.id.fuceng);
        timej2 = findViewById(R.id.timee_j2);
        gone = findViewById(R.id.gone_j);
        input_y = findViewById(R.id.input_ye);
        input_jd = findViewById(R.id.input_jd);
        smaile = findViewById(R.id.smaile_j);
        send = findViewById(R.id.send_j);
        over_ok = findViewById(R.id.over_ok);
        overContent = findViewById(R.id.over_content);

        LoginDao loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginDao.TABLENAME).getLoginDao();
        List<Login> logins = loginBeanDao.loadAll();
        id = logins.get(0).getId();
        sessionId = logins.get(0).getSessionId();
//        pinglunPersenter = new PinglunPersenter(new pinglunCallBack());

        //病友圈详情
        circleInfoPresenter = new CircleInfoPresenter(new circleCall());
        intent = getIntent();
        sickCircleIds = intent.getIntExtra("sickCircleIds", 0);
        circleInfoPresenter.reqeust(sickCircleIds);

        //发表评论
        publishPresenter = new PublishPresenter(new publishCall());


        jied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gone.setVisibility(View.GONE);
                input_y.setVisibility(View.VISIBLE);
            }
        });


            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(input_jd.getText().toString().trim())) {
                        inputjd = input_jd.getText().toString().trim();
                            publishPresenter.reqeust(id , sessionId, sickCircleIds, inputjd);
                            overContent.setText(inputjd);
                            input_y.setVisibility(View.GONE);
                            over_ok.setVisibility(View.VISIBLE);
                    }
                }
            });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgKknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fuceng.setVisibility(View.GONE);
            }
        });

    }



    private class circleCall implements DataCall<CircleInfoBean> {
        @Override
        public void success(CircleInfoBean data, Object... args) {
            UIUtils.showToastSafe("成功ll");

               tiYop.setText(data.getTitle());
            namej.setText(data.getAuthorName());
            bingz.setText(data.getDisease());
            eshij.setText(data.getDepartmentName());
            xiangqj.setText(data.getDetail());
            zldizhi.setText(data.getTreatmentHospital());
            try {
                timej.setText(DateUtils.dateTransformer(data.getTreatmentStartTime(),DateUtils.MINUTE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                timej2.setText(DateUtils.dateTransformer(data.getTreatmentEndTime(),DateUtils.MINUTE_PATTERN));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            jingl.setText(data.getTreatmentProcess());
            Glide.with(getBaseContext()).load(data.getPicture()).into(tu);
            qianj.setText(data.getAmount()+"H币"+"");
            if (data.getWhetherContent()==1){
                qianj.setVisibility(View.VISIBLE);
                jied.setVisibility(View.VISIBLE);

            }else {
                qianj.setVisibility(View.GONE);
            jied.setVisibility(View.GONE);
            quanzi_pinglun.setVisibility(View.VISIBLE);
            }
//            pinglunPersenter.reqeust(id,sessionId,1,1);


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    private class publishCall implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {

            UIUtils.showToastSafe(   "发表成功");
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
//    class pinglunCallBack implements DataCall<MyAdoptedBean>{
//
//        @Override
//        public void success(MyAdoptedBean data, Object... args) {
//            if (data==null){
//                qianj.setVisibility(View.VISIBLE);
//                jied.setVisibility(View.VISIBLE);
//            }
//
//        }
//
//        @Override
//        public void fail(ApiException data, Object... args) {
//
//        }
//    }
}
