package com.example.myapplication;

import android.location.Location;
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


    String s1[], s3[],s4[], s5[], b1[], b3[],  b4[], b5[];
    Double arr[];
    Boolean s2[], b2[];
    String images[], images2[];
    private FirebaseMatchViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_matches, container, false);
        viewModel = new FirebaseMatchViewModel();
        checkValues c = new checkValues();
        ((checkValues)getActivity()).EnableNetworkUpdates(view);


        recyclerView = view.findViewById(R.id.RecyclerView);

        c.getLocation((Double[] arr ) -> {
                    this.arr = arr;
                    viewModel.getData((Note[] notes) -> {
                        s1 = viewModel.getNames();
                        s2 = viewModel.getLikes();
                        s3 = viewModel.getUid();
                        s4 = viewModel.getLat();
                        s5 = viewModel.getLong();
                        images = viewModel.getImageUrls();
                        arrayTrimmer(s1,s2,s3,s4,s5, images, arr);

                        myAdapter = new MyAdapter(this.getActivity(), b1, b2, images2, b3, b4, b5);
                        recyclerView.setAdapter(myAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

                    });
                });




        sw = view.findViewById(R.id.sw);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAdapter = new MyAdapter(view.getContext(), b1, b2, images2, b3, b4, b5);
                myAdapter.notifyDataSetChanged();

            }
        });







        return view;
                }

                public void arrayTrimmer(String s1[],Boolean s2[], String s3[], String s4[], String s5[], String images[], Double[] Arr){
                    Location l = new Location("");
                    l.setLongitude(Arr[0]);
                    l.setLatitude(Arr[1]);
                    int z = 0;
                    for(int i = 0; i < images.length; i++){
                        Location d = new Location("");
                        d.setLongitude(Double.parseDouble(s5[i]));
                        d.setLatitude(Double.parseDouble(s4[i]));
                        if(l.distanceTo(d) > 10){
                            for(int e = i; e < images.length-1;) {
                                s1[e] = s1[e + 1];
                                s2[e] = s2[e + 1];
                                s3[e] = s3[e + 1];
                                s4[e] = s4[e + 1];
                                s5[e] = s5[e + 1];
                                images[e] = images[e+1];
                                e++;
                            }
                            z++;

                        }
                    }

                    b1 = new String[images.length - z];
                    b2 = new Boolean[images.length - z];
                    b3 = new String[images.length - z];
                    b4 = new String[images.length - z];
                    b5 = new String[images.length - z];
                    images2 = new String[images.length - z];

                    for(int i = 0; i < b1.length; i++){
                        b1[i] = s1[i];
                        b2[i] = s2[i];
                        b3[i] = s3[i];
                        b4[i] = s4[i];
                        b5[i] = s5[i];
                        images2[i] = images[i];
                    }

                }


                }