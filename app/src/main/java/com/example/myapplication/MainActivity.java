package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



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
            Bio.setError("Empty Field");
        }
        if((Name.getText().toString().isEmpty())){
            Name.setError("Empty Field");
        }
        if((Age.getText().toString().isEmpty())){
            Age.setError("Empty Field");
        }
        if((Work.getText().toString().isEmpty())){
            Bio.setError("Empty Field");
        }

        if( !(Bio.getText().toString().isEmpty()) && !(Name.getText().toString().isEmpty()) && !(Work.getText().toString().isEmpty()) && !(Age.getText().toString().isEmpty())) {



            Intent intent = new Intent(MainActivity.this, checkValues.class);
            intent.putExtra(Constants.NAME, Name.getText().toString());
            intent.putExtra(Constants.BIO, Bio.getText().toString());
            intent.putExtra(Constants.AGE, Age.getText().toString());
            intent.putExtra(Constants.WORK, Work.getText().toString());

            Bio.setText("");
            Name.setText("");
            Age.setText("");
            Work.setText("");


            startActivity(intent);

        }

    }


}

