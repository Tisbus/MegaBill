package com.example.megabill.domain.usecase.total

import com.example.megabill.domain.repository.total.TotalRepository

class DeleteAllTotalUseCase(private val repository: TotalRepository) {
    suspend fun deleteAllTotal(){
        repository.deleteAllTotal()
    }
}