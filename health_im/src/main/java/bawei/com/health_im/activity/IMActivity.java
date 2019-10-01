package bawei.com.health_im.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.common.util.MD5Utils;
import com.dingtao.common.util.view.RsaCoder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.im.R;
import com.wd.doctor.im.R2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bawei.com.health_im.adapter.IMAdapter;
import com.dingtao.common.bean.QuiryRecordBean;
import bawei.com.health_im.persenter.InquiryRecordListPersenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

@Route(path = Constant.ACTIVITY_URL_IM)

public class IMActivity extends WDActivity {

    @BindView(R2.id.inquiry_recycler)
    XRecyclerView inquiryRecycler;
    private InquiryRecordListPersenter inquiryRecordListPersenter;
    private String key;
    private String pwd;
    private String userName;
    private String pwds;
    private Bitmap bitmap;
    private IMAdapter imAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_im;
    }

    @Override
    protected void initView() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ButterKnife.bind(this);
        inquiryRecycler.setLayoutManager(new LinearLayoutManager(this));
        inquiryRecordListPersenter = new InquiryRecordListPersenter(new inquiryCallBack());
        String id = String.valueOf(LOGIN_USER.getId());
        inquiryRecordListPersenter.reqeust(id, LOGIN_USER.getSessionId());
        Init();
    }

    private void Init() {

        JMessageClient.logout();
        userName = LOGIN_USER.getUserName();
        JMessageClient.enterSingleConversation(this.userName);
        String jiGuangPwd = LOGIN_USER.getJiGuangPwd();
        try {
            key = RsaCoder.decryptByPublicKey(jiGuangPwd);
        } catch (Exception e) {
        }
        pwds = MD5Utils.MD5(key);

        JMessageClient.login(userName, pwds, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i==0){
              Toast.makeText(IMActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    List<Conversation> list = JMessageClient.getConversationList();
                    imAdapter = new IMAdapter(IMActivity.this, list);
                    inquiryRecycler.setAdapter(imAdapter);
                    imAdapter.notifyDataSetChanged();
                    inquiryRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
                        @Override
                        public void onRefresh() {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                imAdapter.notifyDataSetChanged();
                                }
                            }, 5000);
                            inquiryRecycler.refreshComplete();

                        }
                        @Override
                        public void onLoadMore() {

                        }
                    });
                }else {
                    Log.i("", "gotResult: ");
                }
            }
        });
    }

    @Override
    protected void destoryData() {

    }




    class inquiryCallBack implements DataCall<List<QuiryRecordBean>> {


        @Override
        public void success(List<QuiryRecordBean> data, Object... args) {


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

}
