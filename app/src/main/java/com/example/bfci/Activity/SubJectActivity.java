package com.example.bfci.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bfci.Adapters.LectureAdapter;
import com.example.bfci.Adapters.SubjectAdapter;
import com.example.bfci.EnterApp.Login;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SubJectActivity extends AppCompatActivity {
    SubjectAdapter subjectAdapter;
    ArrayList<String> matrialName=new ArrayList<>();
    ArrayList<String> matrialimage=new ArrayList<>();
    RecyclerView recy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_ject);
        recy=findViewById(R.id.subjectRecy);
        recy.setHasFixedSize(true);
        recy.setLayoutManager(new GridLayoutManager(SubJectActivity.this, 2));
        getMatrial();
    }

    public void back(View view) {
        finish();
    }
    void getMatrial(){
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Matrial");
        SharedPreferences sharedPreferences= SubJectActivity.this.getSharedPreferences("user",MODE_PRIVATE);
        query.whereEqualTo("semister",sharedPreferences.getString("semester","first semester"));
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        matrialName.add(player.get(i).getString("name"));
                        matrialimage.add(player.get(i).getString("image"));
                    }
                    subjectAdapter=new SubjectAdapter(SubJectActivity.this,matrialName,matrialimage);
                    recy.setAdapter(subjectAdapter);
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                }
            }
        });
    }
}
