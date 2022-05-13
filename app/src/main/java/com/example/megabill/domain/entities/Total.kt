package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total")
data class Total(
    val namePerson : String,
    val listBuyProduct : String,
    val totalSumToPerson : String,
    var sumTips : String,
    var totalSumWithTips : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}