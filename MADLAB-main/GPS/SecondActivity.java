package com.example.ex6_locationfinder;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SecondActivity  extends AppCompatActivity {
    double latitude,longitude;
    EditText locationName;
    Button searchbtn;
    TextView lat_res,long_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        locationName=(EditText)findViewById(R.id.searchBox);
        searchbtn=(Button)findViewById(R.id.search);
        lat_res=(TextView)findViewById(R.id.latitude1);
        long_res=(TextView)findViewById(R.id.longitude1);



        searchbtn.setOnClickListener(v -> {
            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());


            lat_res.setText("");
            long_res.setText("");

            try
            {
                List<Address> address;

                address = geocoder.getFromLocationName(locationName.getText().toString(),1);

                if(address.size() > 0)
                {
                    for(int i=0;i<address.size();i++)
                    {
                        latitude= address.get(i).getLatitude();
                        longitude= address.get(i).getLongitude();
                        lat_res.append(" "+latitude);
                        long_res.append(" "+longitude);

                    }

                }



            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finally
            {

            }

        });
    }

}
