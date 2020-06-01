package com.example.myapplication;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.UserDao;
import com.example.myapplication.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();


}
