package com.example.otp_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
Button b1;
TextView e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e1=findViewById(R.id.ot1);
        b1=findViewById(R.id.ot2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int randomnumber=random.nextInt(999999);
                System.out.println("======="+randomnumber+"\n");
                e1.setText(String.valueOf(randomnumber));

                SmsManager sms=SmsManager.getDefault();
                String num="5554";
                String msg=String.valueOf(randomnumber);
                sms.sendTextMessage(num,null,"OTP: "+msg,null,null);
            }
        });
    }
}