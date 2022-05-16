package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.repository.history.HistoryRepository

class DeleteBillHistoryItemUseCase(private val repository: HistoryRepository) {
    fun deleteBillHistoryItemUseCase(itemId : Int){
        repository.deleteBillHistoryItem(itemId)
    }
}