package com.example.myapplication;

public class Note {

    String imageUrl, name, uid, lat, longitude;

    Boolean liked;

    public Note() {
        //empty constructor
    }


    public Note(String imageUrl, String lat, Boolean liked, String longitude, String name, String uid) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.liked = liked;
        this.uid = uid;
        this.lat = lat;
        this.longitude = longitude;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLat() {
        return lat;
    }

    public Boolean getLiked() {
        return liked;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }
}