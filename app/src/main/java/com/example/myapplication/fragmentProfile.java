package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentProfile extends Fragment {
    private static final String TAG = "fragmentProfile";
    public static final String MY_SHARED_PREFERENCES = "MySharedPrefs" ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);


        checkValues activity = (checkValues) getActivity();
        Bundle bundle = activity.getMyData();
        String a = bundle.getString("NAME");
        String b = bundle.getString("AGE");
        String c = bundle.getString("BIO");
        String d = bundle.getString("WORK");


        TextView name = (TextView) view.findViewById(R.id.Nameplate);
        TextView age = (TextView) view.findViewById(R.id.Ageplate);
        TextView bio = (TextView) view.findViewById(R.id.Bioblock);
        TextView job = (TextView) view.findViewById(R.id.Jobblock);


        name.setText(a);
        age.setText(b);
        bio.setText(c);
        job.setText(d);

        return view;

    }
}
