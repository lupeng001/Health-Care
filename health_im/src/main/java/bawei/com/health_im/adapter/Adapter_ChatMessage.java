package bawei.com.health_im.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dingtao.common.bean.Sendmessage;
import com.wd.doctor.im.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adapter_ChatMessage extends BaseAdapter {
    List<Sendmessage> data;
    LayoutInflater inflater;
    Context context;
    private String str;

    public Adapter_ChatMessage(Context context, List<Sendmessage> list) {
        this.data = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getDirection() == 1)
            return 0;// 返回的数据位角标
        else
            return 1;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Sendmessage sendmessage = data.get(i);
        String content = sendmessage.getContent();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(sendmessage.getAskTime());
        str = sdf.format(date);

        int isMeSend = sendmessage.getDirection();
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            if (isMeSend == 2) {//对方发送
                view = inflater.inflate(R.layout.item_chat_receive_text, viewGroup, false);
                holder.tv_content = view.findViewById(R.id.tv_content);
                holder.tv_sendtime = view.findViewById(R.id.tv_sendtime);
            } else {
                view = inflater.inflate(R.layout.item_chat_send_text, viewGroup, false);
                holder.tv_content = view.findViewById(R.id.tv_content);
                holder.tv_sendtime = view.findViewById(R.id.tv_sendtime);
                holder.tv_isRead = view.findViewById(R.id.tv_isRead);
            }

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

//        if (sendmessage.getMsgType()==2){
//            Uri  uri  =  Uri.parse(content);
//            MediaPlayer player = new MediaPlayer.create(content,uri);
//            player.start();
//        }
        holder.tv_sendtime.setText(str);
        holder.tv_content.setVisibility(View.VISIBLE);
        holder.tv_content.setText(content);


        //如果是自己发送才显示未读已读




        return view;
    }

    class ViewHolder {
        private TextView tv_content, tv_sendtime, tv_display_name, tv_isRead;
    }



}
