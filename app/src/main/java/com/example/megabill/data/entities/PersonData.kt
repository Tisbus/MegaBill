package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)