package com.moviesuggestions.manojkumar.moviesuggestions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainAcitivityTabs extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first);
        Toast.makeText(getApplicationContext(), "This is the activity", Toast.LENGTH_LONG).show();
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        Context context;

        public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context =  context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentHis();
                case 1:
                    return new FragmentHers();
                default:
                    return null;

            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "HIS";
                case 1:
                    return "HER";
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }



}
