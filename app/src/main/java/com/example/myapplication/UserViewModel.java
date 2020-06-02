package com.example.myapplication;

import com.example.myapplication.AppDatabase;
import com.example.myapplication.AppDatabaseSingleton;
import com.example.myapplication.User;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {


    public void update(Context context, User... User) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.userDao().insert(User);
        });
    }

    public void delete(Context context, User... User) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.userDao().delete(User);
        });
    }

    public LiveData<List<User>> loadAllByIds(Context context, String[] emailIds) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.userDao().loadAllByIds(emailIds);

    }
}
