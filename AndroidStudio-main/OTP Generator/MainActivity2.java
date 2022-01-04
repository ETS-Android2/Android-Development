package com.example.otp_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
Button b3;
EditText e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b3=findViewById(R.id.btn1);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                db=openOrCreateDatabase("db",MODE_PRIVATE,null);
                e3=findViewById(R.id.ed1);
                e4=findViewById(R.id.ed2);
                String use=e3.getText().toString();
                String pass=e4.getText().toString();
                db.execSQL("DROP TABLE  IF EXISTS STUDENT");
                db.execSQL("CREATE TABLE STUDENT(USERNAME VARCHAR(20),PASSWORD VARCHAR(20));");
                db.execSQL("INSERT INTO STUDENT VALUES(?,?);",new String[]{e3.getText().toString(),e4.getText().toString()});
                String msg="inserted";
                Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}