
package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bfci.Adapters.LectureAdapter;
import com.example.bfci.Adapters.NotifyAdappter;
import com.example.bfci.Adapters.Practice;
import com.example.bfci.Adapters.QuizAdapter;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MatrialDetails extends AppCompatActivity {
    ArrayList<String> matrialimage=new ArrayList<>();
    ArrayList<String> lecture_link=new ArrayList<>();
    ArrayList<String> vedio_link=new ArrayList<>();
    ArrayList<String> assigment_link=new ArrayList<>();
    ArrayList<String> quizNmae=new ArrayList<>();
    RecyclerView lecturRecy;
    LectureAdapter lectureAdapter;
    String matrialName=null;
    RecyclerView popularRecycler;
    QuizAdapter quizAdapter;
    final List<Practice> practiceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrial_details);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            matrialName = extras.getString("matrial");
            // and get whatever type user account id is
        }
         lecturRecy=findViewById(R.id.lecture_details);
        lecturRecy.setHasFixedSize(true);
        lecturRecy.setLayoutManager(new LinearLayoutManager(this));

        popularRecycler=findViewById (R.id.quiz);
        popularRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularRecycler.setHasFixedSize(true);
        getWeakLectures();
        getquiz();
    }

    public void back(View view) {
        finish();
    }
    void  getWeakLectures(){
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("MatrialsDetails");
        query.whereEqualTo("matrial_name", matrialName);
        query.orderByAscending("weak_num");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        matrialimage.add(player.get(i).getString("matrial_image"));
                        assigment_link.add(player.get(i).getString("assignment_link"));
                        vedio_link.add(player.get(i).getString("lecture_video"));
                        lecture_link.add(player.get(i).getString("lecture_link"));

                    }
                    lectureAdapter=new LectureAdapter(MatrialDetails.this,matrialimage,assigment_link,vedio_link,lecture_link);
                    lecturRecy.setAdapter(lectureAdapter);
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                } }}); }
    void  getquiz(){
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quizs");
        query.whereEqualTo("matrial_name", matrialName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        matrialimage.add(player.get(i).getString("matrial_image"));
                        quizNmae.add(player.get(i).getString("quiz_name"));
                    }
                    findLevels();
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                }
            }
        });
    }
    public void findLevels() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Quizs");
        //sorting object, ordering it by level number
        query.whereEqualTo("quiz_id","n1");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> result, ParseException e) {
                Log.e("result123456", String.valueOf(result.size()));

                if (e == null) {
                    for (int i = 0; i < result.size(); i++) {
                        Practice practice = new Practice();
                        practice.level_number = result.get(i).getNumber("level_number");
                        practice.level_name = result.get(i).getString("level_name");
                        practice.level_color = result.get(i).getString("level_url");
                        //retrieving array of quiz using the state name
                        practice.quiz = result.get(i).getList("level_exam");
                        practice.setQuiz(result.get(i).<String>getList("level_exam"));
                        // Log.e("result __",String.valueOf(result.toArray()));
                        //send result data to adapter->recyclerView
                        practiceList.add(practice);
                        Log.e("result__2", String.valueOf(result.get(i).getList("level_exam")));
                        Log.e("result123456", String.valueOf(result.get(i).getString("level_exam")));

                    }
                    quizAdapter=new QuizAdapter(practiceList,MatrialDetails.this,matrialimage,quizNmae);
                    popularRecycler.setAdapter(quizAdapter);
                } else {
                    // something went wrong
                }


            }
        });
    }
}
