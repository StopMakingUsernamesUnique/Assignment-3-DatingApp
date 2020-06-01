package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {
    @PrimaryKey
    @NonNull
    private int ID;

    @ColumnInfo(name = "Max_Distance")
    private int MaxDistance;

    @ColumnInfo(name = "Gender")
    private String Gender;

    @ColumnInfo(name = "Age_Range_Low")
    private int low;

    @ColumnInfo(name = "Age_Range_High")
    private int high;

    @ColumnInfo(name = "Looking_For")
    private String LookingFor;


    public void setMaxDistance(int MaxDistance) {
        this.MaxDistance = MaxDistance;
    }

    public int getMaxDistance() {
        return MaxDistance;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getGender() {
        return Gender;
    }

    public void setAgeRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public String getAgeRange() {
        return low + "-" + high;
    }

    public void setLookingFor(String lookingFor) {
        this.LookingFor = lookingFor;

    }

    public String getLookingFor() {
        return LookingFor;
    }



}
