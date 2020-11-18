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
import com.example.bfci.SubjectDetails.Lecture_Ditails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.ViewHolder> {
    Context context;
    ArrayList<String> matrialName=new ArrayList<>();
    ArrayList<String> matrialimage=new ArrayList<>();
    ArrayList<String> lecture_link=new ArrayList<>();
    ArrayList<String> vedio_link=new ArrayList<>();
    ArrayList<String> assigment_link=new ArrayList<>();
    public LectureAdapter(Context context,ArrayList<String> matrialimage,ArrayList<String> assigment_link,ArrayList<String> vedio_link,ArrayList<String> lecture_link){
        this.context = context;
        this.matrialimage=matrialimage;
        this.assigment_link=assigment_link;
        this.lecture_link=lecture_link;
        this.vedio_link=vedio_link;

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
        final int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Lecture_Ditails.class);
                intent.putExtra("assigment_link",assigment_link.get(finalPosition));
                intent.putExtra("lecture_link",lecture_link.get(finalPosition));
                intent.putExtra("vedio_link",vedio_link.get(finalPosition));
                context.startActivity(intent);
            }
        });
        Picasso.get().load(matrialimage.get(position)).into(holder.imageView);
        position+=1;
        holder.textView.setText("Weak number "+position);

    }

    @Override
    public int getItemCount() {
        return matrialimage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item2;
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item2=itemView.findViewById(R.id.item);
            imageView=itemView.findViewById(R.id.image_weak);
            textView=itemView.findViewById(R.id.weakName);
        }
    }
}
