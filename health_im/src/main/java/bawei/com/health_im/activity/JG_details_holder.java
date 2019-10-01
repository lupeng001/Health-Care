package bawei.com.health_im.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.speech.tts.Voice;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dingtao.common.bean.Login;
import com.dingtao.common.core.db.DaoMaster;
import com.dingtao.common.core.db.LoginDao;
import com.makeramen.roundedimageview.RoundedImageView;
import com.wd.doctor.im.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import androidx.annotation.RequiresApi;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.PromptContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */

/**

 * Created by Xinghai.Zhao on 18/04/02.

 */

/*

 *作者:赵星海

 *时间:18/11/27 17:52

 *用途:极光聊天页面Holder

 */


/**

 * Created by Xinghai.Zhao on 18/04/02.

 */

/*

 *作者:赵星海

 *时间:18/11/27 17:52

 *用途:极光聊天页面Holder

 */

public class JG_details_holder extends BaseViewHolder implements View.OnClickListener {



    private RoundedImageView MyImg;  //发送的图片

    private TextView MyTv_content, MyTV_Time, My_tc, My_tc1, My_Tv_state;

    private CircleImageView MyHead;

    private Context MyContext;

    private  TextView yuyin;
    private LinearLayout quanbu;
    private JG_details_Adapter.OnItemClickListener mOnItemClickLis = null;
    private ImageView ivAudio;
    private View view;
    private final String userName;
    private MediaPlayer mediaPlayer;
    private VoiceContent voiceContent;


    public JG_details_holder(View itemView, Context con, JG_details_Adapter.OnItemClickListener mOnItemClick) {

        super(itemView);

        MyContext = con;

        mOnItemClickLis = mOnItemClick;

        LoginDao login = DaoMaster.newDevSession(con, LoginDao.TABLENAME).getLoginDao();
        List<Login> userInfos = login.queryBuilder().where(LoginDao.Properties.Status.eq(1)).list();
        userName = userInfos.get(0).getUserName();


    }



    @Override

    public void findView(View view) {

        this.view = view;

        MyImg = this.view.findViewById(R.id.item_jg_details_img);//图片

        MyHead = view.findViewById(R.id.item_jg_details_head);  //头像


    yuyin = view.findViewById(R.id.yuyin);
        MyTv_content = view.findViewById(R.id.item_jg_details_content);//内容

        MyTV_Time = view.findViewById(R.id.item_jg_details_time); // 时间

        My_tc = view.findViewById(R.id.item_jg_details_tc);
        ivAudio = view.findViewById(R.id.ivAudio);
        My_tc1 = view.findViewById(R.id.item_jg_details_tc1);
        quanbu = view.findViewById(R.id.quanbu);
        My_Tv_state = view.findViewById(R.id.item_jg_details_state);



        MyImg.setOnClickListener(this);
        yuyin.setOnClickListener(this);
        MyHead.setOnClickListener(this);

        MyTv_content.setOnClickListener(this);

        MyTV_Time.setOnClickListener(this);

        My_Tv_state.setOnClickListener(this);



    }



    @TargetApi(Build.VERSION_CODES.M)

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override

    public void setHolderData(Object o, int position) {

        if (o != null) {

            Message bean = (Message) o;


            if (bean.getFromUser() != null) {

                if (bean.getFromUser().getUserName().equals(userName)) {

                    //是自己的聊天

                    MyHead = view.findViewById(R.id.item_jg_details_head1);  //头像 右边

                    MyHead.setVisibility(View.VISIBLE);//头像显示隐藏

                    view.findViewById(R.id.item_jg_details_head).setVisibility(View.GONE);

                    //内容背景

                    MyTv_content.setBackground(MyContext.getDrawable(R.drawable.jmui_msg_send_bg));

                    MyTv_content.setTextColor(MyContext.getResources().getColor(R.color.white));

                    My_tc.setVisibility(View.VISIBLE);//权重挤压

                    My_tc1.setVisibility(View.GONE);

                    //对方是否未读

//                    My_Tv_state.setVisibility(View.VISIBLE);
//
//                    if (bean.haveRead()) {
//
//                        My_Tv_state.setText("已读");
//
//                        My_Tv_state.setTextColor(MyContext.getResources().getColor(R.color.blue));
//
//                    }
//
//                    {
//
//                        My_Tv_state.setText("未读");
//
//                        My_Tv_state.setTextColor(MyContext.getResources().getColor(R.color.blue));
//
//
//
//                    }



                } else {

                    My_Tv_state.setVisibility(View.GONE);//对方是否未读

                    MyHead = view.findViewById(R.id.item_jg_details_head);  //头像

                    MyHead.setVisibility(View.VISIBLE);//头像显示隐藏

                    view.findViewById(R.id.item_jg_details_head1).setVisibility(View.GONE);

                    //内容背景

                    MyTv_content.setBackground(MyContext.getDrawable(R.drawable.jmui_msg_receive_bg));

                    MyTv_content.setTextColor(MyContext.getResources().getColor(R.color.blue));

                    My_tc.setVisibility(View.GONE);

                    My_tc1.setVisibility(View.VISIBLE);



                }

                MyHead.setOnClickListener(this); //刷新头像点击事件

                //头像

                bean.getFromUser().getAvatarBitmap(new GetAvatarBitmapCallback() {

                    @Override

                    public void gotResult(int i, String s, Bitmap bitmap) {

                        if (bitmap != null) {

                            MyHead.setImageBitmap(bitmap);

                        } else {

                            Log.e("极光会话详情-用户头像赋值", "bitmap为空!");

                        }



                    }

                });



                switch (bean.getContentType()) {

                    case text:
                        yuyin.setVisibility(View.GONE);
                        ivAudio.setVisibility(View.GONE);
                        MyTv_content.setVisibility(View.VISIBLE);

                        MyTV_Time.setVisibility(View.GONE);

                        MyImg.setVisibility(View.GONE);

                        //内容

                        TextContent textContent = (TextContent) bean.getContent();
                        String text = textContent.getText();

                        MyTv_content.setText(text);


                        break;

                    case image:
                        yuyin.setVisibility(View.GONE);
                        ivAudio.setVisibility(View.GONE);
                        MyTv_content.setVisibility(View.GONE);

                        MyTV_Time.setVisibility(View.GONE);

                        MyImg.setVisibility(View.VISIBLE);

                        ImageContent imageContent = (ImageContent) bean.getContent();

                        if (imageContent.getLocalThumbnailPath() != null) {

                            Glide.with(MyContext).load(imageContent.getLocalThumbnailPath()).into(MyImg);

                        }

                        break;


                    case location:
                        break;
                    case video:
                        break;
                    case eventNotification:
                        break;
                    case custom:
                        break;
                    case unknown:
                        break;
                    case file:
                        break;
                    case prompt: //提示
                        yuyin.setVisibility(View.GONE);
                        ivAudio.setVisibility(View.GONE);
                        MyTv_content.setVisibility(View.GONE);

                        MyTV_Time.setVisibility(View.VISIBLE);

                        MyImg.setVisibility(View.GONE);

                        //内容

                        PromptContent promptContent = (PromptContent) bean.getContent();

                        String promptText = promptContent.getPromptText();

                        MyTV_Time.setText(promptText);

                        break;
                    case voice:
                        yuyin.setVisibility(View.VISIBLE);
                        ivAudio.setVisibility(View.VISIBLE);
                        MyTv_content.setVisibility(View.GONE);

                        MyTV_Time.setVisibility(View.GONE);

                        MyImg.setVisibility(View.GONE);

                        voiceContent = (VoiceContent) bean.getContent();

                        int duration = voiceContent.getDuration();

                        yuyin.setText(duration+"");
                        quanbu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    mediaPlayer = new MediaPlayer();
                                    FileInputStream fileInputStream = new FileInputStream(voiceContent.getLocalPath());
                                    FileDescriptor fd = fileInputStream.getFD();
                                    mediaPlayer.setDataSource(fd);
                                    mediaPlayer.prepare();
                                    if (0 > 0) mediaPlayer.seekTo(0);
                                    mediaPlayer.start();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
//                                mediaPlayer.release();
                            }
                        });
//
//                        // 如果之前存在播放动画，无论这次点击触发的是暂停还是播放，停止上次播放的动画
//                        if (mVoiceAnimation != null) {
//                            mVoiceAnimation.stop();
//                        }
//                        // 播放中点击了正在播放的Item 则暂停播放
//                        if (mp.isPlaying() && mPosition == position) {
//                            if (msgDirect == MessageDirect.send) {
//                                holder.voice.setImageResource(R.drawable.jmui_voice_send);
//                            } else {
//                                holder.voice.setImageResource(R.drawable.jmui_voice_receive);
//                            }
//                            mVoiceAnimation = (AnimationDrawable) holder.voice.getDrawable();
//                            pauseVoice(msgDirect, holder.voice);
//                            // 开始播放录音
//                        } else if (msgDirect == MessageDirect.send) {
//                            holder.voice.setImageResource(R.drawable.jmui_voice_send);
//                            mVoiceAnimation = (AnimationDrawable) holder.voice.getDrawable();
//
//                            // 继续播放之前暂停的录音
//                            if (mSetData && mPosition == position) {
//                                mVoiceAnimation.start();
//                                mp.start();
//                                // 否则重新播放该录音或者其他录音
//                            } else {
//                                playVoice(position, holder, true);
//                            }
//                            // 语音接收方特殊处理，自动连续播放未读语音
//                        } else {
//                            try {
//                                // 继续播放之前暂停的录音
//                                if (mSetData && mPosition == position) {
//                                    if (mVoiceAnimation != null) {
//                                        mVoiceAnimation.start();
//                                    }
//                                    mp.start();
//                                    // 否则开始播放另一条录音
//                                } else {
//                                    // 选中的录音是否已经播放过，如果未播放，自动连续播放这条语音之后未播放的语音
//                                    if (msg.getContent().getBooleanExtra("isRead") == null
//                                            || !msg.getContent().getBooleanExtra("isRead")) {
//                                        autoPlay = true;
//                                        playVoice(position, holder, false);
//                                        // 否则直接播放选中的语音
//                                    } else {
//                                        holder.voice.setImageResource(R.drawable.jmui_voice_receive);
//                                        mVoiceAnimation = (AnimationDrawable) holder.voice.getDrawable();
//                                        playVoice(position, holder, false);
//                                    }
//                                }
//                            } catch (IllegalArgumentException e) {
//                                e.printStackTrace();
//                            } catch (SecurityException e) {
//                                e.printStackTrace();
//                            } catch (IllegalStateException e) {
//                                e.printStackTrace();
//                            }
//                        }
                        break;


                }}}};




















    @Override

    public void onClick(View v) {

        if (mOnItemClickLis != null) {

            mOnItemClickLis.onItemClick(v, getPosition());

        }

    }



}
