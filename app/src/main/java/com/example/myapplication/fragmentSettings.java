package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentSettings extends Fragment {
    private static final String TAG = "fragmentSettings";
    private FirebaseMatchViewModel viewModel;
    String[] names = new String[100];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView t = (TextView) view.findViewById(R.id.settings);

        viewModel = new FirebaseMatchViewModel();
        names = viewModel.getNames();
        t.setText(names[1]);




        return view;

    }
}
