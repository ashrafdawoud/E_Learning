package com.example.bfci.Adapters;


import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bfci.HomeFragments.ChatFragment;
import com.example.bfci.HomeFragments.CourceFragment;
import com.example.bfci.HomeFragments.NotfyFragment;
import com.example.bfci.HomeFragments.SettingFragment;
import com.example.bfci.R;
import com.google.android.material.tabs.TabLayout;

public class HomeTabFAdapter extends FragmentPagerAdapter {
    TabLayout tab;
    boolean i=true;
    public HomeTabFAdapter(TabLayout tab,@NonNull FragmentManager fm) {
        super (fm);
        this.tab=tab;



    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(i){
            seticons ();
            i=false;
            Log.e ("err","______________");
        }

        switch (position){
            case 0 :
                //  tab.getTabAt (0).setIcon (R.drawable.ic_home_red_24dp);
                return new CourceFragment();
            case 1:
                //  tab.getTabAt (1).setIcon (R.drawable.ic_whatshot_red_24dp);
                return new NotfyFragment();
            case 2 :
                // tab.getTabAt (2).setIcon (R.drawable.ic_shopping_cart_red_24dp);
                return new ChatFragment ();
            case 3 :
                //   tab.getTabAt (3).setIcon (R.drawable.ic_account_box_red_24dp);
                return new SettingFragment();
            default:return new CourceFragment ();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
    //to desplay icons to tab layout
    public void seticons(){
        tab.getTabAt (0).setIcon (R.drawable.ic_home_black_24dp);
        tab.getTabAt (1).setIcon (R.drawable.ic_notifications_active_black_24dp);
        tab.getTabAt (2).setIcon (R.drawable.ic_chat_black_24dp);
        tab.getTabAt (3).setIcon (R.drawable.ic_account_circle_black_24dp);
        ////


        //text
        tab.getTabAt (0).setText ("Home");
        tab.getTabAt (1).setText ("notification");
        tab.getTabAt (2).setText ("chat");
        tab.getTabAt (3).setText ("Profile");



    }
    public void setPosition(int position){
        getItem (position);
    }
}

