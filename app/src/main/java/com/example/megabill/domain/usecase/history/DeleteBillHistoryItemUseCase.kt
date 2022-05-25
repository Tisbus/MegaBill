package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.repository.history.HistoryRepository

class DeleteBillHistoryItemUseCase(private val repository: HistoryRepository) {
    fun deleteBillHistoryItem(itemId : Int){
        repository.deleteBillHistoryItem(itemId)
    }
}