package com.example.megabill.domain.repository.history

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.BillHistory

interface HistoryRepository {
     fun getBillHistoryList() : LiveData<MutableList<BillHistory>>
     fun addBillHistoryItem(item : BillHistory)
     fun deleteBillHistoryItem(itemId : Int)
     fun deleteAllBillHistory()

}

