package com.example.megabill.data.room.person

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.megabill.domain.entities.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getPersonList() : LiveData<MutableList<Person>>
    @Insert
    fun addPersonItem(item : Person)
    @Query("DELETE FROM person")
    fun deleteAllPersonItem()
}