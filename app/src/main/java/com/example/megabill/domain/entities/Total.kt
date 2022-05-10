package com.example.megabill.domain.entities

data class Total(
    val namePerson : String,
    val listBuyProduct : String,
    val totalSumToPerson : Int,
    val sumTips : Int,
    val totalSumWithTips : Int
)