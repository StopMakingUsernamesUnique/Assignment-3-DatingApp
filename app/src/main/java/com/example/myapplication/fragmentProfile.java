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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);


        checkValues activity = (checkValues) getActivity();
        Bundle bundle = activity.getMyData();
        String a = bundle.getString(getString(R.string.Name));
        String b = bundle.getString(getString(R.string.Age));
        String c = bundle.getString(getString(R.string.Bio));
        String d = bundle.getString(getString(R.string.Work));


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
