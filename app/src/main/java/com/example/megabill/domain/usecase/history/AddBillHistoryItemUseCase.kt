package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.repository.history.HistoryRepository

class AddBillHistoryItemUseCase(private val repository: HistoryRepository) {
    fun addBillHistoryItem(item : BillHistory){
        repository.addBillHistoryItem(item)
    }
}