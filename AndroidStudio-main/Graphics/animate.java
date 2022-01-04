package com.example.ex3_graphics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class animate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Button bt_move=findViewById(R.id.move);
        bt_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move(v);
            }
        });

        Button bt_rotate=findViewById(R.id.rotate);
        bt_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate(v);
            }
        });

        Button bt_blink=findViewById(R.id.blink);
        bt_blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blink(v);
            }
        });

        Button bt_zoom=findViewById(R.id.zoom);
        bt_zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoom(v);
            }
        });

        Button next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(animate.this,draw.class);
                startActivity(i);
            }
        });

    }
    public void move(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);
    }

    public void rotate(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        image.startAnimation(animation);
    }

    public void blink(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        image.startAnimation(animation1);
    }

    public void zoom(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        image.startAnimation(animation1);
    }
}