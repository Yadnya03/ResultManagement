package com.example.resultmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Teacher_login extends AppCompatActivity {
    private Button submit;
    public EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        email=findViewById(R.id.singupemail);
        password=findViewById(R.id.signuppassword);
        submit=(Button)findViewById(R.id.signin);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email1=email.getText().toString();
        String password1=password.getText().toString();
        if(!TextUtils.isEmpty(email1)&&!TextUtils.isEmpty(password1)){
            if(email1.equals("admin@gmail.com")&&password1.equals("Admin")){
                Intent intent=new Intent(Teacher_login.this,ViewButtons.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(this, "Please Enter Correct Email or Password", Toast.LENGTH_SHORT).show();
            }


        }
        else {
            Toast.makeText(this, "Please Enter  Email or Password", Toast.LENGTH_SHORT).show();
        }
    }


}