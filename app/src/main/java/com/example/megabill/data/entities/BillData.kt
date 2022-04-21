package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill")
class BillData(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val name: String,
    private val item: String,
    private val price: Int
)