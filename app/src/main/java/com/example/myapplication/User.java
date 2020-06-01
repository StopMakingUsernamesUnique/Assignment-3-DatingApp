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
    private String MaxDistance;

    @ColumnInfo(name = "Gender")
    private String Gender;

    @ColumnInfo(name = "Age_Range_Low")
    private String low;

    @ColumnInfo(name = "Age_Range_High")
    private String high;

    @ColumnInfo(name = "Looking_For")
    private String LookingFor;


    public void setMaxDistance(String MaxDistance) {
        this.MaxDistance = MaxDistance;
    }

    public String getMaxDistance() {
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

    public void setLow(String low) {
        this.low = low;

    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public String getHigh(){
        return high;
    }

    public void setLookingFor(String lookingFor) {
        this.LookingFor = lookingFor;

    }

    public String getLookingFor() {
        return LookingFor;
    }



}
