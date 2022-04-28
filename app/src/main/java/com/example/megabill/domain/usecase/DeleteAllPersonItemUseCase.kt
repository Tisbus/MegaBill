package com.example.megabill.domain.usecase

import com.example.megabill.domain.repository.BillRepository

class DeleteAllPersonItemUseCase(private val repository: BillRepository) {
    fun deleteAllPersonItem(){
        repository.deleteAllPersonItem()
    }
}