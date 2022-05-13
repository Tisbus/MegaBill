package com.example.megabill.presentation.viewmodel.total

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.megabill.data.repository.total.TotalRepositoryImpl
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.usecase.total.AddTotalItemUseCase
import com.example.megabill.domain.usecase.total.DeleteAllTotalUseCase
import com.example.megabill.domain.usecase.total.GetTotalListUseCase

class TotalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TotalRepositoryImpl(application)

    private val addTotalItemUseCase = AddTotalItemUseCase(repository)
    private val deleteAllTotalUseCase = DeleteAllTotalUseCase(repository)
    private val getTotalListUseCase = GetTotalListUseCase(repository)

    val listTotal = getTotalListUseCase.getTotalList()

    fun addItemTotal(
        namePerson : String,
        listBuyProduct : String,
        totalSumToPerson : String,
        sumTips : String,
        totalSumWithTips : String
    ){
        addTotalItemUseCase.addItemTotal(Total(namePerson, listBuyProduct, totalSumToPerson, sumTips, totalSumWithTips))
    }

    fun deleteAllTotal(){
        deleteAllTotalUseCase.deleteAllTotal()
    }
}