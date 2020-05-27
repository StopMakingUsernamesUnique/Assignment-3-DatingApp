package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentMatches extends Fragment {
    private static final String TAG = "fragmentMatches";
    RecyclerView recyclerView;
    String s1[];
    Boolean s2[];
    String images[];
    private FirebaseMatchViewModel viewModel = new FirebaseMatchViewModel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView textView = (TextView) view.findViewById(R.id.settings);
        textView.setText(s1[1]);
        images = viewModel.getImageUrls();
        s2 = viewModel.getLikes();
        s1 = viewModel.getNames();

     /*   recyclerView = view.findViewById(R.id.RecyclerView);
        MyAdapter myAdapter = new MyAdapter(this.getActivity(), s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
*/
        return view;

    }}
