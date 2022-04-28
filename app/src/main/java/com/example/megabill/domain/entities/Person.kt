package com.example.megabill.domain.entities


data class Person(
    val name: String,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}


