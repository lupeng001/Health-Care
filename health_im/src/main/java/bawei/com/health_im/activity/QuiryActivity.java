package bawei.com.health_im.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.Sendmessage;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.MyJPushMessageReceiver;
import com.wd.doctor.im.R;
import com.wd.doctor.im.R2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import bawei.com.health_im.adapter.Adapter_ChatMessage;
import bawei.com.health_im.persenter.LishiPersenter;
import bawei.com.health_im.persenter.PushVoiceMsgPersenter;
import bawei.com.health_im.persenter.Sendmessagepersenter;
import bawei.com.health_im.util.BitMap;
import bawei.com.health_im.util.MyMediaRecorder;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class QuiryActivity extends WDActivity {

    @BindView(R2.id.tv_target_account)
    TextView tvTargetAccount;
    @BindView(R2.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R2.id.btn_send)
    Button etSend;
    @BindView(R2.id.rl_send)
    RelativeLayout rlSend;
    @BindView(R2.id.recyc)
    ListView recyc;
    private Sendmessagepersenter sendmessagepersenter;
//    private LoginBean dao;
    private  int inquiryId = 1;
    private  int content = 666;
    private  int type = 1;
    private LishiPersenter lishiPersenter;
    private Adapter_ChatMessage adapter_chatMessage;
    private EditText message;
    private String messages;
    private Long id;
    private String sessionId;
    private String userId;
    private ImageView btn_voice_or_text;
    private Bitmap bitmap;
    private PushVoiceMsgPersenter pushVoiceMsgPersenter;
    private HashMap<String, String> hashMap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_quiry;
    }

    @Override
    protected void initView() {
        pushVoiceMsgPersenter = new PushVoiceMsgPersenter(new qushVioice());
        btn_voice_or_text = findViewById(R.id.btn_voice_or_text);
        id = LOGIN_USER.getId();
        sessionId = LOGIN_USER.getSessionId();
        ButterKnife.bind(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        message= findViewById(R.id.et_content);

        sendmessagepersenter = new Sendmessagepersenter(new SendmessageCallBack());


                lishiPersenter = new LishiPersenter(new LishiCallBack());
        lishiPersenter.reqeust(id,sessionId,inquiryId,1,10);
        etSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages = message.getText().toString();

//        dao = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
//        Long id = dao.id;
//        String sessionId = dao.sessionId;

                sendmessagepersenter.reqeust(id, sessionId,inquiryId,messages,type,userId);
                lishiPersenter.reqeust(id, sessionId,inquiryId,1,30);
                adapter_chatMessage.notifyDataSetChanged();
            }
        });
    initData();

        btn_voice_or_text.setOnTouchListener(new View.OnTouchListener() {
            private long startTime;

            private long endTime;

            private String path;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        startTime = System.currentTimeMillis();
                        Log.i("dasdadad",startTime+"");
                        endTime = System.currentTimeMillis();

                        break;

                    case MotionEvent.ACTION_MOVE:

                        endTime = System.currentTimeMillis();

                        if (endTime - startTime > 2 * 1000) {
                            path = Environment.getExternalStorageDirectory() + "/" + "jchat_audio.m4a";
                            startRecord(path);

                            Log.i("dasdadad",path.toString()+"dadadadad");
                        }

                        break;

                    case MotionEvent.ACTION_UP:

                        if (endTime - startTime > 2 * 1000) {

                            stopRecord();

                            File file = new File(path);

                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            final MultipartBody.Part part =
                                    MultipartBody.Part.createFormData("content", file.getName(), requestBody);

                            hashMap = new HashMap<>();
                            hashMap.put("inquiryId", "" + inquiryId);
                            hashMap.put("type", 2+"" );
                            hashMap.put("userId",""+userId);
                            //  这里发送语音消息
                            pushVoiceMsgPersenter.reqeust(id,sessionId,hashMap,part);
                        }

                        break;

                    default:

                        break;

                }

                return true;
            }
        });
    }

    public static Bitmap convertStringToIcon(String str) {
        // OutputStream out;
        Bitmap bitmap = null;
        try {

            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(str, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }




    private void startRecord(String path) {

        MyMediaRecorder.getInstance().startRecord(path);

    }

    /**

     * 结束录音

     */

    private void stopRecord() {

        MyMediaRecorder.getInstance().stopRecord();

    }















    private void initData() {
        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (etSend.getText().toString().length() > 0) {
                    etSend.setVisibility(View.VISIBLE);
                } else {
                    etSend.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void destoryData() {

    }


class SendmessageCallBack implements DataCall<Result> {

    @Override
    public void success(Result data, Object... args) {
        adapter_chatMessage.notifyDataSetChanged();
    }

    @Override
    public void fail(ApiException data, Object... args) {

    }
}

//历史纪律
    class LishiCallBack implements DataCall<List<Sendmessage>> {
    @Override
    public void success(List<Sendmessage> data, Object... args) {
        ListSort(data);
        message.setText("");
        adapter_chatMessage = new Adapter_ChatMessage(QuiryActivity.this, data);
        recyc.setAdapter(adapter_chatMessage);
        recyc.setSelection(data.size());
        adapter_chatMessage.notifyDataSetChanged();
    }
//排序
    private void ListSort(List<Sendmessage> data) {
        Collections.sort(data, new Comparator<Sendmessage>() {
            @Override
            //定义一个比较器
            public int compare(Sendmessage o1, Sendmessage o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Long askTime = o1.getAskTime();
                    Long askTime1 = o2.getAskTime();
                    Date date = new Date(askTime);
                    Date date1 = new Date(askTime1);
                    String format1 = format.format(date);
                    String format2 = format.format(date1);
                    Date dt1 = format.parse(format1);
                    Date dt2 = format.parse(format2);
                    if (dt1.getTime() > dt2.getTime()) {
                        return 1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

    }


    @Override
    public void fail(ApiException data, Object... args) {

    }
}
class qushVioice implements DataCall<Result>{

    @Override
    public void success(Result data, Object... args) {

    }

    @Override
    public void fail(ApiException data, Object... args) {

    }
}
}
