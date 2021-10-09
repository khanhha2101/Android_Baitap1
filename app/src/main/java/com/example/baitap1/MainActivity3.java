package com.example.baitap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    EditText txt3;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txt3 = (EditText) findViewById(R.id.txtnd);
        btn3 = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("data");

        if(bundle != null ){
            String date = bundle.getString("date");
            String time = bundle.getString("time");
            txt3.setText(date + "|" + time);
        }

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });

    }
}