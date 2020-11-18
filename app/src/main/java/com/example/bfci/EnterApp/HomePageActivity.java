package com.example.bfci.EnterApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.example.bfci.Adapters.HomeTabFAdapter;
import com.example.bfci.R;
import com.google.android.material.tabs.TabLayout;

public class HomePageActivity extends AppCompatActivity {
    TabLayout tab;
    ViewPager viewPager;
    HomeTabFAdapter homeTabFAdapter;
    Boolean startFromAnotherBoolean=false;

    View dialogView1;
    AlertDialog alertDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //جزء ال viewpager وال tablayout
        viewPager=findViewById (R.id.HomeViewPager);
        tab=findViewById (R.id.tab);
        homeTabFAdapter=new HomeTabFAdapter (tab,getSupportFragmentManager ());
        viewPager.setAdapter (homeTabFAdapter);
        ///////////////////////////////////
        //start tablayout code
        tab.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setSelectedTabIndicatorColor (Color.parseColor ("#192048"));
        tab.setTabTextColors (Color.parseColor ("#9DA0A8"),Color.parseColor ("#192048"));
        viewPager.setCurrentItem(0,true);
        tablayoutfunction ();

        //end of tablayout code

        //دا عشان لو احنا بدانا الactivity من غير ال intent هيعمل crash
        try {
            Bundle i=getIntent().getExtras();
            if (i!=null) {
                int position = (int) i.get("position");
                viewPager.setCurrentItem(position);
            }else {
                viewPager.setCurrentItem(0);
            }
            if (i!=null){startFromAnotherBoolean=true;}else {startFromAnotherBoolean=false;}




        }catch (Exception e){}
        /////////////////////////////////
        View view=findViewById(android.R.id.content);

//                 dialogSetting (view);


    }


    private void tablayoutfunction(){
        tab.setOnTabSelectedListener (new TabLayout.ViewPagerOnTabSelectedListener (viewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected (tab);
                int redcolor= ContextCompat.getColor (HomePageActivity.this,R.color.selected);
                tab.getIcon ().setColorFilter (redcolor, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected (tab);

                int redcolor= ContextCompat.getColor (HomePageActivity.this,R.color.unselected);
                tab.getIcon ().setColorFilter (redcolor, PorterDuff.Mode.SRC_IN);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume ( );
        if (startFromAnotherBoolean){
            viewPager.postDelayed (new Runnable ( ) {
                @Override
                public void run() {
                    viewPager.setCurrentItem (0);
                }
            },1);
        }}


}
