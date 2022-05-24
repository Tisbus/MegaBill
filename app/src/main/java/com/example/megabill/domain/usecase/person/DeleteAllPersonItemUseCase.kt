package com.example.megabill.domain.usecase.person

import com.example.megabill.domain.repository.person.PersonRepository

class DeleteAllPersonItemUseCase(private val repository: PersonRepository) {
    suspend fun deleteAllPersonItem(){
        repository.deleteAllPersonItem()
    }
}