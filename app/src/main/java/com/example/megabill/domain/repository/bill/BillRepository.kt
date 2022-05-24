package com.example.megabill.domain.repository.bill

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Bill

interface BillRepository {
    fun getBillList() : LiveData<MutableList<Bill>>
    suspend fun getBillItem(itemId : Int) : Bill
    suspend fun addBillItem(item : Bill)
    suspend fun editBillItem(item : Bill)
    suspend fun deleteBillItem(id : Int)
    suspend fun deleteAllBillItem()
}