package com.example.bfci.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfci.R;
import com.example.bfci.SubjectDetails.Lecture_Ditails;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.ViewHolder> {
    Context context;

    public LectureAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.lecture_row,parent,false);
       ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Lecture_Ditails.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }
    LinearLayout item2;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item2=itemView.findViewById(R.id.item);
        }
    }
}
