package com.example.tourguide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HotActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle saveInstanceState){
        View hotlayout=inflater.inflate(R.layout.hot_layout,container,false);
        return hotlayout;
    }
}
