package com.example.megabill.data.repository.total

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.megabill.data.room.total.TotalDatabase
import com.example.megabill.domain.entities.Total
import com.example.megabill.domain.repository.total.TotalRepository

class TotalRepositoryImpl(application: Application) : TotalRepository {

    private val db = TotalDatabase.getInstance(application).totalDao()

    override fun getTotalList(): LiveData<MutableList<Total>> = db.getListTotal()

    override suspend fun addTotalItem(item: Total) {
        db.insertItem(item)
    }

    override suspend fun deleteAllTotal() {
        db.deleteAllTotal()
    }
}