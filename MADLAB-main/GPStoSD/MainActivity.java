package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
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

public class MainActivity extends AppCompatActivity {

    private Button gpsButton;
    private TextView latitude;
    private TextView longitude;
    private LocationManager locationManager;
    private LocationListener listener;
    private Button nextButton;
    Button read,write;
    TextView ReadBox;
    String fileName,filePath,fileContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitude = (TextView) findViewById(R.id.latitude1);
        longitude = (TextView) findViewById(R.id.longitude1);
        gpsButton = (Button) findViewById(R.id.search);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        read =findViewById(R.id.Read);
        write = findViewById(R.id.Write);
        ReadBox = findViewById(R.id.ReadBox);

        fileName ="myFile.txt";
        filePath = "FileDir";

        if(!isExternalStorageAvailableRW()){
            write.setEnabled(false);
        }

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude.setText("" + location.getLongitude());
                latitude.setText("" + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        write.setOnClickListener(view -> {
            ReadBox.setText("");
            fileContents = latitude.getText().toString().trim() +"," +longitude.getText().toString().trim() ;
            if(!fileContents.equals("")) {
                //Get external files dir
                File myExtFile = new File(getExternalFilesDir(filePath), fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(myExtFile);
                    fos.write(fileContents.getBytes());
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "TextFile written on SD Card", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Input Field Empty", Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(view -> {
            FileReader fr;
            File myExtFile = new File(getExternalFilesDir(filePath),fileName);
            StringBuilder sb = new StringBuilder();
            String contents = "";
            try{
                fr = new FileReader(myExtFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while(line!=null){
                    contents += line;
                    line = br.readLine();
                }
                ReadBox.setText(contents);
            }
            catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        });

        configure_button1();
        }

    private boolean isExternalStorageAvailableRW() {
        String ExternalStorageState = Environment.getExternalStorageState();
        if(ExternalStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            configure_button1();
        }
    }

    void configure_button1(){
        // first check for permissions

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        // this code won't execute IF permissions are not allowed.

        gpsButton.setOnClickListener(view -> {
            locationManager.requestLocationUpdates("gps", 5000, 0, listener);
        });
    }
}
