package com.example.bfci.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bfci.Adapters.AnnounceAdapter;
import com.example.bfci.EnterApp.HomePageActivity;
import com.example.bfci.EnterApp.Login;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnouncementActivity extends AppCompatActivity {
    RecyclerView announceRecy;
    AnnounceAdapter announceAdapter;
    ArrayList<String>notyname=new ArrayList<>();
    ArrayList<String>notytime=new ArrayList<>();
    ArrayList<String>notytext=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        announceRecy=findViewById(R.id.announRecy);
        announceRecy.setHasFixedSize(true);
        announceRecy.setLayoutManager(new LinearLayoutManager(this));
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Announce");
        query.whereEqualTo("who","student");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        notytext.add(player.get(i).getString("text"));
                        notytime.add(player.get(i).getString("time"));
                        notyname.add(player.get(i).getString("name"));
                    }
                    announceAdapter=new AnnounceAdapter(notyname,notytime,notytext);
                    announceRecy.setAdapter(announceAdapter);
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                }
            }
        });



    }

    public void back(View view) {
        finish();
    }
}
