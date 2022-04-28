package com.example.megabill.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.megabill.data.entities.PersonData

@Dao
interface BillDao {
    @Query("SELECT * FROM person")
    fun getPersonList() : LiveData<MutableList<PersonData>>
    @Insert
    fun addPersonItem(item : PersonData)
    @Query("DELETE FROM person")
    fun deleteAllPersonItem()
    @Query("SELECT * FROM person WHERE id = :id")
    fun getPersonItemId(id : Int) : PersonData
}