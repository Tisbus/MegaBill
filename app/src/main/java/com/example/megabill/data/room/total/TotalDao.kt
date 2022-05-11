package com.example.megabill.data.room.total

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.megabill.domain.entities.Total

@Dao
interface TotalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item : Total)
    @Query("SELECT * FROM total")
    fun getListTotal() : LiveData<MutableList<Total>>
    @Query("DELETE From total")
    fun deleteAllTotal()
}