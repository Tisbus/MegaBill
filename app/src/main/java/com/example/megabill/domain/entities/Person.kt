package com.example.megabill.domain.entities

data class Person(
    private var id: Int = UNDEFINED_ID,
    private val name: String
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}


