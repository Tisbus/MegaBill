package com.example.megabill.presentation.viewmodel.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.megabill.data.repository.history.BillHistoryRepositoryImpl
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.usecase.history.AddBillHistoryItemUseCase
import com.example.megabill.domain.usecase.history.DeleteAllBillHistoryUseCase
import com.example.megabill.domain.usecase.history.DeleteBillHistoryItemUseCase
import com.example.megabill.domain.usecase.history.GetBillHistoryListUseCase

class BillHistoryViewModel(application: Application) : AndroidViewModel(application){
    private val repository = BillHistoryRepositoryImpl(application)

    private val getBillHistoryListUseCase = GetBillHistoryListUseCase(repository)
    private val addBillHistoryItemUseCase = AddBillHistoryItemUseCase(repository)
    private val deleteBillHistoryItemUseCase = DeleteBillHistoryItemUseCase(repository)
    private val deleteAllBillHistoryUseCase = DeleteAllBillHistoryUseCase(repository)

    val listBillHistory = getBillHistoryListUseCase.getListBillHistory()

    fun addBillHistoryItem(
        data: String,
        partyName: String,
        itemProduct: List<Total>,
        totalSum: String,
        totalSumWithTip : String
    ){
        addBillHistoryItemUseCase.addBillHistoryItem(BillHistory(data, partyName, itemProduct, totalSum, totalSumWithTip))
    }

    fun deleteAllBillHistory(){
        deleteAllBillHistoryUseCase.deleteAllBillHistory()
    }

    fun deleteBillHistoryItem(itemId : Int){
        deleteBillHistoryItemUseCase.deleteBillHistoryItemUseCase(itemId)
    }
}