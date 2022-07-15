package com.example.simpletodoroomapp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.simpletodoroomapp.model.User


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun  deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun  deleteAllUsers()


    @Query("SELECT * FROM user_table" )
    fun getAllUsers():LiveData<List<User>>



}