package com.example.megabill.domain.usecase.person

import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository

class GetPersonItemUseCase(private val repository: PersonRepository) {
    fun getPersonItem(item : Int) : Person{
        return repository.getPersonItem(item)
    }
}