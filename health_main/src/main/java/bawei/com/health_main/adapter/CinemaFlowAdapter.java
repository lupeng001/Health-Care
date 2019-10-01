package bawei.com.health_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.dingtao.common.bean.XiTong;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bawei.com.health_main.R;
import bawei.com.health_main.activity.XingxiangActivity;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class CinemaFlowAdapter extends RecyclerView.Adapter<CinemaFlowAdapter.ViewHolder> {
    Context context;
    List<XiTong> data;
    public CinemaFlowAdapter(Context context,List<XiTong> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CinemaFlowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.xitong_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaFlowAdapter.ViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(data.get(position).getImagePic());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.simp_cinema_flow);
        }
    }
}

