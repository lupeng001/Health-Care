package bawei.com.health_patientscircle.activity;

import androidx.appcompat.app.AppCompatActivity;
import bawei.com.health_patientscircle.persenter.AllStatusPresenter;
import bawei.com.health_patientscircle.persenter.ReadNumPresenter;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.bean.ReadNotBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.UIUtils;


import java.util.List;

@Route(path = Constant.ACTIVITY_URL_MESS)
public class MessageActivity extends WDActivity {

    private ImageButton back;
    private TextView yidu;
    private LinearLayout lin;
    private Button close;
    private Button startm;
    private RelativeLayout systemitem;
    private TextView red_b1;
    private RelativeLayout inItem;
    private TextView redb2;
    private RelativeLayout hitem;
    private TextView red_b3;
    private ReadNumPresenter readNumPresenter;
    private Long id;
    private String sessionId;
    private AllStatusPresenter allStatusPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }
    @Override
    protected void initView() {
        back = findViewById(R.id.back_m);
        yidu = findViewById(R.id.already_d);
        lin = findViewById(R.id.line);
        close = findViewById(R.id.close_message);
        startm = findViewById(R.id.start_m);
        systemitem = findViewById(R.id.system_item);
        red_b1 = findViewById(R.id.red_b1);
        inItem = findViewById(R.id.in_item);
        redb2 = findViewById(R.id.red_b2);
        hitem = findViewById(R.id.h_item);
        red_b3 = findViewById(R.id.red_b3);
        sessionId= LOGIN_USER.getSessionId();
        id = LOGIN_USER.getId();
        //修改消息状态为全部已读
        allStatusPresenter = new AllStatusPresenter(new allCall());

        //未读的消息数量
        readNumPresenter = new ReadNumPresenter(new ReadCall());
        readNumPresenter.reqeust(id + "", sessionId);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lin.setVisibility(View.INVISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        systemitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intentByRouter(Constant.ACTIVITY_URL_SYSTEM);
            }
        });
        inItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intentByRouter(Constant.ACTIVITY_URL_ASKMESSAGE);
            }
        });
        hitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intentByRouter(Constant.ACTIVITY_URL_HMONEY);
            }
        });

        red_b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);

            }
        });
        redb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);

            }
        });
        red_b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);

            }
        });

        yidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allStatusPresenter.reqeust(id + "", sessionId);
            }
        });

    }

    @Override
    protected void destoryData() {

    }

     class ReadCall implements DataCall<List<ReadNotBean>> {
        @Override
        public void success(List<ReadNotBean> data, Object... args) {
            Log.e("rrr1", data.toString() + "");
            if (data.get(0).getNoticeType() == 1) {
                red_b1.setText(data.get(0).getNotReadNum() + "");
                Log.e("rrr2", data.get(0).getNotReadNum() + "");
                Toast.makeText(MessageActivity.this, "rrr", Toast.LENGTH_SHORT).show();
            }
            if (data.get(1).getNoticeType() == 2) {
                redb2.setText(data.get(1).getNotReadNum() + "");
            }
            if (data.get(2).getNoticeType() == 3) {
                red_b3.setText(data.get(2).getNotReadNum() + "");
            }
        }

         @Override
         public void fail(ApiException data, Object... args) {
             UIUtils.showToastSafe(data.getCode() + "" + data.getDisplayMessage());
         }
    }

    private class allCall implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {
            UIUtils.showToastSafe("成功");
        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "=====" + data.getDisplayMessage());
        }
    }
}
