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
        final View userlayout=inflater.inflate(R.layout.user_layout, container, false);

        fragmentManager=getFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        textView=(TextView)userlayout.findViewById(R.id.regpage);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*transaction.hide(userActivity);
                if(regActivity==null){
                    regActivity=new RegActivity();
                    transaction.add(R.id.content,regActivity);
                    transaction.show(regActivity);
                    Log.i("tiaozhuan","succeed");
                    //getFragmentManager().beginTransaction().replace(R.id.user_layout,regActivity);

                }else{
                    transaction.show(regActivity);
                    Log.i("tiaozhuan", "unsucceed");
                }*/
                Intent intent=new Intent();
            }
        });

        return userlayout;
    }
}
