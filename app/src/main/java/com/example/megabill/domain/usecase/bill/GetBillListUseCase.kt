package com.example.megabill.domain.usecase.bill

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository

class GetBillListUseCase(private val repository: BillRepository) {
    fun getBillList() : LiveData<MutableList<Bill>>{
        return repository.getBillList()
    }
}