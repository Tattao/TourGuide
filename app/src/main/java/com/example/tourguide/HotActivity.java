package com.example.tourguide;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class HotActivity extends Fragment implements ReFlashListView.IReflashListener{
    //搜索
    private View hotlayout;
    private Button btn_Search;
    private AutoCompleteTextView actv_Search;
    ArrayList<ApkEntity> apk_list;


    //定义一个数组
    private String[] res={"chengdu1","chengdu nihao1",
            "chengdu3","chongqing1","chongqing shancheng2","anhui1","An qing1 ","An hui 2"};


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle saveInstanceState){
        hotlayout=inflater.inflate(R.layout.hot_layout,container,false);
        //listView=(ListView)hotlayout.findViewById(R.id.listView);
        actv_Search=(AutoCompleteTextView)hotlayout.findViewById(R.id.actv_Search);
        btn_Search=(Button)hotlayout.findViewById(R.id.btn_search);

        setData();
        showList(apk_list);


        //搜索框的匹配
       ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,res);
        actv_Search.setAdapter(adapter1);
        //给搜索框添加监听事件
        actv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actv_Search.getText() == null) {
                    actv_Search.setText("");
                }
            }
        });
        //给搜索按钮添加监听事件
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HotActivity.this.getActivity(), actv_Search.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return hotlayout;

    }

    MyAdapter adapter;
    ReFlashListView listView;
    private void showList(ArrayList<ApkEntity> apk_list){
        if (adapter==null){
            listView=(ReFlashListView)hotlayout.findViewById(R.id.listView);
            listView.setInterface(this);
            adapter=new MyAdapter(hotlayout.getContext(),apk_list);
            listView.setAdapter(adapter);
        }else {
            adapter.onDateChange(apk_list);
        }
    }
    private void setData(){
        apk_list=new ArrayList<ApkEntity>();
        for (int i=0;i<5;i++){
            ApkEntity entity=new ApkEntity();
            entity.setName("默认图片");
            apk_list.add(entity);
        }
    }
    private void setReflashData() {
        for (int i = 0; i < 1; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("刷新后的图片");
            apk_list.add(0,entity);
        }
    }

    @Override
    public void onReflash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新数据
                setReflashData();
                //通知界面显示
                showList(apk_list);
                //通知listview 刷新数据完毕；
                listView.reflashComplete();
            }
        },2000);
    }
}
