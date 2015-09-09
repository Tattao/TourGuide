package com.example.tourguide;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private HotActivity hotActivity;
    private GuideActivity guideActivity;
    private UserActivity userActivity;

    private View hotLayout;
    private View guideLayout;
    private View userLayout;

    private TextView hotText;
    private TextView guideText;
    private TextView userText;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        fragmentManager=getFragmentManager();
        setTabSelection(0);
    }

    private  void initViews(){
        hotLayout=findViewById(R.id.hot_layout);
        guideLayout=findViewById(R.id.guide_layout);
        userLayout=findViewById(R.id.user_layout);

        hotText=(TextView)findViewById(R.id.hot_text);
        guideText=(TextView)findViewById(R.id.guide_text);
        userText=(TextView)findViewById(R.id.user_text);

        hotLayout.setOnClickListener(this);
        guideLayout.setOnClickListener(this);
        userLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.hot_layout:
                setTabSelection(0);
                break;
            case R.id.guide_layout:
                setTabSelection(1);
                break;
            case R.id.user_layout:
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index){
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                hotText.setTextColor(Color.WHITE);
                if (hotActivity == null) {
                    hotActivity = new HotActivity();
                    transaction.add(R.id.content, hotActivity);
                } else {
                    transaction.show(hotActivity);
                }
                break;
            case 1:
                guideText.setTextColor(Color.WHITE);
                if (guideActivity == null) {
                    guideActivity = new GuideActivity();
                    transaction.add(R.id.content, guideActivity);
                } else {
                    transaction.show(guideActivity);
                }
                break;
            case 2:
                userText.setTextColor(Color.WHITE);
                if (userActivity == null) {
                    userActivity = new UserActivity();
                    transaction.add(R.id.content, userActivity);
                } else {
                    transaction.show(userActivity);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection(){
        hotText.setTextColor(Color.parseColor("#82858b"));
        guideText.setTextColor(Color.parseColor("#82858b"));
        userText.setTextColor(Color.parseColor("#82858b"));
    }

    private void hideFragments(FragmentTransaction transaction) {
        if(hotActivity!=null){
            transaction.hide(hotActivity);
        }
        if(guideActivity!=null){
            transaction.hide(guideActivity);
        }
        if(userActivity!=null){
            transaction.hide(userActivity);
        }
    }
}
