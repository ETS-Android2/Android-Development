package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = (EditText) findViewById(R.id.user_name);
        EditText message = (EditText) findViewById(R.id.user_message);
        Button bt1 = (Button) findViewById(R.id.read);
        Button bt2 = (Button) findViewById(R.id.write);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String inp,text = "";
                    File file = new File(getFilesDir(),name.getEditableText().toString());
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    while ((inp = bufferedReader.readLine()) != null) text += inp;
                    message.setText(text);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getBaseContext(),"No such file",Toast.LENGTH_LONG).show();
                    message.setText("");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File file = new File(getFilesDir(),name.getEditableText().toString());
                    FileOutputStream fp = new FileOutputStream(file);
                    fp.write(message.getEditableText().toString().getBytes(StandardCharsets.UTF_8));
                    message.setText("");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
