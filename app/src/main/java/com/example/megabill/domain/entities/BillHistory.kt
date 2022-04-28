package com.example.megabill.domain.entities

data class BillHistory(
    val data : String,
    val partyName : String,
    val totalSum : Int,
    val id : Int = UNDEFINED_ID
/*    val listBill : List<Bill>*/
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}