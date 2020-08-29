package com.example.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin_adver.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView img_splash = findViewById(R.id.img_splash);
        TextView txt_splash = findViewById(R.id.txt_splash);
        Glide.with(this).load(R.drawable.splash).into(img_splash);
        img_splash.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim2));
        txt_splash.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3600);

    }
}