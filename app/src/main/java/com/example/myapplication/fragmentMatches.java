package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentMatches extends Fragment {
    private static final String TAG = "fragmentMatches";
    RecyclerView recyclerView;

    String s1[];
    Boolean s2[];
    String images[];
    private FirebaseMatchViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_matches, container, false);
        viewModel = new FirebaseMatchViewModel();

        viewModel.getData((Note[] notes) -> {
            s1 = viewModel.getNames();
            s2 = viewModel.getLikes();
            images = viewModel.getImageUrls();
            recyclerView = view.findViewById(R.id.RecyclerView);
            MyAdapter myAdapter = new MyAdapter(this.getActivity(), s1, s2, images);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        });







        return view;
    }
}