package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.repository.history.HistoryRepository
import javax.inject.Inject

class AddBillHistoryItemUseCase @Inject constructor(private val repository: HistoryRepository) {
    fun addBillHistoryItem(item : BillHistory){
        repository.addBillHistoryItem(item)
    }
}