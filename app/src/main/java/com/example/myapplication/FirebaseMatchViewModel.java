package com.example.myapplication;


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class FirebaseMatchViewModel {

    private Note[] notes;
    private fireBaseModel fb;
    private String[] names;

    public FirebaseMatchViewModel() {
        fb = new fireBaseModel();
        fb.getMatchData();

    }


    public String[] getNames() {

            names = new String[100];
            for (int i = 0; i < notes.length - 1; i++) {
                names[i] = notes[i].getName();
            }
            return names;
        }

    }

