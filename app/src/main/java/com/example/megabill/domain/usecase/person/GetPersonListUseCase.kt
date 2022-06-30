package com.example.megabill.domain.usecase.person

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository
import javax.inject.Inject

class GetPersonListUseCase @Inject constructor(private val repository: PersonRepository) {
    fun getPersonList() : LiveData<MutableList<Person>>{
        return repository.getPersonList()
    }
}