package com.example.megabill.domain.repository.history

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.BillHistory

interface HistoryRepository {
     fun getBillHistoryList() : LiveData<MutableList<BillHistory>>
     suspend fun getBillHistoryItem(itemId : Int) : BillHistory
     suspend fun addBillHistoryItem(item : BillHistory)
     suspend fun deleteBillHistoryItem(itemId : Int)
     suspend fun deleteAllBillHistory()

}

