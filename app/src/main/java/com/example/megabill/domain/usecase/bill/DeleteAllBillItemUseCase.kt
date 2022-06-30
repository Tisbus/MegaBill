package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.repository.bill.BillRepository
import javax.inject.Inject

class DeleteAllBillItemUseCase @Inject constructor(private val repository: BillRepository) {
    suspend fun deleteAllBillItem(){
        repository.deleteAllBillItem()
    }
}