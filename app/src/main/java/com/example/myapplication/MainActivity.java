package com.example.myapplication;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void toCheckValues(View view) throws ParseException {

        EditText Bio = (EditText) findViewById(R.id.Bio);
        EditText Name = (EditText) findViewById(R.id.Name);
        EditText Age = (EditText) findViewById(R.id.Age);
        EditText Work = (EditText) findViewById(R.id.Work);

        if((Bio.getText().toString().isEmpty())){
            Bio.setError(getString(R.string.Empty));
        }
        if((Name.getText().toString().isEmpty())){
            Name.setError(getString(R.string.Empty));
        }
        if((Age.getText().toString().isEmpty())){
            Age.setError(getString(R.string.Empty));
        }
        if((Work.getText().toString().isEmpty())){
            Bio.setError(getString(R.string.Empty));
        }

        if( !(Bio.getText().toString().isEmpty()) && !(Name.getText().toString().isEmpty()) && !(Work.getText().toString().isEmpty()) && !(Age.getText().toString().isEmpty())) {

            Bundle extras = new Bundle();

            Intent intent = new Intent(MainActivity.this, checkValues.class);

            String a = Name.getText().toString();
            String b = Bio.getText().toString();
            String c = Age.getText().toString();
            String d = Work.getText().toString();
            extras.putString(getString(R.string.Name), a);
            extras.putString(getString(R.string.Bio), b);
            extras.putString(getString(R.string.Age), c);
            extras.putString(getString(R.string.Work), d);

            intent.putExtras(extras);

            Bio.setText("");
            Name.setText("");
            Age.setText("");
            Work.setText("");


            startActivity(intent);

        }

    }


}

