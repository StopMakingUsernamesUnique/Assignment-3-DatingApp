package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class checkValues extends AppCompatActivity {
    private SectionsPageAdapter SPA;
    private ViewPager VP;
    SwipeRefreshLayout sw;
    private UserViewModel userViewModel;
    EditText Distance;
    EditText Max;
    EditText Preference;
    EditText Gender;
    EditText Min;
    TextView DistanceT;
    TextView MaxT;
    TextView MinT;
    TextView PreferenceT;
    TextView GenderT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SPA = new SectionsPageAdapter(getSupportFragmentManager());
        VP = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(VP);

        TabLayout TB = (TabLayout) findViewById(R.id.tabs);
        TB.setupWithViewPager(VP);


        DistanceT = (TextView) findViewById(R.id.textView);
        MaxT = (TextView) findViewById(R.id.textView5);
        MinT = (TextView) findViewById(R.id.textView4);
         PreferenceT= (TextView) findViewById(R.id.textView3);
        GenderT = (TextView) findViewById(R.id.textView2);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        final Observer<List<User>> getUsersObserver = newUsers -> {
            if (newUsers == null || newUsers.size() <= 0) {
                return;
            }

            User user = newUsers.get(0);

            if (user == null) {
                return;
            }
            if(user.getMaxDistance() != null) {
                DistanceT = (TextView) findViewById(R.id.textView);
                MaxT = (TextView) findViewById(R.id.textView5);
                MinT = (TextView) findViewById(R.id.textView4);
                PreferenceT= (TextView) findViewById(R.id.textView3);
                GenderT = (TextView) findViewById(R.id.textView2);

                DistanceT.setText(user.getMaxDistance());
                MinT.setText(user.getLow());
                MaxT.setText(user.getHigh());
                PreferenceT.setText(user.getLookingFor());
                GenderT.setText(user.getGender());
            }
        };
        String[] s = {"1"};
        userViewModel.loadAllByIds(this, s).observe(this, getUsersObserver);




        }


    public void updateDatabase(View view){

        Distance = (EditText) findViewById(R.id.editText);
        Max = (EditText) findViewById(R.id.editText5);
        Min = (EditText) findViewById(R.id.editText4);
        Preference = (EditText) findViewById(R.id.editText7);
        Gender = (EditText) findViewById(R.id.editText3);

        User user = new User();
        if((Distance.getText().toString().isEmpty())){
            Distance.setError(getString(R.string.Empty));
        }
        if((Max.getText().toString().isEmpty())){
            Max.setError(getString(R.string.Empty));
        }
        if((Min.getText().toString().isEmpty())){
            Min.setError(getString(R.string.Empty));
        }
        if((Preference.getText().toString().isEmpty())){
            Preference.setError(getString(R.string.Empty));
        }
        if((Gender.getText().toString().isEmpty())){
            Gender.setError(getString(R.string.Empty));
        }

        if(!(Distance.getText().toString().isEmpty()) && !(Max.getText().toString().isEmpty()) && !(Min.getText().toString().isEmpty()) && !(Preference.getText().toString().isEmpty()) && !(Gender.getText().toString().isEmpty())){
            user.setGender(Gender.getText().toString());
            user.setID(1);
            user.setHigh(Max.getText().toString());
            user.setLow(Min.getText().toString());
            user.setLookingFor(Preference.getText().toString());
            user.setMaxDistance(Distance.getText().toString());

            DistanceT.setText(user.getMaxDistance());
            MinT.setText(user.getLow());
            MaxT.setText(user.getHigh());
            PreferenceT.setText(user.getLookingFor());
            GenderT.setText(user.getGender());

            userViewModel.update(this, user);

            Distance.setText("");
            Max.setText("");
            Min.setText("");
            Preference.setText("");
            Gender.setText("");

        }
    }


    public Bundle getMyData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        return bundle;
    }


    public void toMainActivity(View view) {

        finish();

    }



    private void setupViewPager(ViewPager viewpager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentProfile(), getString(R.string.Profile));
        adapter.addFragment(new fragmentSettings(), getString(R.string.Settings));
        adapter.addFragment(new fragmentMatches(), getString(R.string.Matches));
        viewpager.setAdapter(adapter);
    }




}

