package com.example.megabill.domain.usecase.total

import com.example.megabill.domain.repository.total.TotalRepository
import javax.inject.Inject

class DeleteAllTotalUseCase @Inject constructor(private val repository: TotalRepository) {
    suspend fun deleteAllTotal(){
        repository.deleteAllTotal()
    }
}