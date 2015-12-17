package com.kingja.customcheckcodeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CustomCheckCodeView cccv_number = (CustomCheckCodeView) findViewById(R.id.cccv_number);
        cccv_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cccv_number.invaliChenkCode();
            }
        });
        final CustomCheckCodeView cccv_chinese = (CustomCheckCodeView) findViewById(R.id.cccv_chinese);
        cccv_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cccv_chinese.invaliChenkCode();
            }
        });
    }
}
