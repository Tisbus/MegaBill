package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill")
data class Bill(
    val name : String,
    val idName : Int,
    val item : String,
    val price : Int,
    @PrimaryKey(autoGenerate = true)
    val id : Int = UNDEFINED_ID,
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}