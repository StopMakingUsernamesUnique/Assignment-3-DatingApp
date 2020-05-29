package com.example.myapplication;
import android.util.Log;

import java.lang.Object.*;
import java.util.function.Consumer;

import static android.content.ContentValues.TAG;

public class FirebaseMatchViewModel {


    private Note[] notes;
    private fireBaseModel fb;
    private String[] names;
    private String[] imageUrls;
    private Boolean[] likes;
    private String[] UID;

    public FirebaseMatchViewModel() {

        fb = new fireBaseModel();
    }

        public void getData(Consumer<Note[]> responseCallback){

            fb.getMatchData(note -> {

                notes = note;



                responseCallback.accept(notes);
                
            });
        }


    public String[] getNames() {

        names = new String[notes.length];
        for (int i = 0; i < notes.length; i++) {
            names[i] = notes[i].getName();
        }
        return names;
    }


    public String[] getImageUrls() {

        imageUrls = new String[notes.length];
        for (int i = 0; i < notes.length; i++) {
            imageUrls[i] = notes[i].getImageUrl();
        }
        return imageUrls;
    }

    public Boolean[] getLikes() {

        likes = new Boolean[notes.length];
        for (int i = 0; i < notes.length; i++) {
            likes[i] = notes[i].getLiked();
        }
        return likes;
    }


    public String[] getUid() {
        UID = new String[notes.length];
        for (int i = 0; i < notes.length; i++) {
            UID[i] = notes[i].getUid();
        }
        return UID;
    }

    public void like(String s){
        fb.like(s);

    }

}


