package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bfci.Adapters.AnnounceAdapter;
import com.example.bfci.Adapters.QuestionBankAdapter;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class QuestionBankActivity extends AppCompatActivity {
    ArrayList<String> question=new ArrayList<>();
    ArrayList<String> answer=new ArrayList<>();
    RecyclerView announceRecy;
    QuestionBankAdapter questionBankActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_bank);

        announceRecy=findViewById(R.id.anserRecy);
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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionBank");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        if (!player.get(i).get("answer").equals(" ")) {
                            question.add(player.get(i).getString("question"));
                            answer.add(player.get(i).getString("answer"));
                        }
                    }
                    questionBankActivity=new QuestionBankAdapter(question,answer);
                    announceRecy.setAdapter(questionBankActivity);
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
