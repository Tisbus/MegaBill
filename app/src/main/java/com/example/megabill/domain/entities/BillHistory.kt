package com.example.megabill.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BillHistory(
    @PrimaryKey
    val id : Int = UNDEFINED_ID,
    val data : String,
    val partyName : String,
    val totalSum : Int,
/*    val listBill : List<Bill>*/
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}