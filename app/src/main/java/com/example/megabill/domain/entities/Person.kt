package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = false)
    var id: Int = UNDEFINED_ID,
    val name: String,
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}


