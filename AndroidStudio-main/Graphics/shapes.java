package com.example.ex3_graphics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class shapes extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Bitmap bg = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        ImageView im1 = (ImageView) findViewById(R.id.im1);
        im1.setBackgroundDrawable(new BitmapDrawable(bg));
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();

        paint.setColor(Color.RED);
        paint.setTextSize(50);
        canvas.drawText("Rectangle", 420, 150, paint);
        canvas.drawRect(400, 200, 650, 700, paint);

        paint.setColor(Color.BLUE);
        paint.setTextSize(50);

        canvas.drawText("Circle", 120, 150, paint);
        canvas.drawCircle(200, 350, 150, paint);

        paint.setColor(Color.GREEN);
        paint.setTextSize(50);

        canvas.drawText("Arc", 120, 800, paint);
        canvas.drawArc(80, 850, 350, 1150, 180, 60, true,  paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Line", 480, 800, paint);
        canvas.drawLine(520, 850, 520, 1150, paint);

        Button next= findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(shapes.this,animate.class);
                startActivity(i);
            }
        });
    }
}