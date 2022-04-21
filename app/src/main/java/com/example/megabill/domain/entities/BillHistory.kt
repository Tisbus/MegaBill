package com.example.megabill.domain.entities

data class BillHistory(
    private val id : Int = UNDEFINED_ID,
    private val data : String,
    private val partyName : String,
    private val totalSum : Int,
    private val listBill : List<Bill>
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}