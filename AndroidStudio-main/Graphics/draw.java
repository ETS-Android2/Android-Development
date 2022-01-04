package com.example.ex3_graphics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class draw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Bitmap bg = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        ImageView im1 = (ImageView) findViewById(R.id.imageView);
        im1.setBackgroundDrawable(new BitmapDrawable(bg));
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        RectF r=new RectF(150,200,500,300);
        canvas.drawRect(r,paint);
        r=new RectF(250,100,400,200);
        canvas.drawRect(r,paint);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(250,300,35,paint);
        canvas.drawCircle(450,300,35,paint);

        paint.setColor(Color.RED);
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

        Button bt_forward=findViewById(R.id.forward);
        bt_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.animate().translationXBy(300f).setDuration(600);
            }
        });

        Button bt_backward=findViewById(R.id.backward);
        bt_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.animate().translationXBy(-300f).setDuration(600);
            }
        });
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