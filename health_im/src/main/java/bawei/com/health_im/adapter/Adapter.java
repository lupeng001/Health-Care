package bawei.com.health_im.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingtao.common.bean.Sendmessage;
import com.wd.doctor.im.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Sendmessage> data;
    LayoutInflater inflater;
    Context context;
    private String str;
    private View inflate;
    private  int a= 0;
    private  int b= 1;
    private int isMeSend;

    public Adapter(List<Sendmessage> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate;
        if (isMeSend==2){
            inflate = View.inflate(parent.getContext(), R.layout.item_chat_receive_text, null);
                return new OneViewHolder(inflate);
        }else {
            inflate = View.inflate(parent.getContext(),R.layout.item_chat_send_text, null);
            return new TwoViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Sendmessage sendmessage = data.get(position);
        String content = sendmessage.getContent();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(sendmessage.getAskTime());
        str = sdf.format(date);
        isMeSend = sendmessage.getDirection();

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OneViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content, tv_sendtime, tv_display_name, tv_isRead;
        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_sendtime = itemView.findViewById(R.id.tv_sendtime);
        }
    }
    public class TwoViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content, tv_sendtime, tv_display_name, tv_isRead;
        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_sendtime = itemView.findViewById(R.id.tv_sendtime);
            tv_isRead = itemView.findViewById(R.id.tv_isRead);
        }
    }
}
