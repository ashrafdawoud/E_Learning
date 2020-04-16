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
import com.example.bfci.SubjectDetails.MatrialDetails;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    Context context;

    public SubjectAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.subject_row,parent,false);
        ViewHolder vh=new ViewHolder (view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MatrialDetails.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout subject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.subject);
        }
    }
}
