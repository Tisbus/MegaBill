package com.example.megabill.data.repository.bill

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.megabill.data.room.bill.BillDatabase
import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository

class BillRepositoryImpl(application: Application) : BillRepository {

    private val db = BillDatabase.getInstance(application).billDao()

    override fun getBillList(): LiveData<MutableList<Bill>> {
        return db.getBillList()
    }

    override fun getBillItem(itemId: Int): Bill {
        return db.getBillItem(itemId)
    }

    override fun editBillItem(item: Bill) {
        db.addBillItem(item)
    }

    override fun addBillItem(item: Bill) {
        db.addBillItem(item)
    }

    override fun deleteBillItem(id: Int) {
        db.deleteBillItem(id)
    }

    override fun deleteAllBillItem() {
        db.deleteAllBillItem()
    }
}