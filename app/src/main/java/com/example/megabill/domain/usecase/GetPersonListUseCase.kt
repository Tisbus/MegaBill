package com.example.megabill.domain.usecase

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.BillRepository

class GetPersonListUseCase(private val repository: BillRepository) {
    fun getPersonList() : LiveData<MutableList<Person>>{
        return repository.getPersonList()
    }
}