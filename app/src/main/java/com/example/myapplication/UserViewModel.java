package com.example.myapplication;

import com.example.myapplication.AppDatabase;
import com.example.myapplication.AppDatabaseSingleton;
import com.example.myapplication.User;
import android.content.Context;

import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {


    public void insert(Context context, User... User) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.userDao().insert(User);
        });
    }
}
