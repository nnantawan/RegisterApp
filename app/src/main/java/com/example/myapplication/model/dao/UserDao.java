package com.example.myapplication.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.entity.User;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);*/

    @Query("SELECT * FROM user WHERE username LIKE :username AND " +
            "password LIKE :password LIMIT 1")
    User getUserLogin(String username, String password);

    @Insert(onConflict = REPLACE)
    void insertAll(User users);

    @Delete
    void delete(User user);
}
