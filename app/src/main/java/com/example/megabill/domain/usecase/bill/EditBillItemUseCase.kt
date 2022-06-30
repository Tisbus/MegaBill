package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository
import javax.inject.Inject

class EditBillItemUseCase @Inject constructor(private val repository: BillRepository) {
    suspend fun editBillItem(item : Bill){
        repository.editBillItem(item)
    }
}