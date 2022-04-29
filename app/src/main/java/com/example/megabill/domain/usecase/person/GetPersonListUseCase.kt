package com.example.megabill.domain.usecase.person

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository

class GetPersonListUseCase(private val repository: PersonRepository) {
    fun getPersonList() : LiveData<MutableList<Person>>{
        return repository.getPersonList()
    }
}