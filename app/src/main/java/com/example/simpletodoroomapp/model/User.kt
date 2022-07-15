package com.example.simpletodoroomapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id:Int,

    @ColumnInfo(name = "user_name")
    var name:String,
    @ColumnInfo(name = "user_email")
    var email:String,

    @ColumnInfo(name = "user_comment")
    var comment:String

):Serializable