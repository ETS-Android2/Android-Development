package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button convert,clear;
        EditText src,destn;
        RadioButton sinr,sdollar,seuro,dinr,ddollar,deuro;
        convert=(Button) findViewById(R.id.button);
        clear=(Button) findViewById(R.id.clear);
        src=(EditText) findViewById(R.id.srcv);
        destn=(EditText) findViewById(R.id.dstnv);
        sinr=(RadioButton) findViewById(R.id.inrbutton);
        sdollar=(RadioButton) findViewById(R.id.dollarbutton);
        seuro=(RadioButton) findViewById(R.id.eurobutton);
        dinr=(RadioButton) findViewById(R.id.inrbuttond);
        ddollar=(RadioButton) findViewById(R.id.dollarbuttond);
        deuro=(RadioButton) findViewById(R.id.eurobuttond);


        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = src.getText().toString();
                float a = Float.parseFloat(s);
                //float ans=a*72;
                if (sinr.isChecked() && ddollar.isChecked()){
                    float ans= (float) (a*0.0133);
                    destn.setText(""+ans);
                }
                if (sinr.isChecked() && deuro.isChecked()){
                    float ans= (float) (a*0.0154);
                    destn.setText(""+ans);
                }
                if (sdollar.isChecked() && dinr.isChecked()){
                    float ans= (float) (a*74.9);
                    destn.setText(""+ans);
                }
                if (sdollar.isChecked() && deuro.isChecked()){
                    float ans= (float) (a*0.86);
                    destn.setText(""+ans);
                }
                if (seuro.isChecked() && dinr.isChecked()){
                    float ans= (float) (a*86.75);
                    destn.setText(""+ans);
                }
                if (seuro.isChecked() && ddollar.isChecked()){
                    float ans= (float) (a*1.1565);
                    destn.setText(""+ans);
                }
                if (seuro.isChecked() && deuro.isChecked()){
                    destn.setText(""+a);
                }
                if (sinr.isChecked() && dinr.isChecked()){
                    destn.setText(""+a);
                }
                if (sdollar.isChecked() && ddollar.isChecked()){
                    destn.setText(""+a);
                }



            }

        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinr.setChecked(false);
                sdollar.setChecked(false);
                seuro.setChecked(false);
                dinr.setChecked(false);
                ddollar.setChecked(false);
                deuro.setChecked(false);
                src.setText("");
                destn.setText("");
            }
        });

    }
}