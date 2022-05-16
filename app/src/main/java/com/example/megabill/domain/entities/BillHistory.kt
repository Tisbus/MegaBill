package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_history")
data class BillHistory(
    val data: String,
    val partyName: String,
    val itemProduct: List<Total>,
    val totalSum: String,
    val totalSumWithTip: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = UNDEFINED_ID
) {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}