package com.example.bfci.HomeFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bfci.Adapters.AnnounceAdapter;
import com.example.bfci.Adapters.LectureAdapter;
import com.example.bfci.Adapters.NotifyAdappter;
import com.example.bfci.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotfyFragment extends Fragment {

    ArrayList<String> notytext=new ArrayList<>();

    public NotfyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notfy, container, false);
        final RecyclerView lecturRecy=view.findViewById(R.id.notifyRow);
        lecturRecy.setHasFixedSize(true);
        lecturRecy.setLayoutManager(new LinearLayoutManager(getActivity()));

        Parse.initialize(new Parse.Configuration.Builder(getActivity())
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Notifications");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> player, ParseException e) {
                if (e == null) {
                    for (int i=0;i<player.size();i++){
                        notytext.add(player.get(i).getString("text"));
                        NotifyAdappter lectureAdapter=new NotifyAdappter(getActivity(),notytext);
                        lecturRecy.setAdapter(lectureAdapter);
                    }
                } else {
                    // Something is wrong
                    Log.e("return2", "true");
                }
            }
        });
        return view;
    }

}
