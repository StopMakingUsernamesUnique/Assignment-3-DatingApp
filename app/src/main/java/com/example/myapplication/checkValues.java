package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class checkValues extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView Name = findViewById(R.id.Nameplate);
        TextView Bio = findViewById(R.id.Bioblock);
        TextView Age = findViewById(R.id.Ageplate);
        TextView Job = findViewById(R.id.Jobblock);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String a = bundle.getString("NAME");
        String b = bundle.getString("BIO");
        String c = bundle.getString("AGE");
        String d = bundle.getString("WORK");


        Name.setText(a);
        Bio.setText(b);
        Age.setText(c);
        Job.setText(d);



    }

    public void toMainActivity(View view) {

        finish();

    }

}

