package bawei.com.health_main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.dingtao.common.bean.DoctorBankCardById;
import com.dingtao.common.bean.DoctorIdCardInfo;
import com.dingtao.common.bean.Result;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.view.RsaCoder;

import bawei.com.health_main.R;
import bawei.com.health_main.R2;
import bawei.com.health_main.card.BandShowActivity;
import bawei.com.health_main.card.BankScanActivity;
import bawei.com.health_main.card.CameraScanActivity;
import bawei.com.health_main.persenter.DoctorBankCardByIdPersenter;
import bawei.com.health_main.persenter.DoctorIdCardInfoPersenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BIndBankActivity extends WDActivity {

    @BindView(R2.id.bind_id_card)
    TextView bindIdCard;
    @BindView(R2.id.idCard_layout_hide)
    LinearLayout idCardLayoutHide;
    @BindView(R2.id.bind_bank_card)
    TextView bindBankCard;
    @BindView(R2.id.bank_layout_hide)
    LinearLayout bankLayoutHide;
    @BindView(R2.id.cord_name)
    TextView cordName;
    @BindView(R2.id.cord_sex)
    TextView cordSex;
    @BindView(R2.id.cord_zu)
    TextView cordZu;
    @BindView(R2.id.cord_id)

    TextView cordId;
    @BindView(R2.id.idCard_layout_show)
    LinearLayout idCardLayoutShow;
    @BindView(R2.id.cord_address)
    TextView cordAddress;
    @BindView(R2.id.cord_type)
    TextView cordType;
    @BindView(R2.id.cord_number)
    TextView cordNumber;
    @BindView(R2.id.bank_layout_show)
    LinearLayout bankLayoutShow;
    private boolean hasGotToken;
    private DoctorIdCardInfoPersenter doctorIdCardInfoPersenter;
    private String names;
    private DoctorBankCardByIdPersenter doctorBankCardByIdPersenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_bank;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getOcrSing();
        doctorIdCardInfoPersenter = new DoctorIdCardInfoPersenter(new DoctorInfoCallBack());
        doctorBankCardByIdPersenter = new DoctorBankCardByIdPersenter(new DoctorIdbankCallBalk());

        Long id = LOGIN_USER.getId();
        String sessionId = LOGIN_USER.getSessionId();
        doctorBankCardByIdPersenter.reqeust(id,sessionId);
        doctorIdCardInfoPersenter.reqeust(id,sessionId);
        bindIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(BindiDentityCard.class);
            }
        });
        bindBankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent(BankScanActivity.class);
            }
        });


    }

    private void getOcrSing() {
            OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
                @Override
                public void onResult(AccessToken result) {
                    String token = result.getAccessToken();
                }

                @Override
                public void onError(OCRError error) {
                    error.printStackTrace();
                }
            }, getApplicationContext(), "8FKFdqZA67SaYnbOYZ7nLdAq", "3TmFH1uUzOSNiGYi1NBtpBl9xBh5Wsas");
    }

    @Override
    protected void destoryData() {

    }
    /**
     * 用明文ak，sk初始化
     */
class DoctorInfoCallBack implements DataCall<DoctorIdCardInfo>{
        private String sexs;
        private String numbers;
        private String nations;
        @Override
        public void success(DoctorIdCardInfo data, Object... args) {
            if (data==null){
                idCardLayoutHide.setVisibility(View.INVISIBLE);
            }else {
                idCardLayoutHide.setVisibility(View.GONE);
                idCardLayoutShow.setVisibility(View.VISIBLE);
                try {
                    names = RsaCoder.decryptByPublicKey(data.getName());
                    sexs = RsaCoder.decryptByPublicKey(data.getSex());
                    numbers = RsaCoder.decryptByPublicKey(data.getIdNumber());
                    nations = RsaCoder.decryptByPublicKey(data.getNation());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cordName.setText(names);
                cordSex.setText(sexs);
                cordZu.setText(nations);
                cordId.setText(numbers);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    class DoctorIdbankCallBalk implements DataCall<DoctorBankCardById> {


        @Override
        public void success(DoctorBankCardById data, Object... args) {
            if (data==null){
                bankLayoutHide.setVisibility(View.GONE);

            }else {

                bankLayoutShow.setVisibility(View.VISIBLE);
                bankLayoutHide.setVisibility(View.GONE);
                cordAddress.setText(data.getBankName());

                cordNumber.setText(data.getBankCardNumber());
                if (data.getBankCardType()=="1"){
                    cordType.setText("借记卡");
                }else {
                    cordType.setText("信用卡");
                }

            }
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
