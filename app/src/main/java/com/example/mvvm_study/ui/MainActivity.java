package com.example.mvvm_study.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvvm_study.R;

public class MainActivity extends Activity {

    Button btn_link_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_link_to_login = findViewById(R.id.btn_link_to_login);
        btn_link_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login_intent);
            }
        });
    }
}
