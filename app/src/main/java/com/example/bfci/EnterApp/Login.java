package com.example.bfci.EnterApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////connect to back4app////////////////////////
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
        //////////////////////////////////////////////////
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "true");
                Intent intent=new Intent(Login.this,HomePageActivity.class);
                startActivity(intent);

            }
        });

    }


    public void signup(View view) {
        Intent intent=new Intent(Login.this,Signup_Activity.class);
        startActivity(intent);
    }
}

