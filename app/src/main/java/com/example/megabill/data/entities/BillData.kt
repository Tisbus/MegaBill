package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill")
data class BillData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val idName : Int,
    val item: String,
    val price: Int
)