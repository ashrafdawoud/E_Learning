package com.example.bfci.SubjectDetails;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bfci.Adapters.Practice;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuizDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewQuestion;
    private TextView textViewAnswerA;
    private TextView textViewAnswerB;
    private TextView textViewAnswerC;
    private TextView textViewAnswerD;
    private ImageView imageViewQuestionImageUrl;
    private ImageView imageViewLeftArrow;
    private LinearLayout imageViewNextQuestion;
    private ImageView imageViewLetterA;
    private ImageView imageViewLetterB;
    private ImageView imageViewLetterC;
    private ImageView imageViewLetterD;
    private ConstraintLayout constraintLayoutMain;
    private ConstraintLayout constraintLayoutAnswerA;
    private ConstraintLayout constraintLayoutAnswerB;
    private ConstraintLayout constraintLayoutAnswerC;
    private ConstraintLayout constraintLayoutAnswerD;
    private ProgressBar progressBarQuiz,loadPhoto;
    private Drawable OriginalBackgroundColor;
    private Integer levelNumber;
    private boolean allAnswersCompleted = false;
    private String correctAnswer;
    private boolean shouldRepeatAnimation = true;
    private int progressStatus = 0;
    //variable to move to the next question
    //variable to move to the next question
    private int questionNumber = 0;
    //variable to know total number of questions, to set progressbar max value
    private List<String> quizList;
    private List<String> allPracticeLevels;
    AlertDialog alertDialog;
    View dialogView;
    int position,positionCopy;
    String queryLanguage;
    List<Practice> practiceList = new ArrayList<>();
    TextView next,textViewLevelNumber,textViewLevelName;
    String stateName;
    RelativeLayout pbar;
    int num_of_levels;
    SharedPreferences sharedPreferences;
    int num_correct_ans=0;
    String key="null";
    AlertDialog alertDialog1,alertDialog2;
    View dialogView1,dialogView2;
    Animation clickanim;
    String levelName;
    String question,answerA,answerB,answerC,answerD,imageUrl;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);
        ///////////////////////////////////////////////////////////////
        constraintLayoutMain = findViewById(R.id.constraint_layout_quiz_main);
        textViewLevelName = findViewById(R.id.text_view_quiz_level_name);
        pbar=findViewById(R.id.pbar);
        textViewQuestion = findViewById(R.id.text_view_quiz_question);
        textViewAnswerA = findViewById(R.id.text_view_quiz_answer_a);
        textViewAnswerB = findViewById(R.id.text_view_quiz_answer_b);
        textViewAnswerC = findViewById(R.id.text_view_quiz_answer_c);
        textViewAnswerD = findViewById(R.id.text_view_quiz_answer_d);
        imageViewQuestionImageUrl = findViewById(R.id.image_view_quiz_image_url);
        imageViewLeftArrow = findViewById(R.id.image_view_quiz_left_arrow);
        imageViewNextQuestion = findViewById(R.id.image_view_next_question);
        imageViewLetterA = findViewById(R.id.image_view_quiz_letter_a);
        imageViewLetterB = findViewById(R.id.image_view_quiz_letter_b);
        imageViewLetterC = findViewById(R.id.image_view_quiz_letter_c);
        imageViewLetterD = findViewById(R.id.image_view_quiz_letter_d);
        constraintLayoutAnswerA = findViewById(R.id.constraint_layout_quiz_answer_a);
        constraintLayoutAnswerB = findViewById(R.id.constraint_layout_quiz_answer_b);
        constraintLayoutAnswerC = findViewById(R.id.constraint_layout_quiz_answer_c);
        constraintLayoutAnswerD = findViewById(R.id.constraint_layout_quiz_answer_d);
        progressBarQuiz = findViewById(R.id.progress_bar_quiz);
      //  loadPhoto=findViewById(R.id.photoLoad);
        constraintLayoutAnswerA.setOnClickListener(this);
        constraintLayoutAnswerB.setOnClickListener(this);
        constraintLayoutAnswerC.setOnClickListener(this);
        constraintLayoutAnswerD.setOnClickListener(this);
        imageViewLetterA.setOnClickListener(this);
        imageViewLetterB.setOnClickListener(this);
        imageViewLetterC.setOnClickListener(this);
        imageViewLetterD.setOnClickListener(this);
        textViewAnswerA.setOnClickListener(this);
        textViewAnswerB.setOnClickListener(this);
        textViewAnswerC.setOnClickListener(this);
        textViewAnswerD.setOnClickListener(this);
        imageViewLeftArrow.setOnClickListener(this);
        imageViewNextQuestion.setOnClickListener(this);
        imageViewLetterA.setClickable(false);
        imageViewLetterA.setClickable(false);
        imageViewLetterB.setClickable(false);
        imageViewLetterC.setClickable(false);
        imageViewLetterD.setClickable(false);
        textViewAnswerA.setClickable(false);
        textViewAnswerB.setClickable(false);
        textViewAnswerC.setClickable(false);
        textViewAnswerD.setClickable(false);
        imageViewLeftArrow.setClickable(false);
        imageViewNextQuestion.setClickable(false);
        constraintLayoutMain.setOnClickListener(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {

            } else {
             /*   levelNumber = extras.getInt("LEVEL_NUMBER");
                position=extras.getInt("POSITION");
                positionCopy=position;
                stateName=extras.getString("STATE_QUIZ_NAME");
                key=extras.getString("key");
                //Log.e("position",String.valueOf(position+" "+stateName));*/
                quizList=extras.getStringArrayList("LEVEL_QUIZ");
                textViewLevelName.setText(extras.getString("Name"));

            }
        }
        Log.e("quez",quizList.toString());
        getQueryLanguage();
        //load_next_Leavel(stateName,queryLanguage);
        // quizList=practiceList.get(position).getQuiz();
        OriginalBackgroundColor = textViewQuestion.getBackground();
        //set max value of progress bar depending on quizList size
        loadQuestionAndAnswers();
        OriginalBackgroundColor = textViewQuestion.getBackground();
        progressBarQuiz.setMax(quizList.size());
        hideNextQuestionView();
        updateQuizProgressBar();
    }

    //compare correct answer with answer number, if its the same, then green, not the same, red and method call
    //green:#FF00C853
    //red:#FFD50000
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.image_view_quiz_letter_a:
            case R.id.constraint_layout_quiz_answer_a:
            case R.id.text_view_quiz_answer_a:

                if (correctAnswer.toUpperCase().equals("A")) {
                    imageViewLetterA.setImageResource(R.drawable.ic_one_a);
                    num_correct_ans+=1;
                    Log.e("correcr ",String.valueOf(num_correct_ans));
                    Log.e("number ",String.valueOf(questionNumber));
                    imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
                    imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
                    imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);

                }
                else{
                    //  constraintLayoutAnswerA.setBackgroundColor(Color.parseColor("#23D50000"));
                    //  imageViewLetterA.setImageResource(R.drawable.ic_red_answer_a);
                    imageViewLetterA.setImageResource(R.drawable.ic_one_a);
                    //showCorrectAnswer();
                    Log.e("number ",String.valueOf(questionNumber));
                    imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
                    imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
                    imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);
                }
                //disableMultipleClicks();
                unHideNextQuestionView();
                enableNextQuestionViewClick();
                startAnimationNextQuestion();
                break;


            case R.id.image_view_quiz_letter_b:
            case R.id.constraint_layout_quiz_answer_b:
            case R.id.text_view_quiz_answer_b:
                if (correctAnswer.equals("B")) {
                    //   constraintLayoutAnswerB.setBackgroundColor(Color.parseColor("#1D00C853"));
                    //   imageViewLetterB.setImageResource(R.drawable.ic_green_answer_b);
                    imageViewLetterB.setImageResource(R.drawable.ic_one_b);
                    imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
                    imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
                    imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);
                    num_correct_ans+=1;
                    Log.e("correcr ",String.valueOf(num_correct_ans));



                }
                else{
                    // constraintLayoutAnswerB.setBackgroundColor(Color.parseColor("#23D50000"));
                    // imageViewLetterB.setImageResource(R.drawable.ic_red_answer_b);
                    imageViewLetterB.setImageResource(R.drawable.ic_one_b);
                    imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
                    imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
                    imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);
                    //showCorrectAnswer();

                }
                // disableMultipleClicks();
                unHideNextQuestionView();
                enableNextQuestionViewClick();
                startAnimationNextQuestion();
                break;


            case R.id.image_view_quiz_letter_c:
            case R.id.constraint_layout_quiz_answer_c:
            case R.id.text_view_quiz_answer_c:

                if (correctAnswer.equals("C")) {
                    // constraintLayoutAnswerC.setBackgroundColor(Color.parseColor("#1D00C853"));
                    //  imageViewLetterC.setImageResource(R.drawable.ic_green_answer_c);
                    imageViewLetterC.setImageResource(R.drawable.ic_one_c);
                    imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
                    imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
                    imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);
                    num_correct_ans+=1;
                    Log.e("correcr ",String.valueOf(num_correct_ans));


                }
                else{
                    // constraintLayoutAnswerC.setBackgroundColor(Color.parseColor("#23D50000"));
                    // imageViewLetterC.setImageResource(R.drawable.ic_red_answer_c);
                    imageViewLetterC.setImageResource(R.drawable.ic_one_c);
                    imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
                    imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
                    imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);
                    //showCorrectAnswer();

                }
                //disableMultipleClicks();
                unHideNextQuestionView();
                enableNextQuestionViewClick();
                startAnimationNextQuestion();
                break;


            case R.id.image_view_quiz_letter_d:
            case R.id.constraint_layout_quiz_answer_d:
            case R.id.text_view_quiz_answer_d:

                try {
                    if (correctAnswer.toUpperCase().equals("D")) {
                        //   constraintLayoutAnswerD.setBackgroundColor(Color.parseColor("#1D00C853"));
                        // imageViewLetterD.setImageResource(R.drawable.ic_green_answer_d);
                        imageViewLetterD.setImageResource(R.drawable.ic_one_d);
                        imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
                        imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
                        imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
                        num_correct_ans+=1;
                        Log.e("correcr ",String.valueOf(num_correct_ans));

                    }
                    else{
                        //  constraintLayoutAnswerD.setBackgroundColor(Color.parseColor("#23D50000"));
                        //  imageViewLetterD.setImageResource(R.drawable.ic_red_answer_d);
                        imageViewLetterD.setImageResource(R.drawable.ic_one_d);
                        imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
                        imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
                        imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
                        //showCorrectAnswer();

                    }
                }catch (Exception e){}

                // disableMultipleClicks();
                unHideNextQuestionView();
                enableNextQuestionViewClick();
                startAnimationNextQuestion();
                break;

            case R.id.image_view_quiz_left_arrow:
                finish();
                break;


            case R.id.image_view_next_question:
                if (!allAnswersCompleted) {

                   // loadPhoto.setVisibility(View.GONE);
                    enableSingleClick();
                    loadQuestionAndAnswers();
                    updateQuizProgressBar();
                    stopAnimationNextQuestion();

                }else{
                    //this code called if user click next level
                    allAnswersCompleted = false;
                    quizList = null;
                    questionNumber = 0;
                    progressStatus = 0;
                    position++;
                    levelNumber++;
                    quizList = practiceList.get(position).getQuiz();
                    next.setText("next");
                    enableSingleClick();
                    loadQuestionAndAnswers();
                    updateQuizProgressBar();
                    if (Locale.getDefault().getLanguage().equals("hi")){
                        textViewLevelNumber.setVisibility(View.GONE);
                    }
                    textViewLevelNumber.setText(String.valueOf(levelNumber));
                    textViewLevelName.setText(practiceList.get(position).getLevel_name());
                    Log.e("position ____", String.valueOf(position + "  " + quizList.size() + " " + practiceList.get(position).getLevel_number()));
                }
                break;

            default:
                break;
        }

    }

    /** Values are placed in Json Array.
     * Json Objects inside Json Array
     * Need to retrieve object position from "quizList" json array via getJSONArray.
     * This method is the responsible for loading new questions in the activity
     */
    private void loadQuestionAndAnswers() {

        try {
            JSONArray jsonArray= new JSONArray(quizList);
            Log.e("next level",String.valueOf(quizList));
            JSONObject json_data = jsonArray.getJSONObject(questionNumber);
            question = json_data.getString("question");
            answerA = json_data.getString("answer_a");
            answerB = json_data.getString("answer_b");
            answerC = json_data.getString("answer_c");
            answerD = json_data.getString("answer_d");
            imageUrl = json_data.getString("image_url");

            //making this value global in this activity for being use in others methods(showcorrectAnswer)
            correctAnswer = json_data.getString("correct_answer");


            //method to know if answer D includes a value, if it's null(no answer), then hide the view
            checkValueAnswerD(answerD);
            loadUI(question, answerA, answerB, answerC,answerD,imageUrl);

            loadDefaultColors();

            disableNextQuestionViewClick();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //increasing question number, every time the user click on "Next" will jump to next question
        questionNumber++;
    }
    private void loadQuestionAndAnswerscolor() {
        String question,answerA,answerB,answerC,answerD,imageUrl;
        try {
            JSONArray jsonArray= new JSONArray(quizList);
            Log.e("next level",String.valueOf(quizList));
            JSONObject json_data = jsonArray.getJSONObject(questionNumber);
            question = json_data.getString("question");
            answerA = json_data.getString("answer_a");
            answerB = json_data.getString("answer_b");
            answerC = json_data.getString("answer_c");
            answerD = json_data.getString("answer_d");
            imageUrl = json_data.getString("image_url");

            //making this value global in this activity for being use in others methods(showcorrectAnswer)
            correctAnswer = json_data.getString("correct_answer");


            //method to know if answer D includes a value, if it's null(no answer), then hide the view
            checkValueAnswerD(answerD);
            loadUIcolor(question, answerA, answerB, answerC,answerD,imageUrl);

            loadDefaultColors();

            disableNextQuestionViewClick();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //increasing question number, every time the user click on "Next" will jump to next question
        questionNumber++;
    }

    //load views with data from the json array
    private void loadUI(String question, String answerA, String answerB, String answerC, String answerD,String imageUrl){
//        loadPhoto.setVisibility(View.GONE);
        textViewQuestion.setText(question);
        Log.e("ansewer a",answerA);
        textViewAnswerA.setText(answerA);
        textViewAnswerB.setText(answerB);
        textViewAnswerC.setText(answerC);
        textViewAnswerD.setText(answerD);
        checkValueAnswerD(answerD);


        if (imageUrl.equals("null"))
        {
//            loadPhoto.setVisibility(View.GONE);
            Log.e("errorImage", "error");
            hideQuestionImageUrl();
        }
        else
        {
            Log.e("errorImage", imageUrl);
            unHideQuestionImageUrl();
            //Picasso.with(this).load(imageUrl).error(R.drawable.ic_green_answer_d)..into(imageViewQuestionImageUrl);
            Picasso.Builder builder=new Picasso.Builder(getApplicationContext());
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    Log.e("error with pic",exception.getMessage());
                }
            });
            Picasso picasso= builder.build();
            Log.e("image",imageUrl);

            picasso.load(imageUrl).into(imageViewQuestionImageUrl, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                }
            });



        }
        //////////////////to handel multiple click///////////////////////////
        imageViewLetterA.setClickable(true);
        imageViewLetterA.setClickable(true);
        imageViewLetterB.setClickable(true);
        imageViewLetterC.setClickable(true);
        imageViewLetterD.setClickable(true);
        textViewAnswerA.setClickable(true);
        textViewAnswerB.setClickable(true);
        textViewAnswerC.setClickable(true);
        textViewAnswerD.setClickable(true);
        imageViewLeftArrow.setClickable(true);
        imageViewNextQuestion.setClickable(true);
        //pbar.setVisibility(View.GONE);

    }
    private void loadUIcolor(String question, String answerA, String answerB, String answerC, String answerD,String imageUrl){
       // loadPhoto.setVisibility(View.GONE);
        textViewQuestion.setText(question);
        Log.e("ansewer a",answerA);
        textViewAnswerA.setText(answerA);
        textViewAnswerB.setText(answerB);
        textViewAnswerC.setText(answerC);
        textViewAnswerD.setText(answerD);
        checkValueAnswerD(answerD);


        if (imageUrl.equals("null"))
        {
           // loadPhoto.setVisibility(View.GONE);
            Log.e("errorImage", "error");
            hideQuestionImageUrl();
        }
        else
        {
            Log.e("errorImage", imageUrl);
            unHideQuestionImageUrl();
            //Picasso.with(this).load(imageUrl).error(R.drawable.ic_green_answer_d)..into(imageViewQuestionImageUrl);
            Picasso.Builder builder=new Picasso.Builder(getApplicationContext());
            builder.listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    Log.e("error with pic",exception.getMessage());
                }
            });
            Picasso picasso= builder.build();
            Log.e("image",imageUrl);

            picasso.load(imageUrl).into(imageViewQuestionImageUrl, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                }
            });



        }
        //////////////////to handel multiple click///////////////////////////
        imageViewLetterA.setClickable(true);
        imageViewLetterA.setClickable(true);
        imageViewLetterB.setClickable(true);
        imageViewLetterC.setClickable(true);
        imageViewLetterD.setClickable(true);
        textViewAnswerA.setClickable(true);
        textViewAnswerB.setClickable(true);
        textViewAnswerC.setClickable(true);
        textViewAnswerD.setClickable(true);
        imageViewLeftArrow.setClickable(true);
        imageViewNextQuestion.setClickable(true);
        //pbar.setVisibility(View.GONE);

    }


    //draw green the constraintLayout background and the answer icon to show the correct answer
    private void showCorrectAnswer() {
        switch (correctAnswer) {
            case "A":
                constraintLayoutAnswerA.setBackgroundColor(Color.parseColor("#1D00C853"));
                imageViewLetterA.setImageResource(R.drawable.ic_green_answer_a);
                break;
            case "B":
                constraintLayoutAnswerB.setBackgroundColor(Color.parseColor("#1D00C853"));
                imageViewLetterB.setImageResource(R.drawable.ic_green_answer_b);
                break;
            case "C":
                constraintLayoutAnswerC.setBackgroundColor(Color.parseColor("#1D00C853"));
                imageViewLetterC.setImageResource(R.drawable.ic_green_answer_c);
                break;
            case "D":
                constraintLayoutAnswerD.setBackgroundColor(Color.parseColor("#1D00C853"));
                imageViewLetterD.setImageResource(R.drawable.ic_green_answer_d);
                break;
        }
    }

    //method called in order to update progressBar every time a new question is loaded
    private void updateQuizProgressBar()
    {
        if(progressStatus< quizList.size()) {
            progressStatus++;
            progressBarQuiz.setProgress(progressStatus);

            pbar.setVisibility(View.GONE);

        }
        else{
           // loadPhoto.setVisibility(View.GONE);
            //final View viewPos = findViewById(R.id.myCoordinatorLayout);
            disableMultipleClicks();
            allAnswersCompleted = true;
            if (num_correct_ans>=quizList.size()*80/100){
                //SharedPreferences.Editor editor=sharedPreferences.edit();
                // int i=levelNumber-1;
                // editor.putBoolean(String.valueOf(i),true);
                //editor.commit();
                Log.e("passed2",num_correct_ans+"-"+quizList.size());
               /* Snackbar snackbar = Snackbar.make(viewPos,"you pass this exam ", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(TestExam.this, R.color.redBright));
                snackbar.show();*/
                WrongAnswer(num_correct_ans);

            }else {
              /*  Snackbar snackbar = Snackbar.make(viewPos,"you didn't pass this exam", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(TestExam.this, R.color.redBright));
                snackbar.show();*/
                WrongAnswer(num_correct_ans);
            }
            imageViewNextQuestion.setVisibility(View.GONE);
            num_correct_ans=0;
            //   editor.putInt("level_position",-1);
            //   editor.putInt("questionNumber",-1);

        }
    }

    private void startAnimationNextQuestion()
    {
        shouldRepeatAnimation = true;
        AlphaAnimation fadeIn=new AlphaAnimation(0,1);
        AlphaAnimation fadeOut=new AlphaAnimation(1,0);
        final AnimationSet set = new AnimationSet(false);
        set.addAnimation(fadeIn);
        set.addAnimation(fadeOut);

        fadeOut.setStartOffset(1000);
        set.setDuration(2000);

        imageViewNextQuestion.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationRepeat(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                if (shouldRepeatAnimation) {
                    imageViewNextQuestion.startAnimation(set);
                }
            }
        });

    }

    private void stopAnimationNextQuestion()
    {
        shouldRepeatAnimation = false;
        imageViewNextQuestion.clearAnimation();
    }


    //loading default colors of both, constraintLayout background and icon number
    private void loadDefaultColors(){

        //set default colors every time user press Next
        constraintLayoutAnswerA.setBackground(OriginalBackgroundColor);
        constraintLayoutAnswerB.setBackground(OriginalBackgroundColor);
        constraintLayoutAnswerC.setBackground(OriginalBackgroundColor);
        constraintLayoutAnswerD.setBackground(OriginalBackgroundColor);
        imageViewLetterA.setImageResource(R.drawable.ic_white_answer_a);
        imageViewLetterB.setImageResource(R.drawable.ic_white_answer_b);
        imageViewLetterC.setImageResource(R.drawable.ic_white_answer_c);
        imageViewLetterD.setImageResource(R.drawable.ic_white_answer_d);
    }

    //disabling click event of answers after choosing one, prevents multiple clicks
    private void disableMultipleClicks(){

        imageViewLetterA.setClickable(false);
        imageViewLetterB.setClickable(false);
        imageViewLetterC.setClickable(false);
        imageViewLetterD.setClickable(false);
        textViewAnswerA.setClickable(false);
        textViewAnswerB.setClickable(false);
        textViewAnswerC.setClickable(false);
        textViewAnswerD.setClickable(false);
        constraintLayoutAnswerA.setClickable(false);
        constraintLayoutAnswerB.setClickable(false);
        constraintLayoutAnswerC.setClickable(false);
        constraintLayoutAnswerD.setClickable(false);


    }

    //enabling click events of answers after pressing in Next
    private void enableSingleClick(){

        imageViewLetterA.setClickable(true);
        imageViewLetterB.setClickable(true);
        imageViewLetterC.setClickable(true);
        imageViewLetterD.setClickable(true);
        textViewAnswerA.setClickable(true);
        textViewAnswerB.setClickable(true);
        textViewAnswerC.setClickable(true);
        textViewAnswerD.setClickable(true);
        constraintLayoutAnswerA.setClickable(true);
        constraintLayoutAnswerB.setClickable(true);
        constraintLayoutAnswerC.setClickable(true);
        constraintLayoutAnswerD.setClickable(true);
    }


    private void enableNextQuestionViewClick()
    {
        imageViewNextQuestion.setClickable(true);
    }

    private void disableNextQuestionViewClick()
    {
        imageViewNextQuestion.setClickable(false);
    }

    private void checkValueAnswerD(String answerD)
    {Log.e("D",answerD);
        if (answerD.equals("null"))
        {
            hideAnswer();
        }
        else
        {
            unHideAnswer();
        }
    }


    private void hideAnswer(){

        constraintLayoutAnswerD.setVisibility(View.GONE);
        imageViewLetterD.setVisibility(View.GONE);
        textViewAnswerD.setVisibility(View.GONE);
    }

    private void unHideAnswer()
    {
        constraintLayoutAnswerD.setVisibility(View.VISIBLE);
        imageViewLetterD.setVisibility(View.VISIBLE);
        textViewAnswerD.setVisibility(View.VISIBLE);
    }

    private void hideQuestionImageUrl()
    {
        imageViewQuestionImageUrl.setVisibility(View.GONE);
    }

    private void unHideQuestionImageUrl()
    {
        imageViewQuestionImageUrl.setVisibility(View.GONE);
    }

    private void hideNextQuestionView()
    {
        imageViewNextQuestion.setVisibility(View.GONE);
    }

    private void unHideNextQuestionView()
    {
        imageViewNextQuestion.setVisibility(View.VISIBLE);
    }



    @Override
    public void onBackPressed() {
        ////////////check internet connection /////////////////////////

        if(!allAnswersCompleted)
        {
            finish();
        }
        else
        {
            finish();
        }

        ///////////////////////////////////////////////////////////////

    }

    /* private void showDialog()
     {

         Rect displayRectangle = new Rect();
         Window window = PracticeQuizActivity.this.getWindow();
         window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
         final AlertDialog.Builder builder = new AlertDialog.Builder(PracticeQuizActivity.this,R.style.CustomAlertDialog);
         ViewGroup viewGroup = findViewById(android.R.id.content);
         dialogView2 = LayoutInflater.from(PracticeQuizActivity.this).inflate(R.layout.continue_level_dialog, viewGroup, false);
         builder.setView(dialogView2);
         alertDialog2 = builder.create();
         WindowManager.LayoutParams wm= new WindowManager.LayoutParams ();
         wm.copyFrom(alertDialog2.getWindow ().getAttributes ());
         wm.width=(int)(displayRectangle.width ()*0.9f);
         //wm.width=WindowManager.LayoutParams.MATCH_PARENT;
         wm.height=WindowManager.LayoutParams.WRAP_CONTENT;
         wm.gravity= Gravity.CENTER_HORIZONTAL;
         wm.dimAmount=0.60f;
         final CardView yes=dialogView2.findViewById(R.id.btnClickRetry);
         final CardView no=dialogView2.findViewById(R.id.btnClickOk);
         TextView text=dialogView2.findViewById(R.id.text);
         text.setText(getString(R.string.do_you_want_111) +" "+ String.valueOf(levelNumber) + "?");
         yes.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 yes.startAnimation(clickanim);
                 clickanim.setAnimationListener(new Animation.AnimationListener() {
                     @Override
                     public void onAnimationStart(Animation animation) {
                     }
                     @Override
                     public void onAnimationEnd(Animation animation) {
                         if (!allAnswersCompleted){
                             Log.e("position22",questionNumber+" "+sharedPreferences.getInt("questionNumber",0)+"position "+position);
                             //if (questionNumber>sharedPreferences.getInt("questionNumber",0)){
                             questionNumber-=1;
                             SharedPreferences.Editor editor=sharedPreferences.edit();
                             editor.putInt(levelNumber+"level_position",position);
                             editor.putInt(levelNumber+"questionNumber",questionNumber);
                             editor.commit();
                             Log.e("position3", String.valueOf(sharedPreferences.getInt("level_position",-1)));}
                         //}
                         alertDialog2.dismiss();
                         finish();
                     }
                     @Override
                     public void onAnimationRepeat(Animation animation) {
                     }
                 });


             }
         });
         no.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 no.startAnimation(clickanim);
                 clickanim.setAnimationListener(new Animation.AnimationListener() {
                     @Override
                     public void onAnimationStart(Animation animation) {

                     }

                     @Override
                     public void onAnimationEnd(Animation animation) {
                         alertDialog2.dismiss();

                     }

                     @Override
                     public void onAnimationRepeat(Animation animation) {

                     }
                 });

             }
         });

         alertDialog2.show();
         alertDialog2.getWindow ().setAttributes (wm);
         alertDialog2.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
         //  alertDialog2.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
         alertDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         alertDialog2.setCancelable(false);




     }
     private void noInternetDialog(){
         Rect displayRectangle = new Rect();
         Window window = PracticeQuizActivity.this.getWindow();
         window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
         final AlertDialog.Builder builder = new AlertDialog.Builder(PracticeQuizActivity.this,R.style.CustomAlertDialog);
         ViewGroup viewGroup = findViewById(android.R.id.content);
         dialogView = LayoutInflater.from(PracticeQuizActivity.this).inflate(R.layout.alert_dialog_no_connection, viewGroup, false);
         builder.setView(dialogView);
         alertDialog = builder.create();
         WindowManager.LayoutParams wm= new WindowManager.LayoutParams ();
         wm.copyFrom(alertDialog.getWindow ().getAttributes ());
         wm.width=(int)(displayRectangle.width ()*0.9f);
         //wm.width=WindowManager.LayoutParams.MATCH_PARENT;
         wm.height=WindowManager.LayoutParams.WRAP_CONTENT;
         wm.gravity= Gravity.CENTER_HORIZONTAL;
         wm.dimAmount=0.60f;
         final CardView retry=dialogView.findViewById(R.id.btnClickRetry);
         final CardView ok=dialogView.findViewById(R.id.btnClickOk);
         retry.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 retry.startAnimation(clickanim);

                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         if (InternetConnection.checkConnection(getApplicationContext())) {
                             alertDialog.dismiss();

                         }else {
                             startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                         }                            }
                 }, 260);



             }
         });
         ok.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ok.startAnimation(clickanim);
                 clickanim.setAnimationListener(new Animation.AnimationListener() {
                     @Override
                     public void onAnimationStart(Animation animation) {

                     }

                     @Override
                     public void onAnimationEnd(Animation animation) {

                         alertDialog.dismiss();
                         finishAffinity();
                     }

                     @Override
                     public void onAnimationRepeat(Animation animation) {

                     }
                 });


             }
         });
         alertDialog.show();
         alertDialog.getWindow ().setAttributes (wm);
         alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
         //  alertDialog2.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
         alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         alertDialog.setCancelable(false);
         alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
             @Override
             public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                 if (i == KeyEvent.KEYCODE_BACK) {
                     if (InternetConnection.checkConnection(getApplicationContext())) {
                         alertDialog.dismiss();
                     }else {finishAffinity();}
                     /*alertDialog.dismiss();
                     finishAffinity();
                     Log.e("pressed","true");}
                 }
                 return true;
             }
         });




     }*/
    private void load_next_Leavel(final String stateQuizName,String queryLanguage) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery(queryLanguage);
        //sorting object, ordering it by level number
        query.orderByAscending("level_number");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> result, ParseException e) {

                if (e == null) {
                    num_of_levels=result.size();
                    for (int i = 0; i < result.size(); i++) {
                        Practice practice = new Practice();
                        practice.level_number = result.get(i).getNumber("level_number");
                        practice.level_name = result.get(i).getString("level_name");
                        practice.level_color = result.get(i).getString("level_color");
                        //retrieving array of quiz using the state name
                        practice.quiz = result.get(i).getList(stateQuizName);

                        //send result data to adapter->recyclerView
                        practiceList.add(practice);
                    }
                    quizList=practiceList.get(position).getQuiz();
                    loadQuestionAndAnswers();
                    OriginalBackgroundColor = textViewQuestion.getBackground();
                    progressBarQuiz.setMax(quizList.size());
                    hideNextQuestionView();
                    updateQuizProgressBar();



                } else {
                    // something went wrong
                }


            }
        });

    }
    private void getQueryLanguage()
    {

        Locale.getDefault().getDisplayLanguage();
        if(Locale.getDefault().getLanguage().equals("en"))
        {
            queryLanguage="Practice_EN";
        }
        if(Locale.getDefault().getLanguage().equals("hi"))
        {
            queryLanguage="Practice_HI";
        }
        else
        {
            queryLanguage="Practice_EN";
        }
    }

    public void speak(View view) {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    if (textToSpeech.isSpeaking())
                        textToSpeech.stop();
                    if (question!=null)
                        textToSpeech.speak(question, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

    }


    /*  private void continuelevel(){
          Rect displayRectangle = new Rect();
          Window window = TestExam.this.getWindow();
          window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
          final AlertDialog.Builder builder = new AlertDialog.Builder(PracticeQuizActivity.this,R.style.CustomAlertDialog);
          ViewGroup viewGroup = findViewById(android.R.id.content);
          dialogView1 = LayoutInflater.from(PracticeQuizActivity.this).inflate(R.layout.continue_level_dialog, viewGroup, false);
          //  dialogView.setMinimumWidth((int)(displayRectangle.width()*1f ));
          //  dialogView.setMinimumHeight((int)(displayRectangle.height() * 0.7f));


          builder.setView(dialogView1);
          alertDialog1 = builder.create();

          WindowManager.LayoutParams wm= new WindowManager.LayoutParams ();
          wm.copyFrom(alertDialog1.getWindow ().getAttributes ());
          wm.width=(int)(displayRectangle.width ()*0.9f);
          //wm.width=WindowManager.LayoutParams.MATCH_PARENT;
          wm.height=WindowManager.LayoutParams.WRAP_CONTENT;
          wm.gravity= Gravity.CENTER_HORIZONTAL;
          wm.dimAmount=0.60f;
          final CardView yes=dialogView1.findViewById(R.id.btnClickRetry);
          final CardView no=dialogView1.findViewById(R.id.btnClickOk);
          yes.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  yes.startAnimation(clickanim);
                  clickanim.setAnimationListener(new Animation.AnimationListener() {
                      @Override
                      public void onAnimationStart(Animation animation) {

                      }

                      @Override
                      public void onAnimationEnd(Animation animation) {
                          pbar.setVisibility(View.VISIBLE);
                          yes.startAnimation(clickanim);
                          questionNumber=sharedPreferences.getInt(levelNumber+"questionNumber",0)-1;
                          progressStatus=questionNumber;
                          load_next_Leavel(stateName,queryLanguage);
                          alertDialog1.dismiss();
                      }

                      @Override
                      public void onAnimationRepeat(Animation animation) {

                      }
                  });


              }
          });
          no.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  no.startAnimation(clickanim);
                  clickanim.setAnimationListener(new Animation.AnimationListener() {
                      @Override
                      public void onAnimationStart(Animation animation) {

                      }

                      @Override
                      public void onAnimationEnd(Animation animation) {
                          no.startAnimation(clickanim);
                          //load_next_Leavel(stateName,queryLanguage);
                          alertDialog1.dismiss();
                      }

                      @Override
                      public void onAnimationRepeat(Animation animation) {

                      }
                  });


              }
          });




          alertDialog1.show();
          alertDialog1.getWindow ().setAttributes (wm);
          alertDialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
          //  alertDialog2.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
          alertDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          alertDialog1.setCancelable(false);

          alertDialog1.setOnKeyListener(new DialogInterface.OnKeyListener() {
              @Override
              public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {

                  return true;
              }
          });


      }*/
    private void WrongAnswer(int persentage) {
        int t=quizList.size();
        int pers=(persentage*100)/t;
        Rect displayRectangle = new Rect();
        Window window = QuizDetailsActivity.this.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(QuizDetailsActivity.this, R.style.CustomAlertDialog);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View contactUsView1 = LayoutInflater.from(QuizDetailsActivity.this).inflate(R.layout.worng_answer, viewGroup, false);

        builder.setView(contactUsView1);
        AlertDialog contactUsDialog1 = builder.create();

        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.copyFrom(contactUsDialog1.getWindow().getAttributes());
        wm.width = (int) (displayRectangle.width() * 0.9f);
        wm.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wm.gravity = Gravity.CENTER_HORIZONTAL;
        wm.dimAmount = 0.60f;
        TextView score=contactUsView1.findViewById(R.id.score);
        score.setText(pers+"%");
        Button exit=contactUsView1.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        contactUsDialog1.show();
        contactUsDialog1.getWindow().setAttributes(wm);
        contactUsDialog1.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        contactUsDialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        contactUsDialog1.setCancelable(false);
    }



}
