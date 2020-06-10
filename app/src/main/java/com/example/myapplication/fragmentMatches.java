package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.function.Consumer;

import static androidx.core.app.ActivityCompat.recreate;

public class fragmentMatches extends Fragment {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    SwipeRefreshLayout sw;
    Context context;

    String s1[], s3[], s4[], s5[], b1[], b3[], b4[], b5[];
    Double arr[];
    Boolean s2[], b2[];
    String images[], images2[];
    private FirebaseMatchViewModel viewModel;
    LocationManager locationManager;
    double longitudeNetwork, latitudeNetwork;
    Boolean NetworkUpdates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_matches, container, false);
        viewModel = new FirebaseMatchViewModel();
        NetworkUpdates = false;

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        EnableNetworkUpdates(view);
        context = this.getActivity();

        recyclerView = view.findViewById(R.id.RecyclerView);


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

    public void arrayTrimmer(String s1[], Boolean s2[], String s3[], String s4[], String s5[], String images[], Double[] Arr) {
        Location l = new Location("");
        l.setLongitude(Arr[0]);
        l.setLatitude(Arr[1]);
        int z = 0;
        for (int i = 0; i < images.length; i++) {
            Location d = new Location("");
            d.setLongitude(Double.parseDouble(s5[i]));
            d.setLatitude(Double.parseDouble(s4[i]));
            if (l.distanceTo(d) > 16093.4) {
                for (int e = i; e < images.length - 1; ) {
                    s1[e] = s1[e + 1];
                    s2[e] = s2[e + 1];
                    s3[e] = s3[e + 1];
                    s4[e] = s4[e + 1];
                    s5[e] = s5[e + 1];
                    images[e] = images[e + 1];
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

        for (int i = 0; i < b1.length; i++) {
            b1[i] = s1[i];
            b2[i] = s2[i];
            b3[i] = s3[i];
            b4[i] = s4[i];
            b5[i] = s5[i];
            images2[i] = images[i];
        }

    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton("Cancel", (paramDialogInterface, paramInt) -> {
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void EnableNetworkUpdates(View view) {

        if (!checkLocation()) {
            return;
        }
        if (NetworkUpdates) {
            locationManager.removeUpdates(locationListenerGps);
            Toast.makeText(this.getActivity(), "Network provider Stopped", Toast.LENGTH_LONG).show();
            NetworkUpdates = false;

        } else {
            if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGps);
            Toast.makeText(this.getActivity(), "Network provider started running", Toast.LENGTH_LONG).show();
            NetworkUpdates = true;
        }
    }

    private final LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();


            viewModel.getData((Note[] notes) -> {
                s1 = viewModel.getNames();
                s2 = viewModel.getLikes();
                s3 = viewModel.getUid();
                s4 = viewModel.getLat();
                s5 = viewModel.getLong();
                images = viewModel.getImageUrls();
                arr = new Double[]{longitudeNetwork, latitudeNetwork};
                arrayTrimmer(s1, s2, s3, s4, s5, images, arr);

                myAdapter = new MyAdapter(context, b1, b2, images2, b3, b4, b5);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                myAdapter.notifyDataSetChanged();

            });


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}