package com.example.menuapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.simple:
                Toast.makeText(this, "s1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.simple1:
                Toast.makeText(this, "s2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.simple2:
                Toast.makeText(this, "s3", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}