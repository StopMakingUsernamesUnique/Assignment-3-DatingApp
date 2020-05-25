package com.example.myapplication;

import android.content.Context;
import android.widget.Button;

public class Note {

    String name, age, bio, work;

    public Note() {
        //empty constructor
    }


    public Note( String name, String age, String bio, String work){

        this.name = name;
        this.age = age;
        this.bio = bio;
        this.work = work;
    }

    public String getName() {
        return name;
    }
}
