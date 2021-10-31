package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sample.R;

public class MainActivity extends AppCompatActivity {
    private TextView banner, coord, counter_text;

    private Button counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (TextView) findViewById(R.id.banner);
        counter_text = (TextView) findViewById(R.id.counter);
        coord = (TextView) findViewById(R.id.coord);
        counter = (Button) findViewById(R.id.button);

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(counter_text.getText().toString());
                current++;
                counter_text.setText(String.valueOf(current));
            }});

//Ways to convert from integer to string and back and forth
//from integer to string :- Integer.to_string(num)
//String.valueOf(num)
//from string to int :- Integer.valueOf(str)
//Integer.parseInt(str)

/*
    To create a new thread, either create a class that extends Thread and override void run() function
    To start the thread, do thread.start()
    Or another way to create thread is,
*/




        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (i++ < 300) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            banner.animate().translationXBy(20f);
                            coord.animate().translationXBy(20f);
                            int[] location = new int[2];
                            banner.getLocationOnScreen(location);
                            coord.setText("Location: (" + location[0] + ", " + location[1] + ")");
                        }
                    });

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i++ < 300)
                {
                    try {
                        Thread.sleep(100);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Integer.parseInt(counter_text.getText().toString())%2 == 0) {
                                banner.setBackgroundColor(Color.BLUE);
                            }
                            else{
                                banner.setBackgroundColor(Color.RED);
                            }

                        }
                    });
                }
            }
        }).start();

    }
}

//UI changes can only be done by the main thread
//if a background thread has to update the UI then inside the thread
//It has to create a runOnUiThread(new Runnable(){
//                         void run(){
//                          }

//For animating - translateXBy 20
//To get the coorinates use .. getLocation.onScreen();