package com.example.megabill.domain.usecase.bill

import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.repository.bill.BillRepository
import javax.inject.Inject

class GetBillItemUseCase @Inject constructor(private val repository: BillRepository) {
    suspend fun getBillItem(itemId : Int) : Bill {
        return repository.getBillItem(itemId)
    }
}