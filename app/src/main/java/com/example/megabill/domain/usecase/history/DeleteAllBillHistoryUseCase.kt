package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.repository.history.HistoryRepository

class DeleteAllBillHistoryUseCase(private val repository: HistoryRepository) {
    fun deleteAllBillHistory(){
        repository.deleteAllBillHistory()
    }
}