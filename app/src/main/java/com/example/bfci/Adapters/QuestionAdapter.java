package com.example.bfci.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
   ArrayList<String> question=new ArrayList<>();
    ArrayList<String> email=new ArrayList<>();
    ArrayList<String> date=new ArrayList<>();
    MyListener myListener;
    Context context;
    public QuestionAdapter(MyListener myListener, Context context, ArrayList<String> question, ArrayList<String> email, ArrayList<String> date) {
        this.question = question;
        this.email = email;
        this.date = date;
        this.context= context;
        this.myListener=myListener;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_row, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionAdapter.ViewHolder holder, final int position) {
        holder.text.setText(question.get(position));
        holder.date.setText(date.get(position));
        holder.email.setText(email.get(position));
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseQuery<ParseObject> query = ParseQuery.getQuery("QuestionBank");
                query.whereEqualTo("question", String.valueOf(question.get(position)));
                // Retrieve the object by id
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException eg) {
                        if (eg == null && objects.size() > 0) {
                            ParseObject Grillen;
                            for (int i=0;i<objects.size();i++){
                                if (objects.get(i).get("answer").equals(" ")){
                                    Grillen = objects.get(i);
                                    Grillen.put("answer", holder.answer.getText().toString());
                                    final int finalI = i;
                                    Grillen.saveInBackground(new SaveCallback() {
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                //success, saved!
                                                Log.d("MyApp", "Successfully saved!"+1);
                                                myListener.callback();
                                            } else {
                                                //fail to save!
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    break;
                                }
                            }

                        }else {
                            //fail to get!!
                            eg.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email,date,text;
        EditText answer;
        ImageView send;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.docname);
            date=itemView.findViewById(R.id.time);
            text=itemView.findViewById(R.id.text);
            answer=itemView.findViewById(R.id.answer);
            send=itemView.findViewById(R.id.send);
        }
    }
    public interface MyListener {
        // you can define any parameter as per your requirement
        public void callback();
    }
}
