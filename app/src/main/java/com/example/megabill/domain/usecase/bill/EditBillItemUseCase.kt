package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository

class EditBillItemUseCase(private val repository: BillRepository) {
    suspend fun editBillItem(item : Bill){
        repository.editBillItem(item)
    }
}