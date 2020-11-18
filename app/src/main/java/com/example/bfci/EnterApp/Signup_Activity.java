package com.example.bfci.EnterApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bfci.R;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.Arrays;

public class Signup_Activity extends AppCompatActivity {
    EditText name, email, password, repassword;
    String nameText,emailtext,passwordtext,repasswordtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_);
        name=findViewById(R.id.name1);
        email=findViewById(R.id.email1);
        password=findViewById(R.id.password1);
        repassword=findViewById(R.id.Rpassword1);
        Log.e("emailtext",emailtext+"s");

        //////////////////////////connect to back4app////////////////////////
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
    }
    public void signup(View view) {
        nameText=name.getText().toString();
        emailtext=email.getText().toString();
        passwordtext=password.getText().toString();
        repasswordtext=repassword.getText().toString();
        Log.e("emailtext",emailtext);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        String emailtext=email.getText().toString();
        final String passwordtext=password.getText().toString();
        query.whereEqualTo("email", emailtext);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject player, ParseException e) {
                if (e == null) {
                    Toast.makeText(Signup_Activity.this, "you have signed in before ", Toast.LENGTH_SHORT).show();
                } else {
                   signup(); } }}); }
    void signup(){
        ParseInstallation.getCurrentInstallation().saveInBackground();
        //////////////////////////////////////////////////
        final ParseObject soccerPlayers = new ParseObject("Student");
// Store an object
        if (passwordtext.equals(repasswordtext)&&!passwordtext.equals("")&&!repasswordtext.equals("")) {
            Log.e("emailtext",emailtext);
            if (emailtext.contains("@gmail.com")||emailtext.contains("@yahoo.com")) {
                soccerPlayers.put("email", emailtext);
                soccerPlayers.put("password", passwordtext);
                soccerPlayers.put("name", nameText);
                Toast.makeText(Signup_Activity.this, "sign up successfully ", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(Signup_Activity.this, "email is not correct ", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(Signup_Activity.this, "password not matched", Toast.LENGTH_SHORT).show();
        }
// Saving object
        soccerPlayers.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Success
                } else {
                    // Error
                }
            }
        });
    }
    public void back2(View view) {
        finish();
    }
}
