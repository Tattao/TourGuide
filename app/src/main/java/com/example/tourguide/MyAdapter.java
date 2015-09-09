package com.example.tourguide;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.superbearman6.imagecachetatics.ImageCacheManager;
import java.io.IOException;
import java.net.URL;
import android.os.AsyncTask;
import android.os.Bundle;

import javax.security.auth.callback.Callback;


/**
 * Created by Tattao on 2015/9/8.
 */
public class MyAdapter extends BaseAdapter implements View.OnClickListener {

    ArrayList<ApkEntity> apk_list;
    LayoutInflater inflater;
    private ImageView mImageView;
    private ImageCacheManager imageCacheManager;
    private static final String TAG="ContentAdapter";
    private Callback mCallback;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity*/
    public  interface Callback{
        public void click(View v);
    }

    public MyAdapter(Context context, ArrayList<ApkEntity> apk_list, Callback callback){
        this.apk_list=apk_list;
        this.inflater=LayoutInflater.from(context);
        mCallback=callback;
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

            //LRU
            imageCacheManager=ImageCacheManager.getImageCacheService(convertView.getContext(),
                    ImageCacheManager.MODE_FIXED_MEMORY_USED,"num");
            imageCacheManager.setMax_num(8);

            imageCacheManager=ImageCacheManager.getImageCacheService(convertView.getContext(),
                    ImageCacheManager.MODE_FIXED_MEMORY_USED,"nocache");

            mImageView=(ImageView)convertView.findViewById(R.id.item3_apkiv);
            new DownloadTask().execute("http://img.ivsky.com/img/tupian/pic/201509/06/jinshan_temple.jpg");

            holder.name_tv=(TextView)convertView.findViewById(R.id.item3_apkname);
            //holder.items_iv=(ImageView)convertView.findViewById(R.id.item3_apkiv);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.name_tv.setText(entity.getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }

    private class DownloadTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                return imageCacheManager.downlaodImage(new URL(params[0]));
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mImageView.setImageBitmap(result);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            //向imageview中添加图片
            mImageView.setBackgroundResource(R.drawable.itemimg);
            super.onPreExecute();
        }
    }
    class ViewHolder{
        TextView name_tv;
        //ImageView items_iv;
    }
}
