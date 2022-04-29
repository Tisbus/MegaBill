package com.example.megabill.presentation.viewmodel.bill

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.megabill.data.repository.bill.BillRepositoryImpl
import com.example.megabill.domain.entities.Bill
import com.example.megabill.domain.usecase.bill.*

class BillViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BillRepositoryImpl(application)

    private val addBillItemUseCase = AddBillItemUseCase(repository)
    private val deleteAllBillItemUseCase = DeleteAllBillItemUseCase(repository)
    private val deleteBillItemUseCase = DeleteBillItemUseCase(repository)
    private val editBillItemUseCase = EditBillItemUseCase(repository)
    private val getBillItemUseCase = GetBillItemUseCase(repository)
    private val getBillListUseCase = GetBillListUseCase(repository)

    private val _getBillItemLD = MutableLiveData<Bill>()
    val getBillItemLD :LiveData<Bill>
    get() = _getBillItemLD


    val listBill = getBillListUseCase.getBillList()

    fun addBillItem(
        name : String,
        nameId : Int,
        item : String,
        price : Int
    ){
        addBillItemUseCase.addBillItem(Bill(name, nameId, item, price))
    }

    fun getBillItem(itemId : Int){
        getBillItemUseCase.getBillItem(itemId)
    }

    fun deleteAllBillItem(){
        deleteAllBillItemUseCase.deleteAllBillItem()
    }

    fun deleteBillItem(itemId: Int){
        deleteBillItemUseCase.deleteBillItem(itemId)
    }

    fun editBillItem(name : String, nameId : Int, item : String,  price : Int){
        _getBillItemLD.value?.let{
            val itemBill = it.copy(
                name = name,
                idName = nameId,
                item = item,
                price = price
            )
            editBillItemUseCase.editBillItem(itemBill)
        }

    }
}