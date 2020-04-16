package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.example.bfci.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Lecture_Ditails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture__ditails);
        PRDownloader.initialize(getApplicationContext());
    }

    public void back(View view) {
        finish();
    }

    public void youtube(View view) {
        Intent intent=new Intent(this,Video_Details.class);
        startActivity(intent);
    }

    public void dwonloadPdf(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.orimi.com/pdf-test.pdf")));
    }

    public void ViewLec(View view) {
        String URL = "http://www.orimi.com/pdf-test.pdf";
        Intent intent=new Intent(this,PdfViewer.class);
        startActivity(intent);
    }
}
