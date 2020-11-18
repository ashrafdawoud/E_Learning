package com.example.bfci.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfci.R;
import com.example.bfci.SubjectDetails.QuizDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    Context context;
    ArrayList<String> matrialimage=new ArrayList<>();
    ArrayList<String> quizNmae=new ArrayList<>();
    private List<Practice> practiceList;

    public QuizAdapter(List<Practice> practiceList,Context context, ArrayList<String>matrialimage, ArrayList<String>quizNmae) {
        this.context = context;
        this.quizNmae=quizNmae;
        this.matrialimage=matrialimage;
        this.practiceList = practiceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.quiz_row,parent,false);
        ViewHolder vh=new ViewHolder (view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(quizNmae.get(position));
        Picasso.get().load(matrialimage.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return quizNmae.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.quiz_image);
            textView=itemView.findViewById(R.id.quiz_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, QuizDetailsActivity.class);
                    Log.e("result__3", String.valueOf(practiceList.get(0).getQuiz()));
                    intent.putStringArrayListExtra("LEVEL_QUIZ", (ArrayList<String>) practiceList.get(0).getQuiz());
                    intent.putExtra("Name", practiceList.get(0).getLevel_name());
                    context.startActivity(intent);
                   // practiceList.clear();
                }
            });

        }
    }
}
