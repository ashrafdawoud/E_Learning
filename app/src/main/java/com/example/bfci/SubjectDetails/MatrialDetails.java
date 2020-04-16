
package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bfci.Adapters.LectureAdapter;
import com.example.bfci.R;

public class MatrialDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrial_details);
        RecyclerView lecturRecy=findViewById(R.id.lecture_details);
        lecturRecy.setHasFixedSize(true);
        lecturRecy.setLayoutManager(new LinearLayoutManager(this));
        LectureAdapter lectureAdapter=new LectureAdapter(this);
        lecturRecy.setAdapter(lectureAdapter);

    }

    public void back(View view) {
        finish();
    }
}
