package com.example.megabill.domain.entities

data class Bill(
    private var id : Int = UNDEFINED_ID,
    private val name : String,
    private val item : String,
    private val price : Int
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}