package com.example.sample;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sample.R;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        TextView name= findViewById(R.id.name);
        TextView address= findViewById(R.id.address);
        TextView age= findViewById(R.id.age);
        TextView dob= findViewById(R.id.dob);
        TextView gender= findViewById(R.id.gender);
        TextView marital= findViewById(R.id.marital);
        TextView phone= findViewById(R.id.phone);
        TextView time= findViewById(R.id.time);
        TextView addiction= findViewById(R.id.addiction);

        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        age.setText(getIntent().getStringExtra("age"));
        dob.setText(getIntent().getStringExtra("dob"));
        gender.setText(getIntent().getStringExtra("gender"));
        marital.setText(getIntent().getStringExtra("marital"));
        phone.setText(getIntent().getStringExtra("phone"));
        time.setText(getIntent().getStringExtra("time"));
        addiction.setText(getIntent().getStringExtra("addiction"));

        Button back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
            }
        });
    }
}
