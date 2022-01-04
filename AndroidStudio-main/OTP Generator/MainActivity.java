package com.example.otp_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button b1,b2;
Cursor rs;
EditText a1,a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);
        a1=findViewById(R.id.editText1);
        a2=findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity2=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(MainActivity2);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                db=openOrCreateDatabase("db",MODE_PRIVATE,null);
                String a_1=a1.getText().toString();
                String a_2=a2.getText().toString();
                rs=db.rawQuery("SELECT * FROM STUDENT WHERE USERNAME=? AND PASSWORD = ?;",new String[]{a_1,a_2});
                rs.moveToFirst();
                if(rs.getString(0).equals(a_1) && rs.getString(1).equals(a_2)){

                    Intent MainActivity3=new Intent(MainActivity.this,MainActivity3.class);
                    startActivity(MainActivity3);
                    String msg="Login Successfull";
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                }
                else{
                    String msg1="Login UnSuccessfull";
                    Toast.makeText(MainActivity.this,msg1,Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}