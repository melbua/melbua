package com.example.dailylook

import android.graphics.Bitmap
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    var userid: String,
    var password: String,
    var name: String,
    var birthdate: String,
    var sex: String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}