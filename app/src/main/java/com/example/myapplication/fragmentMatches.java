package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static androidx.core.app.ActivityCompat.recreate;

public class fragmentMatches extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    SwipeRefreshLayout sw;


    String s1[], s3[];
    Boolean s2[];
    String images[];
    private FirebaseMatchViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_matches, container, false);
        viewModel = new FirebaseMatchViewModel();


        recyclerView = view.findViewById(R.id.RecyclerView);

        viewModel.getData((Note[] notes) -> {
            s1 = viewModel.getNames();
            s2 = viewModel.getLikes();
            s3 = viewModel.getUid();
            images = viewModel.getImageUrls();
            myAdapter = new MyAdapter(this.getActivity(), s1, s2, images, s3);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        });




        sw = view.findViewById(R.id.sw);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


            }
        });







        return view;
                }


                }