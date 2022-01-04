package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sample.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name= findViewById(R.id.name);
                EditText address= findViewById(R.id.address);
                EditText age= findViewById(R.id.age);
                EditText dob= findViewById(R.id.dob);
                RadioGroup gender= findViewById(R.id.gender);
                Spinner marital= findViewById(R.id.marital);
                EditText phone= findViewById(R.id.phone);
                EditText time= findViewById(R.id.time);
                CheckBox smoking= findViewById(R.id.smoking);
                CheckBox alcohol= findViewById(R.id.alcohol);

                if(TextUtils.isEmpty(name.getText())) {
                    Toast.makeText(MainActivity.this, "Enter your name ", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(address.getText())) {
                    Toast.makeText(MainActivity.this, "Enter Your Address ", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(age.getText())) {
                    Toast.makeText(MainActivity.this, "Enter Your Age ", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(dob.getText())) {
                    Toast.makeText(MainActivity.this, "Enter Your Dob ", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(phone.getText())) {
                    Toast.makeText(MainActivity.this, "Enter Your Phone Number ", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(time.getText())) {
                    Toast.makeText(MainActivity.this, "Enter Time of Registration ", Toast.LENGTH_SHORT).show();
                }

                else if(!(((RadioButton)findViewById(R.id.male)).isChecked() || ((RadioButton)findViewById(R.id.female)).isChecked())){
                    Toast.makeText(MainActivity.this, "Select Your Gender ", Toast.LENGTH_SHORT).show();
                }
                else {
                    String name_value=name.getText().toString();
                    String address_value=address.getText().toString();
                    String age_value=age.getText().toString();
                    String dob_value=dob.getText().toString();
                    String gender_value=((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();
                    String marital_value= marital.getSelectedItem().toString();
                    String phone_value=phone.getText().toString();
                    String time_value=time.getText().toString();

                    String addiction_value;
                    if(smoking.isChecked() && alcohol.isChecked())
                        addiction_value="Smoking and Alcohol";
                    else if(smoking.isChecked())
                        addiction_value="Smoking";
                    else if(alcohol.isChecked())
                        addiction_value="Alcohol";
                    else
                        addiction_value="None";


                    Intent intent = new Intent(MainActivity.this, display.class);

                    intent.putExtra("name", name_value);
                    intent.putExtra("address", address_value);
                    intent.putExtra("age", age_value);
                    intent.putExtra("dob", dob_value);
                    intent.putExtra("gender", gender_value);
                    intent.putExtra("marital", marital_value);
                    intent.putExtra("phone", phone_value);
                    intent.putExtra("time", time_value);
                    intent.putExtra("addiction", addiction_value);

                    Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }


            }
        });

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        EditText dob = findViewById(R.id.dob);
        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dob_select(dob);
                }
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dob_select(dob);
            }
        });

        EditText time = findViewById(R.id.time);
        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    time_select(time);
                }
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_select(time);
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

    public void time_select(EditText time) {
        Calendar c = Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int min= c.get(Calendar.MINUTE);
        TimePickerDialog t = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String m;
                if(minute<10)
                {
                    m=Integer.toString(minute);
                    m="0"+m;
                }
                else
                {
                    m=Integer.toString(minute);
                }
                if(hourOfDay==0)
                {
                    time.setText(String.format(Integer.toString(hourOfDay+12)+":"+m+" AM"));
                }
                else if(hourOfDay<12)
                {
                    time.setText(String.format(Integer.toString(hourOfDay)+":"+m+" AM"));
                }
                else if(hourOfDay==12)
                {
                    time.setText(String.format(Integer.toString(hourOfDay)+":"+m+" PM"));
                }
                else
                {
                    time.setText(String.format(Integer.toString(hourOfDay-12)+":"+m+" PM"));
                }

            }
        },hour,min, false);
        t.show();
    }
}