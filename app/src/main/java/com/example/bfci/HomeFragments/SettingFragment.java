package com.example.bfci.HomeFragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bfci.Activity.SubJectActivity;
import com.example.bfci.EnterApp.HomePageActivity;
import com.example.bfci.EnterApp.Login;
import com.example.bfci.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_setting, container, false);
        LinearLayout matrial=view.findViewById(R.id.matrial);
        matrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SubJectActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout semester=view.findViewById(R.id.semester);
        semester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        TextView logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finishAffinity();
                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);

            }
        });
        LinearLayout contact=view.findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),getActivity().getClass());
                intent.putExtra("position",2);
                startActivity(intent);
            }
        });
        return view;

    }

}
