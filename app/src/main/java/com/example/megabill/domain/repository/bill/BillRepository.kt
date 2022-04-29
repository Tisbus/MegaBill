package com.example.megabill.domain.repository.bill

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Bill

interface BillRepository {
    fun getBillList() : LiveData<MutableList<Bill>>
    fun getBillItem(itemId : Int) : Bill
    fun addBillItem(item : Bill)
    fun editBillItem(item : Bill)
    fun deleteBillItem(id : Int)
    fun deleteAllBillItem()
}