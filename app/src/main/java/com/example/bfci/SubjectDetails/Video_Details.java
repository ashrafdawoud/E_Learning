package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.bfci.R;

public class Video_Details extends AppCompatActivity {
    WebView videoWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__details);
        videoWeb = findViewById(R.id.webView);
        videoWeb.getSettings().setJavaScriptEnabled(true);
        videoWeb.setWebChromeClient(new WebChromeClient() {
        } );
        videoWeb.loadData( "<iframe width=\"100%\" height=\"100%\" src=\"https://www" + ".youtube.com/embed/u2CiJXiLVPU\" frameborder=\"0\" allowfullscreen></iframe>", "text/html" , "utf-8");

    }

    public void back(View view) {finish();
    }
}
