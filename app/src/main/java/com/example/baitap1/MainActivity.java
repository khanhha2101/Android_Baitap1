package com.example.baitap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    ImageView logo, splashImg;
    TextView appname, chuyen;
    LottieAnimationView lottieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        appname = findViewById(R.id.app_name);
        splashImg = findViewById(R.id.img);
        lottieView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        appname.animate().translationY(-1400).setDuration(1000).setStartDelay(4000);
        lottieView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

        //Dùng cài đặt sau 5 giây màn hình tự chuyển
        Thread bamgio=new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                } catch (Exception e) {

                }
                finally
                {
                    Intent mh2 = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(mh2);
                }
            }
        };
        bamgio.start();
    }
    //sau khi chuyển sang màn hình chính, kết thúc màn hình chào
    protected void onPause(){
        super.onPause();
        finish();
    }
}
