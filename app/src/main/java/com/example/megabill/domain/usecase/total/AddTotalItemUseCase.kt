package com.example.megabill.domain.usecase.total

import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.repository.total.TotalRepository
import javax.inject.Inject

class AddTotalItemUseCase @Inject constructor(private val repository: TotalRepository){
    suspend fun addItemTotal(item : Total){
        repository.addTotalItem(item)
    }
}