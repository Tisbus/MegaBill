package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.repository.bill.BillRepository
import javax.inject.Inject

class DeleteBillItemUseCase @Inject constructor(private val repository: BillRepository) {
    suspend fun deleteBillItem(itemId : Int){
        repository.deleteBillItem(itemId)
    }
}