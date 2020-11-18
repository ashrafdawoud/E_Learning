package com.example.bfci.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfci.R;
import com.example.bfci.SubjectDetails.QuizDetailsActivity;

import java.util.ArrayList;

public class NotifyAdappter extends RecyclerView.Adapter<NotifyAdappter.ViewHolder> {
    Context context;
    ArrayList<String>noti=new ArrayList<>();

    public NotifyAdappter(Context context, ArrayList<String>noti) {

        this.context = context;
        this.noti=noti;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.notify_row,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notitext.setText(noti.get(position));
    }
    @Override
    public int getItemCount() {
        return noti.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView notitext;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notitext=itemView.findViewById(R.id.text22);

        }
    }
}
