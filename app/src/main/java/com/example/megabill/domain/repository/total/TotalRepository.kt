package com.example.megabill.domain.repository.total

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Total

interface TotalRepository {
    fun getTotalList() : LiveData<MutableList<Total>>
    fun addTotalItem(item : Total)
    fun deleteAllTotal()
}