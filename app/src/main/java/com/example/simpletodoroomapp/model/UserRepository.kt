package com.example.simpletodoroomapp.model

class UserRepository(private val userDao: UserDao) {

    val users = userDao.getAllUsers()

    suspend fun insert(user:User){
        userDao.insertUser(user)
    }

    suspend fun delete(user:User){
        userDao.deleteUser(user)
    }

     suspend fun update(user:User){
        userDao.updateUser(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAllUsers()
    }


}