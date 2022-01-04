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
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    EditText code;
    Button delete;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        code = findViewById(R.id.empcode_upd);
        delete = findViewById(R.id.button_search);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empcode = code.getText().toString();

                if (empcode.length() == 0)
                {
                    code.requestFocus();
                    Toast.makeText(getBaseContext(),"FIELD CANNOT BE EMPTY",Toast.LENGTH_SHORT).show();
                    code.setError("FIELD CANNOT BE EMPTY");
                }

                dbHelper = new DBHelper(DeleteActivity.this);
                try{
                    dbHelper.deleteEmployee(empcode);
                    Toast.makeText(getBaseContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                    code.setText("");
                }
                catch(SQLiteException e){
                    Toast.makeText(DeleteActivity.this,e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}