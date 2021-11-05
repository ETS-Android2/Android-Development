package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;
public class MainActivity extends AppCompatActivity {
    EditText contact_no,user_message;
    Button send;
    NotificationManager manager;
    Notification myNotication;
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,new
                String[]{READ_SMS,RECEIVE_SMS,SEND_SMS,READ_PHONE_STATE},1);
        contact_no = findViewById(R.id.contact_number);
        user_message = findViewById(R.id.user_message);
        send = findViewById(R.id.sendButton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cnt <2)
                    sendMsg(contact_no.getText().toString(), user_message.getText().toString());
                else
                {
                    //Exit Activity
                    Toast etoast = Toast.makeText(MainActivity.this,"SMS Overloaded",Toast.LENGTH_SHORT);
                    etoast.show();
                    finish();
                    moveTaskToBack(true);
                }
            }
        });

    }

    void sendMsg(String number, String message)
    {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, null, null);
        Toast toast = Toast.makeText(MainActivity.this,"SMS Recieved",Toast.LENGTH_SHORT);
        toast.show();
        cnt+=1;
        //Status Bar notification
        sendNotif(message);
    }

    void sendNotif(String message)
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setContentTitle("Message Alert!");
        mBuilder.setContentText(message);

        //Reopen MainActivity on clicking the notification
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(11, mBuilder.build());
    }

}