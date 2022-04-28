package com.example.megabill.domain.usecase

import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.BillRepository

class AddPersonItemUseCase(private val repository: BillRepository) {
    fun addPersonItem(item : Person){
        repository.addPersonItem(item)
    }
}