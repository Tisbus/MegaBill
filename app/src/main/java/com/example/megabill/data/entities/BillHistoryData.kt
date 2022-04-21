package com.example.megabill.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.megabill.domain.entities.Bill

@Entity(tableName = "bill_history")
data class BillHistoryData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val data: String,
    val partyName: String,
    val totalSum: Int,
/*    val listBill: List<Bill>*/
)
