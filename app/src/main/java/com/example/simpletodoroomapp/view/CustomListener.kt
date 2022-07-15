package com.example.simpletodoroomapp.view

import com.example.simpletodoroomapp.model.User

interface CustomListener {
    fun itemClick(user: User)
    fun deleteitemClick(user: User)
    fun edititemClick(user: User)
}