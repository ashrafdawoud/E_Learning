package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.bfci.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
    java.io.File xmlFile = new java.io.File(//this.getFilesDir()
                "lec00.pdf");
        Uri path = Uri.fromFile(xmlFile);
        Log.e("path", String.valueOf(path));
        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromUri(path)
                .load();

    }

}
