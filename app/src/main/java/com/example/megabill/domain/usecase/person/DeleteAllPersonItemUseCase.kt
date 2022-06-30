package com.example.megabill.domain.usecase.person

import com.example.megabill.domain.repository.person.PersonRepository
import javax.inject.Inject

class DeleteAllPersonItemUseCase @Inject constructor(private val repository: PersonRepository) {
    suspend fun deleteAllPersonItem(){
        repository.deleteAllPersonItem()
    }
}