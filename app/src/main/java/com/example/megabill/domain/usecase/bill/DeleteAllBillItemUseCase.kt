package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.repository.bill.BillRepository

class DeleteAllBillItemUseCase(private val repository: BillRepository) {
    suspend fun deleteAllBillItem(){
        repository.deleteAllBillItem()
    }
}