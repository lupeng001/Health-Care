package bawei.com.health_main.activity;

import androidx.appcompat.app.AppCompatActivity;
import bawei.com.health_main.R;
import bawei.com.health_main.persenter.InquiryRecordListPersenter;

import android.os.Bundle;

import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;

public class InquiryActivity extends WDActivity {


    private InquiryRecordListPersenter inquiryRecordListPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inquiry;
    }

    @Override
    protected void initView() {

        inquiryRecordListPersenter = new InquiryRecordListPersenter(new inquiryCallBack());
        String id = String.valueOf(LOGIN_USER.getId());
        inquiryRecordListPersenter.reqeust(id,LOGIN_USER.getSessionId());
    }

    @Override
    protected void destoryData() {

    }
    class inquiryCallBack implements DataCall<Result>{

        @Override
        public void success(Result data, Object... args) {

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
