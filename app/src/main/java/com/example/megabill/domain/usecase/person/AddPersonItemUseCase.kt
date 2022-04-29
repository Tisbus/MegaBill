package com.example.megabill.domain.usecase.person

import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository

class AddPersonItemUseCase(private val repository: PersonRepository) {
    fun addPersonItem(item : Person){
        repository.addPersonItem(item)
    }
}