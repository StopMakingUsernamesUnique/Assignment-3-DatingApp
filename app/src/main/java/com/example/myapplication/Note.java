package com.example.myapplication;

public class Note {

    String imageUrl, name, uid, lat, longitude;
    Boolean liked;

    public Note() {
        //empty constructor
    }


    public Note( String imageUrl, Boolean liked, String name, String uid, String lat, String longitude){
        this.imageUrl = imageUrl;
        this.name = name;
        this.liked = liked;
        this.uid = uid;
        this.lat = lat;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }
    public String getImageUrl(){ return imageUrl;}
    public Boolean getLiked(){return liked;}
    public String getLat(){return lat;}
    public String getLong(){return longitude;}
    public String getUid(){return uid;}
}
