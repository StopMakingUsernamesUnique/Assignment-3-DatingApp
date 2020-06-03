package com.example.myapplication;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.Object.*;
import java.util.ArrayList;
import java.util.function.Consumer;

import static android.content.ContentValues.TAG;

class FirebaseMatchViewModel {


    private Note[] notes;
    private fireBaseModel fb;

    FirebaseMatchViewModel() {

        fb = new fireBaseModel();
    }

        void getData(Consumer<Note[]> responseCallback){
            fb.getMatchData(
                    (QuerySnapshot querySnapshot) -> {
                        if (querySnapshot != null) {
                            notes = new Note[querySnapshot.size()];
                            int i = 0;
                            for (DocumentSnapshot todoSnapshot : querySnapshot.getDocuments()) {
                                Note note = todoSnapshot.toObject(Note.class);
                                assert note != null;
                                note.uid = todoSnapshot.getId();
                                notes[i] = note;
                                i++;
                            }
                            responseCallback.accept(notes);
                        }
                    },
                    (databaseError -> System.out.println("Error reading Todo Items: " + databaseError))
            );
        }


    String[] getNames() {

        String[] names = new String[notes.length];
        for (int i = 0; i < notes.length; i++) {
            names[i] = notes[i].getName();
        }
        return names;
    }


     String[] getImageUrls() {

         String[] imageUrls = new String[notes.length];
        for (int i = 0; i < notes.length; i++) {
            imageUrls[i] = notes[i].getImageUrl();
        }
        return imageUrls;
    }

    Boolean[] getLikes() {

        Boolean[] likes = new Boolean[notes.length];
        for (int i = 0; i < notes.length; i++) {
            likes[i] = notes[i].getLiked();
        }
        return likes;
    }


    String[] getUid() {
        String[] UID = new String[notes.length];
        for (int i = 0; i < notes.length; i++) {
            UID[i] = notes[i].getUid();
        }
        return UID;
    }

    void like(String s){
        fb.like(s);

    }

}


