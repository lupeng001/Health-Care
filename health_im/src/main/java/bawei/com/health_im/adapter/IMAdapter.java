package bawei.com.health_im.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.im.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bawei.com.health_im.activity.Activity_img;
import bawei.com.health_im.activity.QuiryActivity;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

import com.dingtao.common.bean.QuiryRecordBean;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class IMAdapter extends RecyclerView.Adapter<IMAdapter.ViewHolder> {
    Context context;
    List<Conversation> list;

    public IMAdapter(Context context,  List<Conversation> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.messagelist_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.message.setText(list.get(position).getLatestText());
        holder.name.setText(list.get(position).getTitle());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(list.get(position).getLastMsgDate());
        List<Message> message = list.get(position).getAllMessage();
//        message.get(0).getFromUser().getAvatarBitmap(new GetAvatarBitmapCallback() {
//            @Override
//            public void gotResult(int i, String s, Bitmap bitmap) {
//                holder.simpleDraweeView.setImageBitmap(bitmap);
//            }
//        });
        String times = simpleDateFormat.format(date);
        holder.time.setText(times);

//        holder.simpleDraweeView.setImageURI(lists.get(position).getUserHeadPic());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int recordId = lists.get(position).getRecordId();
//                int userId1 = lists.get(position).getUserId();
                JMessageClient.getUserInfo(list.get(position).getTargetId(), list.get(position).getTargetAppKey(), new GetUserInfoCallback() {
                    @Override
                    public void gotResult(int i, String s, UserInfo userInfo) {
                        Log.i("xxxxxxxxxxxxxx","dada"+userInfo);
                        EventBus.getDefault().postSticky(userInfo);
                    }
                });
                String targetAppKey = list.get(position).getTargetAppKey();
                String id = list.get(position).getTargetId();
                Intent intent = new Intent(context,Activity_img.class);

//                String userId = String.valueOf(userId1);
//                String recordIds = String.valueOf(recordId);
//                intent.putExtra("userId",userId);
//                intent.putExtra("recordIds",recordIds);
                intent.putExtra("targetAppKey",targetAppKey);
                intent.putExtra("username",id);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView simpleDraweeView;
        TextView name,message,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.messagelist_simp);
            name = itemView.findViewById(R.id.messagelist_name);
            message = itemView.findViewById(R.id.messagelist_message);
            time = itemView.findViewById(R.id.messagelist_time);
        }
    }
}
