package com.bawei.health_login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.health_login.R;
import com.dingtao.common.bean.Zhicheng;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class ZhichengAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater li;
    private List<Zhicheng> dataList;
    public ZhichengAdapter(Context ctx,List<Zhicheng> dataList) {
        this.ctx = ctx;
        this.li = LayoutInflater.from(ctx);
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Zhicheng getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(ctx, R.layout.item_select, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();// get convertView's holder

        holder.car_brand.setText(dataList.get(position).getJobTitle());
        return convertView;

    }


    class ViewHolder {
        TextView car_brand;
        public ViewHolder(View convertView){
            car_brand =  convertView.findViewById(R.id.tv_car_brand);
            convertView.setTag(this);//set a viewholder
        }
    }


}
