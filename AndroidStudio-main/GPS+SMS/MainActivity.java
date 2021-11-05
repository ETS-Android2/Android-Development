package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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


public class MainActivity extends AppCompatActivity {

    private Button gpsButton;
    private TextView latitude;
    private TextView longitude;
    private LocationManager locationManager;
    private LocationListener listener;
    private Button nextButton;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitude = (TextView) findViewById(R.id.lat);
        longitude = (TextView) findViewById(R.id.lon);
        gpsButton = (Button) findViewById(R.id.gpsButton);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        sendBtn = (Button) findViewById(R.id.sendButton);
        txtphoneNo = (EditText) findViewById(R.id.contact_number);
        txtMessage = (EditText) findViewById(R.id.user_message);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude.setText("" + location.getLongitude());
                latitude.setText("" + location.getLatitude());
            }
        };
        configure();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(txtphoneNo.getText().toString(), null, "Latitude: "+latitude.getText().toString()+"\nLongitude: "+longitude.getText().toString()+"\nMessage: "+txtMessage.getText().toString(), null, null);
                        Toast.makeText(MainActivity.this, "SMS Sent!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        gpsButton.setOnClickListener(view -> {
            locationManager.requestLocationUpdates("gps", 5000, 0, listener);
        });
    }

    void configure()
    {
        // Ask permissions
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
    }
}