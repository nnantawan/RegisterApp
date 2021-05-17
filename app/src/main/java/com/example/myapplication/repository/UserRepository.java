package com.example.myapplication.repository;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.model.AppDatabase;
import com.example.myapplication.model.dao.UserDao;
import com.example.myapplication.model.entity.User;

import java.util.concurrent.CountDownLatch;

public class UserRepository {

    private UserDao mUserDao;
    private User mUser;

    public UserRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        mUserDao = db.userDao();

    }

    public User getUserLogin(String username, String password) {
        CountDownLatch latch = new CountDownLatch(1);

        AppDatabase.databaseWriteExecutor.submit(() -> {
            mUser = mUserDao.getUserLogin(username, password);
            System.out.println("mUser Get : " + mUser);

            latch.countDown();
        });

        // wait for the latch to be decremented by the two remaining threads
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("mUser Return : " + mUser);
        return mUser;


    }

    public void insert(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mUserDao.insertAll(user);
        });
    }
}
