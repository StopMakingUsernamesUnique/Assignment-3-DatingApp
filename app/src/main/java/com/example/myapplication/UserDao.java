package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE ID IN (:id)")
    LiveData<List<User>> loadAllByIds(String[] id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User[] user);

    @Delete
    void delete(User[] user);
}
