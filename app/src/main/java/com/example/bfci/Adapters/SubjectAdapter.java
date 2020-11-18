package com.example.bfci.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bfci.R;
import com.example.bfci.SubjectDetails.MatrialDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    Context context;
    ArrayList<String> matrialName=new ArrayList<>();
    ArrayList<String> matrialimage=new ArrayList<>();
    public SubjectAdapter(Context context,ArrayList<String> matrialName,ArrayList<String> matrialimage) {
        this.context = context;
        this.matrialName=matrialName;
        this.matrialimage=matrialimage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.subject_row,parent,false);
        ViewHolder vh=new ViewHolder (view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text.setText(matrialName.get(position));
         Picasso.get().load(matrialimage.get(position)).into(holder.image);
        holder.subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MatrialDetails.class);
                intent.putExtra("matrial",matrialName.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return matrialName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        LinearLayout subject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.subject);
            image=itemView.findViewById(R.id.subject_image);
            text=itemView.findViewById(R.id.subject_name);
        }
    }
}
