package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonData(
    @PrimaryKey(autoGenerate = false)
    var id: Int = UNDEFINED_ID,
    val name: String
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}