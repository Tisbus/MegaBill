package com.example.megabill.domain.usecase.history

import com.example.megabill.domain.repository.history.HistoryRepository
import javax.inject.Inject

class DeleteBillHistoryItemUseCase @Inject constructor(private val repository: HistoryRepository) {
    fun deleteBillHistoryItem(itemId : Int){
        repository.deleteBillHistoryItem(itemId)
    }
}