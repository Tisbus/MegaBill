package com.example.megabill.domain.entities

data class Bill(
    val name : String,
    val item : String,
    val price : Int,
    val idName : Int,
    var id : Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}