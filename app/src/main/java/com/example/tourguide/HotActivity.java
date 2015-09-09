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
    //����
    private View hotlayout;
    private Button btn_Search;
    private AutoCompleteTextView actv_Search;
    ArrayList<ApkEntity> apk_list;


    //����һ������
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


        //�������ƥ��
       ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,res);
        actv_Search.setAdapter(adapter1);
        //����������Ӽ����¼�
        actv_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actv_Search.getText() == null) {
                    actv_Search.setText("");
                }
            }
        });
        //��������ť��Ӽ����¼�
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
            entity.setName("Ĭ��ͼƬ");
            apk_list.add(entity);
        }
    }
    private void setReflashData() {
        for (int i = 0; i < 1; i++) {
            ApkEntity entity = new ApkEntity();
            entity.setName("ˢ�º��ͼƬ");
            apk_list.add(0,entity);
        }
    }

    @Override
    public void onReflash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //��ȡ��������
                setReflashData();
                //֪ͨ������ʾ
                showList(apk_list);
                //֪ͨlistview ˢ��������ϣ�
                listView.reflashComplete();
            }
        },2000);
    }
}
