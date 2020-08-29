package com.example.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.admin_adver.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import fragments.reports_fragment;
import fragments.request_fragment;
import fragments.slider_fragment;
import fragments.users_fragment;

public class show_bottom_navigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    fragments.reports_fragment reports_fragment;
    fragments.request_fragment request_fragment;
    fragments.slider_fragment slider_fragment;
    fragments.users_fragment users_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_show_tabs);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_main);
        reports_fragment = new reports_fragment();
        request_fragment = new request_fragment();
        slider_fragment = new slider_fragment();
        users_fragment = new users_fragment();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(Color.parseColor("#F3F2F2"));

        setFragment(reports_fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.reports_id:{
                        setFragment(reports_fragment);
                        return true;}
                    case R.id.requests_id:{
                        setFragment(request_fragment);
                        return true; }
                    case R.id.slider_id:{
                        setFragment(slider_fragment);
                        return true; }
                    case R.id.users_id:{
                        setFragment(users_fragment);
                        return true; }
                }

                return false;
            }
        });
    }
    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main,fragment);
        fragmentTransaction.commit();

    }
}