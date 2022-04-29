package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_history")
data class BillHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val data: String,
    val partyName: String,
    val totalSum: Int
)