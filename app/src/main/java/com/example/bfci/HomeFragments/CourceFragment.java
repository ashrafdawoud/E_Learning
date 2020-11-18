package com.example.bfci.HomeFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bfci.Activity.AnnouncementActivity;
import com.example.bfci.Activity.SubJectActivity;
import com.example.bfci.Adapters.SubjectAdapter;
import com.example.bfci.EnterApp.Login;
import com.example.bfci.EnterApp.Signup_Activity;
import com.example.bfci.R;
import com.example.bfci.SubjectDetails.AskedQuestionActivity;
import com.example.bfci.SubjectDetails.QuestionBankActivity;


public class CourceFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cource, container, false);
        LinearLayout subject =view.findViewById(R.id.subject);
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SubJectActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout announce =view.findViewById(R.id.announce);
        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AnnouncementActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout questionbank =view.findViewById(R.id.questionbank);
        questionbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), QuestionBankActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout asked =view.findViewById(R.id.asked_q);
        asked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AskedQuestionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }




}
