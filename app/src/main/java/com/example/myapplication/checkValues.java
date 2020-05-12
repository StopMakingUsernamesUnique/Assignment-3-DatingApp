package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class checkValues extends AppCompatActivity {
    private SectionsPageAdapter SPA;
    private ViewPager VP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SPA = new SectionsPageAdapter(getSupportFragmentManager());
        VP = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(VP);

        TabLayout TB = (TabLayout) findViewById(R.id.tabs);
        TB.setupWithViewPager(VP);
    }

    public void toMainActivity(View view) {

        finish();

    }

    private void setupViewPager(ViewPager viewpager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentProfile(), "Profile");
        adapter.addFragment(new fragmentSettings(), "Settings");
        adapter.addFragment(new fragmentMatches(), "Matches");
        viewpager.setAdapter(adapter);
    }




}

