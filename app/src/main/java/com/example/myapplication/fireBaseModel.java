package com.example.myapplication;

import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class fireBaseModel {

    private FirebaseFirestore db;
    private Note[] note;
    private CollectionReference matchRef;

    public fireBaseModel() {
            db = FirebaseFirestore.getInstance();
            matchRef = db.collection("match");

        }

        public Note[] getMatchData() {
            matchRef.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                note = new Note[task.getResult().size()];
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Gson gson = new Gson();
                                    JsonElement jsonElement = gson.toJsonTree(document.getData());
                                    note[i] = gson.fromJson(jsonElement, Note.class);
                                    Log.d(TAG, "DocumentSnapshot data: " + note[i]);
                                }

                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
            return note;

    }


}
