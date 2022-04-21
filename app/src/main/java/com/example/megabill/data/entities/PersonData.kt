package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val name: String
)