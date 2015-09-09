package com.example.tourguide;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UserActivity extends Fragment {


    private RegActivity regActivity;
    TextView textView;
    UserActivity userActivity=this;

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(final LayoutInflater inflater,ViewGroup container,
                             Bundle saveInstanceState){
        View userlayout=inflater.inflate(R.layout.user_layout, container, false);

        fragmentManager=getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        textView=(TextView)userlayout.findViewById(R.id.regpage);
        textView.setOnClickListener(new TXOnClickListener());
        return userlayout;
    }
    class TXOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(getActivity(), RegActivity.class);
            startActivity(intent);
        }
    }
}
