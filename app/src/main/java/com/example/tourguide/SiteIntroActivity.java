package com.example.tourguide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SiteIntroActivity extends Activity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siteintro_layout);
        textView = (TextView)findViewById(R.id.idm);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String m = textView.getText().toString().trim();
        textView.setText(m+",您点击的是第"+id+"个框");
    }
}
