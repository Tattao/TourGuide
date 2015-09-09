package com.example.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tattao on 2015/9/8.
 */
public class MyAdapter extends BaseAdapter {

    ArrayList<ApkEntity> apk_list;
    LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<ApkEntity> apk_list){
        this.apk_list=apk_list;
        this.inflater=LayoutInflater.from(context);
    }
    public void onDateChange(ArrayList<ApkEntity> apk_list){
        this.apk_list=apk_list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return apk_list.size();
    }

    @Override
    public Object getItem(int position) {
        return apk_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApkEntity entity=apk_list.get(position);
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_layout, null);

            holder.name_tv=(TextView)convertView.findViewById(R.id.item3_apkname);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.name_tv.setText(entity.getName());

        return convertView;
    }
    class ViewHolder{
        TextView name_tv;
    }
}
