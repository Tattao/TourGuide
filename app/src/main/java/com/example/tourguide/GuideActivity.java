package com.example.tourguide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GuideActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle saveInstanceState){
        View guidelayout=inflater.inflate(R.layout.guide_layout,container,false);
        return guidelayout;
    }
}
