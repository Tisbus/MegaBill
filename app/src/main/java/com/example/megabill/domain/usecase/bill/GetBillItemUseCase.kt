package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.repository.bill.BillRepository

class GetBillItemUseCase(private val repository: BillRepository) {
    fun getBillItem(itemId : Int){
        repository.getBillItem(itemId)
    }
}