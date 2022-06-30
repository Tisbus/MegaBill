package com.example.megabill.domain.usecase.person

import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository
import javax.inject.Inject

class AddPersonItemUseCase @Inject constructor(private val repository: PersonRepository) {
    suspend fun addPersonItem(item : Person){
        repository.addPersonItem(item)
    }
}