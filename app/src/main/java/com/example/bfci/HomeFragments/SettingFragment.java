package com.example.bfci.HomeFragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bfci.Activity.SubJectActivity;
import com.example.bfci.EnterApp.HomePageActivity;
import com.example.bfci.EnterApp.Login;
import com.example.bfci.R;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    View view;
    View dialogViewPlaystore;
    AlertDialog alertDialogPlaystore;
    TextView tttt;
    public SettingFragment() {
        // Required empty public constructor
    }

SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Parse.initialize(new Parse.Configuration.Builder(getActivity())
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();

        view= inflater.inflate(R.layout.fragment_setting, container, false);
        ///////////////////////////////////////////////////////////
        sharedPreferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        tttt=view.findViewById(R.id.tttt);
        if (sharedPreferences.getString("semester","").equals("first semester")){
            tttt.setText("First");
        }else {
            tttt.setText("Second");
        }

        Log.e("userid",sharedPreferences.getString("userId","")+"4");
        TextView emaildetails=view.findViewById(R.id.emaildetails);
        TextView username=view.findViewById(R.id.username);
        username.setText(sharedPreferences.getString("username",""));
        emaildetails.setText(sharedPreferences.getString("useremail",""));
        /////////////////////////////
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
                change_symester();
            }
        });
        LinearLayout changePass=view.findViewById(R.id.changePassword);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_password();
            }
        });
        TextView logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finishAffinity();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear().commit();
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
    public void change_password() {
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        dialogViewPlaystore = LayoutInflater.from(getActivity()).inflate(R.layout.change_password, viewGroup, false);
        builder.setView(dialogViewPlaystore);
        alertDialogPlaystore = builder.create();
        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.copyFrom(alertDialogPlaystore.getWindow().getAttributes());
        wm.width = (int) (displayRectangle.width() * 0.9f);
        //wm.width=WindowManager.LayoutParams.MATCH_PARENT;
        wm.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wm.gravity = Gravity.CENTER_HORIZONTAL;
        wm.dimAmount = 0.60f;
        Button change=dialogViewPlaystore.findViewById(R.id.change);
        EditText oldpass=dialogViewPlaystore.findViewById(R.id.oldpass);
        final EditText newpass=dialogViewPlaystore.findViewById(R.id.newpass);
        final String oladpasst=oldpass.getText().toString();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.getString("userpassword","15").equals(oladpasst)){
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
// Retrieve the object by id
                    query.getInBackground(sharedPreferences.getString("userId",""), new GetCallback<ParseObject>() {
                        public void done(ParseObject player, ParseException e) {
                            if (e == null) {
                                // Now let's update it with some new data. In this case, only cheatMode and score
                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                player.put("password", newpass.getText());
                                player.saveInBackground();
                            } else {
                                // Failed
                            }
                        }
                    });
                }
                alertDialogPlaystore.dismiss();
            }
        });

        alertDialogPlaystore.show();
        alertDialogPlaystore.getWindow().setAttributes(wm);

        alertDialogPlaystore.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alertDialogPlaystore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    public void change_symester() {
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
        ViewGroup viewGroup = view.findViewById(android.R.id.content);
        dialogViewPlaystore = LayoutInflater.from(getActivity()).inflate(R.layout.change_symester, viewGroup, false);
        builder.setView(dialogViewPlaystore);
        alertDialogPlaystore = builder.create();
        WindowManager.LayoutParams wm = new WindowManager.LayoutParams();
        wm.copyFrom(alertDialogPlaystore.getWindow().getAttributes());
        wm.width = (int) (displayRectangle.width() * 0.9f);
        //wm.width=WindowManager.LayoutParams.MATCH_PARENT;
        wm.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wm.gravity = Gravity.CENTER_HORIZONTAL;
        wm.dimAmount = 0.60f;
        Button change=dialogViewPlaystore.findViewById(R.id.change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup radioGroup=dialogViewPlaystore.findViewById(R.id.radiogroup);
                RadioButton radioButton=dialogViewPlaystore.findViewById(radioGroup.getCheckedRadioButtonId());
                Log.e("ffff",radioButton.getText().toString());
                String name=radioButton.getText().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                if (name.equals("First Semester")){

                    editor.putString("semester","first semester");
                    tttt.setText("First");
                }else {
                    editor.putString("semester","second semester");
                    tttt.setText("Second");

                }
                editor.commit();
                alertDialogPlaystore.dismiss();
            }
        });

        alertDialogPlaystore.show();
        alertDialogPlaystore.getWindow().setAttributes(wm);

        alertDialogPlaystore.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        alertDialogPlaystore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }



}
