package com.example.megabill.data.repository.history

import androidx.lifecycle.LiveData
import com.example.megabill.data.room.history.BillHistoryDao
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.repository.history.HistoryRepository
import javax.inject.Inject

class BillHistoryRepositoryImpl @Inject constructor(
    private val db : BillHistoryDao

) : HistoryRepository {

    override fun getBillHistoryList(): LiveData<MutableList<BillHistory>> = db.getBillHistoryList()

    override fun addBillHistoryItem(item: BillHistory) {
        db.addBillHistoryItem(item)
    }

    override fun deleteBillHistoryItem(itemId: Int) {
        db.deleteBillHistoryItem(itemId)
    }

    override fun deleteAllBillHistory() {
        db.deleteAllBillHistory()
    }

    override fun getBillHistoryItem(itemId: Int) : BillHistory{
       return  db.getBillHistoryItem(itemId)
    }
}