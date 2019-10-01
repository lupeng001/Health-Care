package bawei.com.health_im.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.Login;
import com.dingtao.common.core.db.DaoMaster;
import com.dingtao.common.core.db.LoginDao;
import com.green.hand.library.widget.EmojiBoard;
import com.green.hand.library.widget.EmojiEdittext;
import com.wd.doctor.im.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import bawei.com.health_im.util.BitMap;
import bawei.com.health_im.util.MyMediaRecorder;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import cn.mrlong.audiorecord.recorder.AudioRecordButton;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class Activity_img extends AppCompatActivity {
    private ImageView btn_send;
    Conversation conversation;
    private EditText et_send;
    private List<Conversation> conversations;
    private String posts;
    private String send;
    private int position;
    private TextView title;
    private JG_details_Adapter mAdapter;
    private RecyclerView mRecycler;
    private List<Login> list;
    private String targetAppKey;
    private String username;
    private CheckBox yuyin;
    private HashMap<String, String> hashMap;
    private Message sendMessages;
    private int i =1;
    private List<Message> allMessage;
    private AudioRecordButton audioRecordButton;
    private MediaPlayer mediaPlayer;
    private File files;
    private  CheckBox checkBox;
    private  ImageView piceure,emoji;
    private EmojiBoard  emojiBoard;
    private String messages = "";//显示内容
    private Bitmap bitmap;
    UserInfo userInfo;
    private ImageView voice;
    private PopupWindow popupWindow;

    public Activity_img() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        JMessageClient.registerEventReceiver(this);
        title = findViewById(R.id.tv_target_account);
        emoji = findViewById(R.id.emoji);
        et_send = findViewById(R.id.jg_details_edit);
        voice = findViewById(R.id.VoicePhone);
        btn_send = findViewById(R.id.btn_send);
        yuyin = findViewById(R.id.btn_voice_or_texts);
        checkBox = findViewById(R.id.btn_voice_or_texts);
        piceure = findViewById(R.id.picture);
        mRecycler = findViewById(R.id.jg_details_recy);
        emojiBoard = findViewById(R.id.sssssss);
        audioRecordButton = findViewById(R.id.arb);
        targetAppKey = getIntent().getStringExtra("targetAppKey");
        username = getIntent().getStringExtra("username");
        conversation = Conversation.createSingleConversation(username,targetAppKey);
        position = getIntent().getIntExtra("position", 0);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        EventBus.getDefault().register(this);
        //设置消息接收 监听
        // 进入会话状态,不接收通知栏
        GlobalEventListener.setJG(this,true);
       JMessageClient.enterSingleConversation(this.username);

        //设置消息接收 监听
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new JG_details_Adapter(Activity_img.this);
        mRecycler.setAdapter(mAdapter);
        initData();
       inits();
//        Conversation conversation = Conversation.createSingleConversation(userName, "c7f6a1d56cb8da740fd18bfa");
//        conversations = JMessageClient.getConversationList();
//        conversation = conversations.get(1);
//        conversations1 = Conversation.createSin
// gleConversation(userName, "c7f6a1d56cb8da740fd18bfa");
//        JMessageClient.createSingleTextMessage(userName, "c7f6a1d56cb8da740fd18bfa", send);

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = LayoutInflater.from(Activity_img.this).inflate(R.layout.jmrtccline_layout, null);

               final CheckBox audio =  view.findViewById(R.id.audio);
                final CheckBox video =  view.findViewById(R.id.video);
                Button finish = view.findViewById(R.id.finish);
                audio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(Activity_img.this,VoiceActivity.class);
                        boolean checked = audio.isChecked();
                        intent.putExtra("audiocheck",checked);
                        EventBus.getDefault().postSticky(userInfo);
                        startActivity(intent);
                    }
                });

                video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(Activity_img.this,VoiceActivity.class);
                        boolean checked = video.isChecked();
                        intent.putExtra("videocheck",checked);
                        EventBus.getDefault().postSticky(userInfo);
                        startActivity(intent);
                    }
                });
                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupWindow.dismiss();
                    }
                });
                popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view,Gravity.BOTTOM,0,0);



            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    et_send.setVisibility(GONE);
                    audioRecordButton.setVisibility(VISIBLE);
                }else {
                    audioRecordButton.setVisibility(GONE);
                    et_send.setVisibility(VISIBLE);
                }
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = et_send.getText().toString();
                final Message message = conversation.createSendMessage(new TextContent(send));
//                Message message1 = conversation.createSendMessage(new TextContent(send));
                message.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int responseCode, String responseDesc) {
                        if (responseCode == 0) {
                            //消息发送成功
                          et_send.setText("");
                            initData();
                        } else {
                            //消息发送失败
                        }
                    }
                });
//                MessageSendingOptions options = new MessageSendingOptions();
//                options.setRetainOffline(false);
                JMessageClient.sendMessage(message);//使用默认控制参数发送消息

            }
        });
        btn_send.setVisibility(View.GONE);
        et_send.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //输入中
                if (s.length() > 0) {
                    btn_send.setVisibility(View.VISIBLE);
                    piceure.setVisibility(View.GONE);
                } else {
                    piceure.setVisibility(View.VISIBLE);
                    btn_send.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
        //输入后

            }

        });

        piceure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,0);
            }
        });

        et_send.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

//                    String sss = et_send.getText().toString();
//                    final Message messages = conversation.createSendMessage(new TextContent(sss));
////                Message message1 = conversation.createSendMessage(new TextContent(send));
//                    messages.setOnSendCompleteCallback(new BasicCallback() {
//                        @Override
//                        public void gotResult(int responseCode, String responseDesc) {
//                            if (responseCode == 0) {
//                                //消息发送成功
//                                et_send.setText("");
//                                initData();
//                            } else {
//                                //消息发送失败
//                            }
//                        }
//                    });
////                MessageSendingOptions options = new MessageSendingOptions();
////                options.setRetainOffline(false);
//                    JMessageClient.sendMessage(messages);//使用默认控制参数发送消息

                }
                return false;
            }
        });

        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmojiBoard();
            }
        });
        emojiBoard.setItemClickListener(new EmojiBoard.OnEmojiItemClickListener() {//表情框点击事件

            @Override

            public void onClick(String code) {

                if (code.equals("/DEL")) {//点击了删除图标

                    et_send.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));

                } else {//插入表情

                    et_send.getText().insert(et_send.getSelectionStart(), code);

                }

            }

        });




//语音
        audioRecordButton.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
            @Override
            public void onFinished(final float seconds, final String filePath) {
                start(yuyin);
                //final float seconds 时间, final String filePath 文件存储位置
                Log.i("dadasss","dadadad"+filePath);
               final String filePath1 = filePath;
                files = new File(filePath);
                int send = (int) seconds;

//                try {
//                    mediaPlayer = new MediaPlayer();
//                    mediaPlayer.setDataSource(filePath);
//                    mediaPlayer.prepare();
//                    if (position > 0) mediaPlayer.seekTo(position);
//                    mediaPlayer.start();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }

                try {
                    sendMessages = conversation.createSendMessage(new VoiceContent(files,send));
                    sendMessages.setOnSendCompleteCallback(new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            if (i==0){
                                Toast.makeText(Activity_img.this,"发送语音成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    initData();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, R.drawable.button_recordnormal, R.drawable.button_recording);
        //或者 使用默认的样式
//        yuyin.setOnTouchListener(new View.OnTouchListener() {
//            private long startTime;
//
//            private long endTime;
//
//            private String path;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//
//                        startTime = System.currentTimeMillis();
//                        Log.i("dasdadad", startTime + "");
//                        endTime = System.currentTimeMillis();
//
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//
//                        endTime = System.currentTimeMillis();
//
//                        if (endTime - startTime > 2 * 1000) {
//                            path = Environment.getExternalStorageDirectory() + "/" + "jchat_audio.m4a"+i;
//                            startRecord(path);
//
//                            Log.i("dasdadad", path.toString() + "dadadadad");
//                        }
//
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//
//                        if (endTime - startTime > 2 * 1000) {
//
//                            stopRecord();
//
//                            File file = new File(path);
//
////                            JMessageClient.createSingleVoiceMessage(username,targetAppKey, file,)
//                            final  int is = 1;
//                            try {
//                                sendMessages = conversation.createSendMessage(new VoiceContent(file,is));
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                            initData();
//                        }
//
//                        break;
//
//                    default:
//
//                        break;
//
//                }
//
//                return true;
//            }
//        });
    }


    private void showEmojiBoard() {
        emoji.setSelected(emojiBoard.getVisibility() == GONE);//设置图片选中效果
        emojiBoard.showBoard();//是否显示表情框

    }

    private void inits() {
        LoginDao login = DaoMaster.newDevSession(this, LoginDao.TABLENAME).getLoginDao();
        list = login.queryBuilder().where(LoginDao.Properties.Status.eq(1)).list();
//        JMessageClient.updateUserAvatar();

    }

    public void onEvent(MessageEvent event) {
        //获取事件发生的会话对象
        Message message = event.getMessage();
        initData();
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到一条来自%s的在线消息。\n", conversation.getTargetId()));
    }

    boolean one ;
    public void initData() {

            //使列表滚动到底部
            if (conversation.getAllMessage() != null) {
                allMessage = conversation.getAllMessage();
                title.setText(allMessage.get(position).getFromName());
                Log.i("aaa", "initData: "+ allMessage);
                if (conversation.getAllMessage().size() > 0) {
                    mAdapter.setData(conversation.getAllMessage());
                    //设置刷新不闪屏
                    ((SimpleItemAnimator) mRecycler.getItemAnimator()).setSupportsChangeAnimations(false);
                    if (one) {
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mAdapter.notifyItemInserted(conversation.getAllMessage().size() - 1);
                    }
                    mRecycler.scrollToPosition(conversation.getAllMessage().size()-1);
                }
        }
        one=false; // 代表不是第一次initData

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

    public void start(View v) {

    }

    public void stop(View v) {
        mediaPlayer.stop();
    }

    public void pause(View v) {
        position = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
    @Override

    public void onBackPressed() {//监听返回键，如果表情框已显示就隐藏

        if (emojiBoard.getVisibility() == VISIBLE) {

            showEmojiBoard();

        } else {

            super.onBackPressed();

        }

    }
        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片
        Uri data1 = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data1);

        } catch (IOException e) {
            e.printStackTrace();
        }
            BitMap bitMap  = new BitMap();
            File file = BitMap.compressImage(bitmap);
            try {
                Message sendMessage = conversation.createSendMessage(new ImageContent(file));
                sendMessage.setOnSendCompleteCallback(new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i==0){
                            Toast.makeText(Activity_img.this,"发送成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getUserinfo(UserInfo userInfo){
        this.userInfo = userInfo;
        Log.i("dsada","qqqqqqqqqqqqqqqqqqqqq"+userInfo);
    }
}