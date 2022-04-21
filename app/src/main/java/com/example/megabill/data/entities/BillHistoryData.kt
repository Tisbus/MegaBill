package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.megabill.domain.entities.Bill

@Entity(tableName = "bill_history")
data class BillHistoryData(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val data: String,
    private val partyName: String,
    private val totalSum: Int,
    private val listBill: List<Bill>
)
