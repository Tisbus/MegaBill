package com.example.megabill.presentation.viewmodel.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.megabill.data.repository.history.BillHistoryRepositoryImpl
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.usecase.history.*

class BillHistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BillHistoryRepositoryImpl(application)

    private val getBillHistoryListUseCase = GetBillHistoryListUseCase(repository)
    private val addBillHistoryItemUseCase = AddBillHistoryItemUseCase(repository)
    private val deleteBillHistoryItemUseCase = DeleteBillHistoryItemUseCase(repository)
    private val deleteAllBillHistoryUseCase = DeleteAllBillHistoryUseCase(repository)
    private val getBillHistoryItemUseCase = GetBillHistoryItemUseCase(repository)

    val listBillHistory = getBillHistoryListUseCase.getListBillHistory()

    private var _getItemHistoryLD = MutableLiveData<BillHistory>()
    val getItemHistoryLD: LiveData<BillHistory>
        get() = _getItemHistoryLD

    fun addBillHistoryItem(
        data: String,
        partyName: String,
        itemProduct: List<Total>,
        totalSum: String,
        totalSumWithTip: String
    ) {
        addBillHistoryItemUseCase.addBillHistoryItem(
            BillHistory(
                data,
                partyName,
                itemProduct,
                totalSum,
                totalSumWithTip
            )
        )
    }

    fun deleteAllBillHistory() {
        deleteAllBillHistoryUseCase.deleteAllBillHistory()
    }

    fun deleteBillHistoryItem(itemId: Int) {
        deleteBillHistoryItemUseCase.deleteBillHistoryItem(itemId)
    }

    fun getBillHistoryItem(itemId: Int) {
        val billHistory = getBillHistoryItemUseCase.getBillHistoryItem(itemId)
        _getItemHistoryLD.value = billHistory
    }

}