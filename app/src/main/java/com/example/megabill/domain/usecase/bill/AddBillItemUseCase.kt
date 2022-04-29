package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository

class AddBillItemUseCase(private val repository: BillRepository) {
    fun addBillItem(item : Bill){
        repository.addBillItem(item)
    }
}