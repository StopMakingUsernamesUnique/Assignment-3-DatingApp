package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class fireBaseModel {

    private FirebaseFirestore db;
    private Note[] note;
    private CollectionReference matchRef;

    public fireBaseModel() {
            db = FirebaseFirestore.getInstance();
            matchRef = db.collection("matchData2");

        }

        public void getMatchData(final onReceivedListener onReceivedListener) {
            matchRef.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                note = new Note[task.getResult().size()];
                                int i = 0;
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d(TAG, document.getId() + " => " + document.getData());
                                    note[i] = document.toObject(Note.class);
                                    Log.v(TAG, "DocumentSnapshot data: " + note[i].getName());
                                    i++;
                                }
                                onReceivedListener.onReceived(note);
                                
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }

                        }
                    });


    }


}
