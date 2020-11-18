package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bfci.Adapters.QuestionAdapter;
import com.example.bfci.EnterApp.Login;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AskedQuestionActivity extends AppCompatActivity implements QuestionAdapter.MyListener {
    RecyclerView lecturRecy;
    ArrayList<String> question=new ArrayList<>();
    ArrayList<String> email=new ArrayList<>();
    ArrayList<String> date=new ArrayList<>();
    ArrayList<String> id=new ArrayList<>();
    ArrayList<String> answer=new ArrayList<>();
    QuestionAdapter questionAdapter;
    TextView no_data;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_question);
        lecturRecy=findViewById(R.id.questionrecy);
        progressBar=findViewById(R.id.progress);
        lecturRecy.setHasFixedSize(true);
        no_data=findViewById(R.id.no_data);
        lecturRecy.setLayoutManager(new LinearLayoutManager(this));
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        getquestion();


    }

    public void search(View view) {
        question.clear();
        email.clear();
        date.clear();
        id.clear();
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionBank");
        Spinner spinner=findViewById(R.id.spinner1);
        if (!spinner.getSelectedItem().toString().equals("All")){
            query.whereEqualTo("linked_matrial",spinner.getSelectedItem().toString().toLowerCase());
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        if (player.get(i).getString("answer").equals(" ")) {
                            question.add(player.get(i).getString("question"));
                            email.add(player.get(i).getString("email"));
                            date.add(player.get(i).getString("date"));
                            id.add(player.get(i).getString("objectId"));

                        }
                        //answer.add(player.get(i).getString("answer"));
                    }
                    // Log.e("ididid",String.valueOf(id.size())+"    "+id.get(0));
                    progressBar.setVisibility(View.GONE);
                    if (question.size()==0){
                        lecturRecy.setVisibility(View.GONE);
                        no_data.setVisibility(View.VISIBLE);
                    }else {
                        lecturRecy.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);

                    }
                    questionAdapter=new QuestionAdapter(AskedQuestionActivity.this,AskedQuestionActivity.this,question,email,date);
                    lecturRecy.setAdapter(questionAdapter);
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

    @Override
    public void callback() {
        question.clear();
        email.clear();
        date.clear();
        id.clear();
        Log.d("MyApp", "Successfully saved!"+1);

        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionBank");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        if (player.get(i).getString("answer").equals(" ")) {
                            question.add(player.get(i).getString("question"));
                            email.add(player.get(i).getString("email"));
                            date.add(player.get(i).getString("date"));
                            id.add(player.get(i).getString("objectId"));

                        }
                        //answer.add(player.get(i).getString("answer"));
                    }
                    //  Log.e("ididid",String.valueOf(id.size())+"    "+id.get(0));
                    progressBar.setVisibility(View.GONE);
                    if (question.size()==0){
                        lecturRecy.setVisibility(View.GONE);
                        no_data.setVisibility(View.VISIBLE);
                    }else {
                        lecturRecy.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);

                    }

                    questionAdapter=new QuestionAdapter(AskedQuestionActivity.this,AskedQuestionActivity.this,question,email,date);
                    lecturRecy.setAdapter(questionAdapter);
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                }
            }
        });

    }

    public void announce(View view) {
    Dialog_add_announce();
    }
    private void Dialog_add_announce() {
        question.clear();
        email.clear();
        date.clear();
        id.clear();
        Rect displayRectangle = new Rect();
        final Window window = AskedQuestionActivity.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(AskedQuestionActivity.this, R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View contactUsView = LayoutInflater.from(AskedQuestionActivity.this).inflate(R.layout.add_matrial_dialog, viewGroup, false);

        builder.setView(contactUsView);
        final AlertDialog contactUsDialog = builder.create();
       /* String[] a={"1","2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, a);
        spinner.setAdapter(adapter);*/
        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.copyFrom(contactUsDialog.getWindow().getAttributes());
        wm.width = (int) (displayRectangle.width() * 0.9f);
        wm.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wm.gravity = Gravity.CENTER_HORIZONTAL;
        wm.dimAmount = 0.60f;
        final EditText question =contactUsView.findViewById(R.id.question);
        Button send=contactUsView.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner =contactUsView.findViewById(R.id.spinner1);
                String textWho=spinner.getSelectedItem().toString();
                Date currentTime = Calendar.getInstance().getTime();
                SharedPreferences     sharedPreferences= AskedQuestionActivity.this.getSharedPreferences("user",MODE_PRIVATE);
                textWho=textWho.toLowerCase();
                if (!textWho.equals("all")){
                ParseObject soccerPlayers = new ParseObject("QuestionBank");// Store an object
                soccerPlayers.put("question", question.getText().toString());
                soccerPlayers.put("answer", " ");
                soccerPlayers.put("email", sharedPreferences.getString("useremail",""));
                soccerPlayers.put("linked_matrial",textWho );
                soccerPlayers.put("date",String.valueOf(currentTime));
                // soccerPlayers.addAllUnique("attributes", Arrays.asList("fast", "good conditioning"));
// Saving object
                soccerPlayers.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            contactUsDialog.dismiss();
                            Toast.makeText(AskedQuestionActivity.this,"Data Sended",Toast.LENGTH_LONG).show();
                            getquestion();
                            // Success
                        } else {
                            // Error
                        }
                    }
                });
            }else {
                    Toast.makeText(AskedQuestionActivity.this,"choose matrial",Toast.LENGTH_LONG).show();

                }}
        });

        contactUsDialog.show();
        contactUsDialog.getWindow().setAttributes(wm);
        contactUsDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        contactUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        contactUsDialog.setCancelable(true);

    }
     void  getquestion(){
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionBank");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        if (player.get(i).getString("answer").equals(" ")) {
                            question.add(player.get(i).getString("question"));
                            email.add(player.get(i).getString("email"));
                            date.add(player.get(i).getString("date"));
                            id.add(player.get(i).getString("objectId"));

                        }
                        //answer.add(player.get(i).getString("answer"));
                    }
                    // Log.e("ididid",String.valueOf(id.size())+"    "+id.get(0));
                    progressBar.setVisibility(View.GONE);
                    if (question.size()==0){
                        lecturRecy.setVisibility(View.GONE);
                        no_data.setVisibility(View.VISIBLE);
                    }else {
                        lecturRecy.setVisibility(View.VISIBLE);
                        no_data.setVisibility(View.GONE);

                    }
                    questionAdapter=new QuestionAdapter(AskedQuestionActivity.this,AskedQuestionActivity.this,question,email,date);
                    lecturRecy.setAdapter(questionAdapter);
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                }
            }
        });
    }
}
