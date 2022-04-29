package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.repository.bill.BillRepository

class DeleteBillItemUseCase(private val repository: BillRepository) {
    fun deleteBillItem(itemId : Int){
        repository.deleteBillItem(itemId)
    }
}