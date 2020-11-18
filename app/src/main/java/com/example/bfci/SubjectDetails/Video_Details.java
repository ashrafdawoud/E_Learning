package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.bfci.R;

public class Video_Details extends AppCompatActivity {
    WebView videoWeb;
    String videolink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          videolink=extras.getString("vediolink");
            // and get whatever type user account id is//
        }
        videoWeb = findViewById(R.id.webView);
        videoWeb.getSettings().setJavaScriptEnabled(true);
        videoWeb.setWebChromeClient(new WebChromeClient() {
        } );
       videoWeb.loadData( "<iframe width=\"100%\" height=\"100%\" src=\""+videolink+"\" frameborder=\"0\" allowfullscreen></iframe>", "text/html" , "utf-8");
    }

    public void back(View view) {finish();
    }
}
