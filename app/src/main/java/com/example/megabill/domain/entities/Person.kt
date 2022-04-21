package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey
    var id: Int = UNDEFINED_ID,
    val name: String
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}


