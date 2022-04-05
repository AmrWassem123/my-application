package com.example.amrwassem;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button button3;
    Button button4;
    EditText txt;
    Socket s ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        txt=(EditText) findViewById(R.id.editTextTextPersonName3);
        txt.setVisibility(View.VISIBLE);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    s = new Socket("192.168.1.25",6666);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    dos.writeUTF(String.valueOf(txt.getText()));
                    String msg = din.readUTF();
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}