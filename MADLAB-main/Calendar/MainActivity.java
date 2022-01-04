package com.example.samp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dobbtn = findViewById(R.id.dob);
        EditText dob = findViewById(R.id.getdate);
        Button sbmt = findViewById(R.id.submt);


        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(dob.getText())){
                    Toast.makeText(MainActivity.this,"PLS ENTER DOB",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, Display.class);
                    intent.putExtra("dob", dob.getText().toString());
                    Toast.makeText(MainActivity.this,"DATE SELECTED",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }

//                Intent intnt = new Intent(MainActivity.this, Display.class);
//                intnt.putExtra("dob", dob.getText().toString());
//                startActivity(intnt);
            }
        });


        dobbtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dob_select(dob);
                }
            }
        });
        dobbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dob_select(dob);
            }
        });

        }

    public void dob_select(EditText dob) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        DatePickerDialog d = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int y, int m, int d) {
                c.set(y, m, d);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                dob.setText(sdf.format(c.getTime()));
            }
        }, year, month, day);
        d.show();
    }
}

