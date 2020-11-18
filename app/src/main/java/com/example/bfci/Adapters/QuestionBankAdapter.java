package com.example.bfci.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfci.R;
import com.example.bfci.SubjectDetails.QuizDetailsActivity;

import java.util.ArrayList;

public class QuestionBankAdapter extends RecyclerView.Adapter<QuestionBankAdapter.ViewHolder> {
    ArrayList<String> question=new ArrayList<>();
    ArrayList<String> answer=new ArrayList<>();

    public QuestionBankAdapter(ArrayList<String> question, ArrayList<String> answer) {
        this.question = question;
        this.answer = answer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.question_bank_row,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.question.setText(question.get(position));
        holder.answer.setText(answer.get(position));

    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout answerContainer;
        LinearLayout showAnswer;
        TextView showansertext,question,answer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.questiontext);
            answer=itemView.findViewById(R.id.answer);
            answerContainer=itemView.findViewById(R.id.answerContainer);
            showAnswer=itemView.findViewById(R.id.showAnswer);
            showansertext=itemView.findViewById(R.id.showansertext);
            showAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (answerContainer.getVisibility()==View.GONE) {
                        answerContainer.setVisibility(View.VISIBLE);
                        showansertext.setText("Hide Answer ...");
                    }
                    else {
                        answerContainer.setVisibility(View.GONE);
                        showansertext.setText("how Answer ...");

                    }
                }
            });
        }
    }
}
