package com.example.megabill.domain.usecase.total

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.repository.total.TotalRepository
import javax.inject.Inject

class GetTotalListUseCase @Inject constructor(private val repository: TotalRepository) {
    fun getTotalList() : LiveData<MutableList<Total>>{
        return repository.getTotalList()
    }
}