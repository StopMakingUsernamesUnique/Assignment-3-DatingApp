package com.example.myapplication;


public class FirebaseMatchViewModel {

    private Note[] notes;
    private fireBaseModel fb;
    private String[] names;
    private String[] imageUrls;
    private Boolean[] likes;

    public FirebaseMatchViewModel() {
        fb = new fireBaseModel();
        fb.getMatchData(new onReceivedListener() {
            @Override
            public void onReceived(Note[] note) {
                notes = note;
            }
        });
    }


    public String[] getNames() {

        names = new String[notes.length];
        for (int i = 0; i < notes.length - 1; i++) {
            names[i] = notes[i].getName();
        }
        return names;
    }


    public String[] getImageUrls() {

        imageUrls = new String[notes.length];
        for (int i = 0; i < notes.length - 1; i++) {
            imageUrls[i] = notes[i].getImageUrl();
        }
        return imageUrls;
    }

    public Boolean[] getLikes() {

        likes = new Boolean[notes.length];
        for (int i = 0; i < notes.length - 1; i++) {
            likes[i] = notes[i].getLiked();
        }
        return likes;
    }



}


