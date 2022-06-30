package com.example.megabill.data.repository.total

import androidx.lifecycle.LiveData
import com.example.megabill.data.room.total.TotalDao
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.repository.total.TotalRepository
import javax.inject.Inject

class TotalRepositoryImpl @Inject constructor(
    private val db : TotalDao
) : TotalRepository {

    override fun getTotalList(): LiveData<MutableList<Total>> = db.getListTotal()

    override suspend fun addTotalItem(item: Total) {
        db.insertItem(item)
    }

    override suspend fun deleteAllTotal() {
        db.deleteAllTotal()
    }
}