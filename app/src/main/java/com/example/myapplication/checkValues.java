package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
import java.util.function.Consumer;

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
    LocationManager locationManager;
    double longitudeNetwork, latitudeNetwork;
    Boolean NetworkUpdates;

    public void getLocation(Consumer<Double[]> response) {


        Double[] arr = {longitudeNetwork, latitudeNetwork };
        response.accept(arr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        NetworkUpdates = false;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        SPA = new SectionsPageAdapter(getSupportFragmentManager());
        VP = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(VP);

        TabLayout TB = (TabLayout) findViewById(R.id.tabs);
        TB.setupWithViewPager(VP);


        DistanceT = (TextView) findViewById(R.id.textView);
        MaxT = (TextView) findViewById(R.id.textView5);
        MinT = (TextView) findViewById(R.id.textView4);
        PreferenceT = (TextView) findViewById(R.id.textView3);
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
            if (user.getMaxDistance() != null) {
                DistanceT = (TextView) findViewById(R.id.textView);
                MaxT = (TextView) findViewById(R.id.textView5);
                MinT = (TextView) findViewById(R.id.textView4);
                PreferenceT = (TextView) findViewById(R.id.textView3);
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


    public void updateDatabase(View view) {

        Distance = (EditText) findViewById(R.id.editText);
        Max = (EditText) findViewById(R.id.editText5);
        Min = (EditText) findViewById(R.id.editText4);
        Preference = (EditText) findViewById(R.id.editText7);
        Gender = (EditText) findViewById(R.id.editText3);

        User user = new User();
        if ((Distance.getText().toString().isEmpty())) {
            Distance.setError(getString(R.string.Empty));
        }
        if ((Max.getText().toString().isEmpty())) {
            Max.setError(getString(R.string.Empty));
        }
        if ((Min.getText().toString().isEmpty())) {
            Min.setError(getString(R.string.Empty));
        }
        if ((Preference.getText().toString().isEmpty())) {
            Preference.setError(getString(R.string.Empty));
        }
        if ((Gender.getText().toString().isEmpty())) {
            Gender.setError(getString(R.string.Empty));
        }

        if (!(Distance.getText().toString().isEmpty()) && !(Max.getText().toString().isEmpty()) && !(Min.getText().toString().isEmpty()) && !(Preference.getText().toString().isEmpty()) && !(Gender.getText().toString().isEmpty())) {
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


    public Bundle getMyData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        return bundle;
    }


    public void toMainActivity(View view) {

        finish();

    }


    private void setupViewPager(ViewPager viewpager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragmentProfile(), getString(R.string.Profile));
        adapter.addFragment(new fragmentSettings(), getString(R.string.Settings));
        adapter.addFragment(new fragmentMatches(), getString(R.string.Matches));
        viewpager.setAdapter(adapter);
    }


    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
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
            locationManager.removeUpdates(locationListenerNetwork);
            Toast.makeText(this, "Network provider Stopped", Toast.LENGTH_LONG).show();
            NetworkUpdates = false;

        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 5 * 1000, 10, locationListenerNetwork);
            Toast.makeText(this, "Network provider started running", Toast.LENGTH_LONG).show();
            NetworkUpdates = true;
        }
    }

        private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            runOnUiThread(() ->

                    Toast.makeText(checkValues.this, "Location Changed", Toast.LENGTH_LONG).show());
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

