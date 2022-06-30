package com.example.megabill.presentation.viewmodel.bill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.usecase.bill.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class BillViewModel @Inject constructor(
    private val addBillItemUseCase: AddBillItemUseCase,
    private val deleteAllBillItemUseCase: DeleteAllBillItemUseCase,
    private val deleteBillItemUseCase: DeleteBillItemUseCase,
    private val editBillItemUseCase: EditBillItemUseCase,
    private val getBillItemUseCase: GetBillItemUseCase,
    private val getBillListUseCase: GetBillListUseCase,
) : ViewModel() {

    private val _getBillItemLD = MutableLiveData<Bill>()
    val getBillItemLD: LiveData<Bill>
        get() = _getBillItemLD

    private val _checkErrorItemName = MutableLiveData<Boolean>()
    val checkErrorItemName: LiveData<Boolean>
        get() = _checkErrorItemName

    private val _checkErrorPrice = MutableLiveData<Boolean>()
    val checkErrorPrice: LiveData<Boolean>
        get() = _checkErrorPrice

    val listBill = getBillListUseCase.getBillList()

    fun addBillItem(
        name: String,
        nameId: Int,
        item: String,
        price: String,
    ) {
        val nameItem = inputItem(item)
        val priceItem = inputPrice(price)
        if (checkErrorItem(nameItem, priceItem)) {
            viewModelScope.launch {
                val result = Bill(name, nameId, nameItem, priceItem)
                addBillItemUseCase.addBillItem(result)
            }
        }
    }

    fun getBillItem(itemId: Int) {
        viewModelScope.launch {
            val bill = getBillItemUseCase.getBillItem(itemId)
            _getBillItemLD.value = bill
        }
    }

    fun deleteAllBillItem() {
        viewModelScope.launch {
            deleteAllBillItemUseCase.deleteAllBillItem()
        }
    }

    fun deleteBillItem(itemId: Int) {
        viewModelScope.launch {
            deleteBillItemUseCase.deleteBillItem(itemId)
        }
    }

    fun editBillItem(name: String, nameId: Int, item: String, price: String) {
        _getBillItemLD.value?.let {
            val nameItem = inputItem(item)
            val priceItem = inputPrice(price)
            if (checkErrorItem(nameItem, priceItem)) {
                viewModelScope.launch {
                    val itemBill = it.copy(
                        name = name,
                        idName = nameId,
                        item = nameItem,
                        price = priceItem
                    )
                    editBillItemUseCase.editBillItem(itemBill)
                }
            }
        }
    }

    private fun inputItem(inputItem: String?): String {
        return inputItem?.trim() ?: ""
    }

    private fun inputPrice(inputPrice: String?): Int {
        return try {
            inputPrice?.trim()?.toInt() ?: 0
        } catch (
            e: Exception,
        ) {
            0
        }
    }

    private fun checkErrorItem(item: String, price: Int): Boolean {
        var result = true
        if (item.isBlank()) {
            _checkErrorItemName.value = true
            result = false
        } else {
            _checkErrorItemName.value = false
        }
        if (price <= 0) {
            _checkErrorPrice.value = true
            result = false
        } else {
            _checkErrorPrice.value = false
        }
        return result
    }

    fun resetCheckErrorName() {
        _checkErrorItemName.value = false
    }

    fun resetCheckErrorPrice() {
        _checkErrorPrice.value = false
    }
}