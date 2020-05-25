package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fragmentMatches extends Fragment {
    private static final String TAG = "fragmentMatches";
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.index, R.drawable.index2, R.drawable.index3, R.drawable.index4};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);



        s1 = getResources().getStringArray(R.array.Match_Names);
        s2 = getResources().getStringArray(R.array.Match_Ages);
        recyclerView = view.findViewById(R.id.RecyclerView);
        MyAdapter myAdapter = new MyAdapter(this.getActivity(), s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return view;

    }}
