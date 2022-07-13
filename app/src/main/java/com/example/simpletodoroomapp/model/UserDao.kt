package com.example.simpletodoroomapp.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun  deleteUser(user: User)

    @Query(value = "delete from user_table")
    suspend fun  deleteAllUsers()


    @Query(value ="select * from user_table" )
    fun getAllUsers():LiveData<List<User>>



}