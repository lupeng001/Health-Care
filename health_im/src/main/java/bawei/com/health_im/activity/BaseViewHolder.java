package bawei.com.health_im.activity;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder{


    public BaseViewHolder(View itemView) {
        super(itemView);
        findView(itemView);
    }

    public abstract void findView(View view);
    public abstract void setHolderData(Object o,int position);


}
