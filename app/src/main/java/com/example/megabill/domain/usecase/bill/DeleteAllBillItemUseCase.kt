package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.repository.bill.BillRepository

class DeleteAllBillItemUseCase(private val repository: BillRepository) {
    fun deleteAllBillItem(){
        repository.deleteAllBillItem()
    }
}