//Pixel 2 -API 22
package com.example.gpslocation;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LocationListener{
    TextView tv_lat,tv_log;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_lat = findViewById(R.id.lat);
        tv_log = findViewById(R.id.lon);
        sendBtn = (Button) findViewById(R.id.sendButton);
        txtphoneNo = (EditText) findViewById(R.id.contact_number);
        txtMessage = (EditText) findViewById(R.id.user_message);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        if(ActivityCompat.checkSelfPermission(MainActivity.this,ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this,ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            return;

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,(LocationListener) this);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(txtphoneNo.getEditableText().toString(),"Latitude: "+tv_lat.getText().toString()+"\nLongitude: "+tv_log.getText().toString()+"\nMessage: "+txtMessage.getText().toString());
            }
        });
    }

    void sendMsg(String number, String message)
    {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, null, null);
        Toast toast = Toast.makeText(MainActivity.this,"SMS Recieved",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        tv_lat.setText(Double.toString(location.getLatitude()));
        tv_log.setText(Double.toString(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
