package com.example.megabill.domain.usecase

import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.BillRepository

class GetPersonItemUseCase(private val repository: BillRepository) {
    fun getPersonItem(item : Int) : Person{
        return repository.getPersonItem(item)
    }
}