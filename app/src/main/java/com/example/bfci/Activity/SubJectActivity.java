package com.example.bfci.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.bfci.Adapters.SubjectAdapter;
import com.example.bfci.R;

public class SubJectActivity extends AppCompatActivity {
    SubjectAdapter subjectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_ject);
        RecyclerView recy=findViewById(R.id.subjectRecy);
        recy.setHasFixedSize(true);
        recy.setLayoutManager(new GridLayoutManager(SubJectActivity.this, 2));
        subjectAdapter=new SubjectAdapter(SubJectActivity.this);
        recy.setAdapter(subjectAdapter);
    }

    public void back(View view) {
        finish();
    }
}
