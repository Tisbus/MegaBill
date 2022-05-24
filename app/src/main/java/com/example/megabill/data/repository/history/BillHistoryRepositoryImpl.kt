package com.example.megabill.data.repository.history

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.megabill.data.room.history.BillHistoryDatabase
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.repository.history.HistoryRepository

class BillHistoryRepositoryImpl(application: Application) : HistoryRepository {

    private val db = BillHistoryDatabase.getInstance(application).billHistoryDao()

    override fun getBillHistoryList(): LiveData<MutableList<BillHistory>> = db.getBillHistoryList()

    override suspend fun addBillHistoryItem(item: BillHistory) {
        db.addBillHistoryItem(item)
    }

    override suspend fun deleteBillHistoryItem(itemId: Int) {
        db.deleteBillHistoryItem(itemId)
    }

    override suspend fun deleteAllBillHistory() {
        db.deleteAllBillHistory()
    }

    override suspend fun getBillHistoryItem(itemId: Int) : BillHistory{
       return  db.getBillHistoryItem(itemId)
    }
}