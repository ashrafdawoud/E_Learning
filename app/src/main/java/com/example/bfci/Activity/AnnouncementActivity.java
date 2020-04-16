package com.example.bfci.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.bfci.Adapters.AnnounceAdapter;
import com.example.bfci.R;

public class AnnouncementActivity extends AppCompatActivity {
    RecyclerView announceRecy;
    AnnounceAdapter announceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        announceRecy=findViewById(R.id.announRecy);
        announceRecy.setHasFixedSize(true);
        announceRecy.setLayoutManager(new LinearLayoutManager(this));
        announceAdapter=new AnnounceAdapter();
        announceRecy.setAdapter(announceAdapter);

    }

    public void back(View view) {
        finish();
    }
}
