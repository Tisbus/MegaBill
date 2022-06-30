package com.example.megabill.presentation.viewmodel.total

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.usecase.total.AddTotalItemUseCase
import com.example.megabill.domain.usecase.total.DeleteAllTotalUseCase
import com.example.megabill.domain.usecase.total.GetTotalListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TotalViewModel @Inject constructor(
    private val addTotalItemUseCase: AddTotalItemUseCase,
    private val deleteAllTotalUseCase: DeleteAllTotalUseCase,
    private val getTotalListUseCase: GetTotalListUseCase,
) : ViewModel() {

    val listTotal = getTotalListUseCase.getTotalList()

    fun addItemTotal(
        namePerson: String,
        listBuyProduct: String,
        totalSumToPerson: String,
        sumTips: String,
        totalSumWithTips: String,
    ) {
        viewModelScope.launch {
            addTotalItemUseCase.addItemTotal(
                Total(
                    namePerson,
                    listBuyProduct,
                    totalSumToPerson,
                    sumTips,
                    totalSumWithTips
                )
            )
        }
    }

    fun deleteAllTotal() {
        viewModelScope.launch {
            deleteAllTotalUseCase.deleteAllTotal()
        }
    }
}