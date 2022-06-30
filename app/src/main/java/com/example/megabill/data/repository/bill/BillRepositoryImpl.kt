package com.example.megabill.data.repository.bill

import androidx.lifecycle.LiveData
import com.example.megabill.data.room.bill.BillDao
import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository
import javax.inject.Inject

class BillRepositoryImpl @Inject constructor(
    private val db : BillDao
) : BillRepository {

    override fun getBillList(): LiveData<MutableList<Bill>> {
        return db.getBillList()
    }

    override suspend fun getBillItem(itemId: Int): Bill {
        return db.getBillItem(itemId)
    }

    override suspend fun editBillItem(item: Bill) {
        db.addBillItem(item)
    }

    override suspend fun addBillItem(item: Bill) {
        db.addBillItem(item)
    }

    override suspend fun deleteBillItem(id: Int) {
        db.deleteBillItem(id)
    }

    override suspend fun deleteAllBillItem() {
        db.deleteAllBillItem()
    }
}