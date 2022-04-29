package com.example.megabill.data.room.bill

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.megabill.domain.entities.Bill

@Dao
interface BillDao {
    @Query("SELECT * FROM bill")
    fun getBillList() : LiveData<MutableList<Bill>>
    @Query("SELECT * FROM bill WHERE id = :itemId")
    fun getBillItem(itemId : Int) : Bill
    @Insert
    fun addBillItem(item : Bill)
    @Query("DELETE FROM bill WHERE id=:shopId")
    fun deleteBillItem(shopId : Int)
    @Query("DELETE FROM bill")
    fun deleteAllBillItem()
}