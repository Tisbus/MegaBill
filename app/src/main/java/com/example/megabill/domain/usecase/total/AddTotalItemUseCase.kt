package com.example.megabill.domain.usecase.total

import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.repository.total.TotalRepository

class AddTotalItemUseCase (private val repository: TotalRepository){
    fun addItemTotal(item : Total){
        repository.addTotalItem(item)
    }
}