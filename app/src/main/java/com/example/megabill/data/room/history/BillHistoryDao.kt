package com.example.megabill.data.room.history

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.megabill.domain.entities.BillHistory

@Dao
interface BillHistoryDao {

    @Query("SELECT * FROM bill_history")
    fun getBillHistoryList() : LiveData<MutableList<BillHistory>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBillHistoryItem(item : BillHistory)
    @Query("DELETE from bill_history")
    fun deleteAllBillHistory()
    @Query("DELETE from bill_history WHERE id=:itemId")
    fun deleteBillHistoryItem(itemId : Int)
}