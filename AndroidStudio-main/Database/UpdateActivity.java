package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText code, name, salary;
    RadioButton male, female, other;
    Spinner spin;
    Button insert;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();

        code = findViewById(R.id.empcode_upd);
        name = findViewById(R.id.empname_upd);
        salary = findViewById(R.id.empsalary_upd);
        male = findViewById(R.id.radioButtonMale);
        female = findViewById(R.id.radioButtonFemale);
        other = findViewById(R.id.radioButtonOther);
        spin = findViewById(R.id.spinner);
        insert = findViewById(R.id.button_search);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setChecked(false);
                other.setChecked(false);
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(false);
                other.setChecked(false);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male.setChecked(false);
                female.setChecked(false);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empcode = code.getText().toString();
                String empname = name.getText().toString();
                String gender;
                if (male.isChecked()) {
                    gender = male.getText().toString();
                } else if (female.isChecked()) {
                    gender = female.getText().toString();
                } else {
                    gender = other.getText().toString();
                }
                String dept = spin.getSelectedItem().toString();
                String sal = salary.getText().toString();

                if (empname.length() == 0) {
                    empname = "";
                }
                else if (empcode.length() == 0) {
                    code.requestFocus();
                    Toast.makeText(getBaseContext(),"ID FIELD CANNOT BE EMPTY",Toast.LENGTH_SHORT).show();
                    code.setError("FIELD CANNOT BE EMPTY");
                }
                else if (sal.length() == 0) {
                    sal = "";
                }
                else {
                    try{
                        dbHelper = new DBHelper(UpdateActivity.this);
                        dbHelper.updateEmployee(empcode, empname, gender, dept, sal);

                        Toast.makeText(getBaseContext(),"Updated successfully",Toast.LENGTH_SHORT).show();

                        code.setText("");
                        name.setText("");
                        male.setChecked(false);female.setChecked(false);other.setChecked(false);
                        spin.setSelection(0);
                        salary.setText("");
                    }
                    catch(SQLiteException e){
                        Toast.makeText(UpdateActivity.this,e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}