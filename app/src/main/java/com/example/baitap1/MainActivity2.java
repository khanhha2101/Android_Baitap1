package com.example.baitap1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    EditText ngay, gio;
    Button btn;
    TextView txt, kq;
    CheckBox cb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ngay = (EditText) findViewById(R.id.day);
        gio = (EditText) findViewById(R.id.hour);
        btn = (Button) findViewById(R.id.button);
        txt = (TextView) findViewById(R.id.textView);
        kq = (TextView) findViewById(R.id.kq);
        cb = (CheckBox) findViewById(R.id.checkBox);


        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });

        gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonGio();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity2.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setIcon(R.drawable.logo_1);

                alertDialog.setMessage("Bạn có xác nhận không?");

                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String date = ngay.getText().toString();
                        String time = gio.getText().toString();
                        kq.setText(ngay.getText().toString() + "và" + gio.getText().toString() );


                        Thread bamgio=new Thread(){
                            public void run()
                            {
                                try {
                                    sleep(2000);
                                } catch (Exception e) {

                                }
                                finally
                                {
                                    //truyền dữ liệu qua activity3
                                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

                                    Bundle bundle = new Bundle();
                                    bundle.putString("date", date);
                                    bundle.putString("time", time);

                                    intent.putExtra("data", bundle);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                                }
                            }
                        };
                        bamgio.start();

                    }
                });

                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        kq.setText("Chờ bạn xác nhận");
                    }
                });

                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSetting:
                txt.setText("Setting");
                break;
            case R.id.menuShare:
                txt.setText("Share");
                break;
            case R.id.menuEmail:
                txt.setText("Email");
                break;
            case R.id.menuPhone:
                txt.setText("Phone");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ChonNgay() {
        Calendar calendar = Calendar.getInstance();
        int ng = calendar.get(calendar.DATE);
        int th = calendar.get(calendar.MONTH);
        int na = calendar.get(calendar.YEAR);

        DatePickerDialog datePickerDiaLog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2){
                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                ngay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, na, th, ng);
        datePickerDiaLog.show();
    }

    private void ChonGio() {
        Calendar calendar = Calendar.getInstance();
        int gi = calendar.get(calendar.HOUR_OF_DAY);
        int phut = calendar.get(calendar.MINUTE);

        TimePickerDialog timePickerDiaLog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1){
                calendar.set(0, 0, 0, i, i1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                gio.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, gi, phut, true);
        timePickerDiaLog.show();
    }
}