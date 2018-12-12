package com.example.jgw04.scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText e1, e2;
    Button b1;
    DatabaseHelper db;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.idText);
        e2 = (EditText)findViewById(R.id.passwordText);
        b1 = (Button)findViewById(R.id.loginButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean checkEmailPass = db.emailpassword(email, password);
                if (checkEmailPass == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    dialog = builder.setMessage("Successfully logged in")
                            .setPositiveButton("Ok", null)
                            .create();
                    dialog.show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    Login.this.startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong ID or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
