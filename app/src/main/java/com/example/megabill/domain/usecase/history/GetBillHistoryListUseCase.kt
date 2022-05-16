package com.example.megabill.domain.usecase.history

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.repository.history.HistoryRepository

class GetBillHistoryListUseCase(private val repository: HistoryRepository) {
    fun getListBillHistory() : LiveData<MutableList<BillHistory>>{
        return repository.getBillHistoryList()
    }
}