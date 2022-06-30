package com.example.megabill.presentation.viewmodel.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megabill.domain.entities.BillHistory
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.usecase.history.*
import javax.inject.Inject

class BillHistoryViewModel @Inject constructor(
    private val getBillHistoryListUseCase: GetBillHistoryListUseCase,
    private val addBillHistoryItemUseCase: AddBillHistoryItemUseCase,
    private val deleteBillHistoryItemUseCase: DeleteBillHistoryItemUseCase,
    private val deleteAllBillHistoryUseCase: DeleteAllBillHistoryUseCase,
    private val getBillHistoryItemUseCase: GetBillHistoryItemUseCase,
) : ViewModel() {

    val listBillHistory = getBillHistoryListUseCase.getListBillHistory()

    private var _getItemHistoryLD = MutableLiveData<BillHistory>()
    val getItemHistoryLD: LiveData<BillHistory>
        get() = _getItemHistoryLD

    fun addBillHistoryItem(
        data: String,
        partyName: String,
        itemProduct: List<Total>,
        totalSum: String,
        totalSumWithTip: String,
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