package com.example.db_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
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
    EditText name, contact, address, email;
    Button insert, update, delete, view;
    DBHelper DB;
    StringBuffer buffer = new StringBuffer();

    Button read, write;
    TextView ReadBox;
    String fileName, filePath, fileContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        read = findViewById(R.id.Read);
        write = findViewById(R.id.Write);
        ReadBox = findViewById(R.id.ReadBox);

        fileName = "myFile.txt";
        filePath = "FileDir";

        if (!isExternalStorageAvailableRW()) {
            write.setEnabled(false);
        }

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String addressTXT = address.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, addressTXT, emailTXT);
                if (checkinsertdata == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String addressTXT = address.getText().toString();
                String emailTXT = email.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, addressTXT, emailTXT);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if (checkudeletedata == true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Contact :" + res.getString(1) + "\n");
                    buffer.append("Address :" + res.getString(2) + "\n\n");
                    buffer.append("E-mail :" + res.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });

        write.setOnClickListener(view -> {
            ReadBox.setText("");
            fileContents = buffer.toString();
            if (!fileContents.equals("")) {
                //Get external files dir
                File myExtFile = new File(getExternalFilesDir(filePath), fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(myExtFile);
                    fos.write(fileContents.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "TextFile written on SD Card", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Input Field Empty", Toast.LENGTH_SHORT).show();
            }
        });


        read.setOnClickListener(view -> {
            FileReader fr;
            File myExtFile = new File(getExternalFilesDir(filePath), fileName);
            StringBuilder sb = new StringBuilder();
            String contents = "";
            try {
                fr = new FileReader(myExtFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    contents += line;
                    line = br.readLine();
                }
                ReadBox.setText(contents);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        //configure_button1();
    }

    private boolean isExternalStorageAvailableRW() {
        String ExternalStorageState = Environment.getExternalStorageState();
        if (ExternalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
