package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.repository.history.HistoryRepository
import javax.inject.Inject

class DeleteAllBillHistoryUseCase @Inject constructor(private val repository: HistoryRepository) {
    fun deleteAllBillHistory(){
        repository.deleteAllBillHistory()
    }
}