package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bill(
    @PrimaryKey
    var id : Int = UNDEFINED_ID,
    val name : String,
    val item : String,
    val price : Int
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}