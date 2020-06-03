package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


import static android.content.ContentValues.TAG;
import static androidx.core.app.ActivityCompat.recreate;
import static java.nio.file.Paths.get;

public class fireBaseModel {

    private FirebaseFirestore db;
    private Note[] note;
    private CollectionReference matchRef;
    private DocumentReference doc;

    private List<ListenerRegistration> listeners;

    public fireBaseModel() {
            db = FirebaseFirestore.getInstance();
            matchRef = db.collection("matchData2");
             listeners = new ArrayList<>();
        }

        public void like(String name){
           doc = matchRef.document(name);
            doc.update("liked", true );

        }

    /*    public void getMatchData(final onReceivedListener onReceivedListener) {
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

                                    i++;
                                }
                                onReceivedListener.onReceived(note);
                                
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }

                        }
                    }); */

    public void getMatchData(androidx.core.util.Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = matchRef
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }

                    dataChangedCallback.accept(queryDocumentSnapshots);
                });

        listeners.add(listener);
    }


    }



