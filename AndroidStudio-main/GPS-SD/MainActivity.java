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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity implements LocationListener{
    TextView tv_lat,tv_log;
    EditText name;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_lat = findViewById(R.id.lat);
        tv_log = findViewById(R.id.lon);
        Button bt1 = (Button) findViewById(R.id.read);
        Button bt2 = (Button) findViewById(R.id.write);
        name = (EditText) findViewById(R.id.file_name);
        message = (EditText) findViewById(R.id.user_message);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        if(ActivityCompat.checkSelfPermission(MainActivity.this,ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this,ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
            return;

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,(LocationListener) this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String inp,text = "";
                    File file = new File(getFilesDir(),name.getEditableText().toString());
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    while ((inp = bufferedReader.readLine()) != null) text += inp;
                    message.setText(text);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getBaseContext(),"No such file",Toast.LENGTH_LONG).show();
                    message.setText("");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File(getFilesDir(),name.getEditableText().toString());
                    FileOutputStream fp = new FileOutputStream(file);
                    fp.write(("Latitude: "+tv_lat.getText().toString()+"\nLongitude: "+tv_log.getText().toString()).getBytes(StandardCharsets.UTF_8));
                    Toast.makeText(getBaseContext(),"File written successfully!",Toast.LENGTH_LONG).show();
                    message.setText("");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
