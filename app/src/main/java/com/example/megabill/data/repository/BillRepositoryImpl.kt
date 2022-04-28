package com.example.megabill.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.megabill.data.mapper.BillMapper
import com.example.megabill.data.room.PersonDatabase
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.BillRepository

class BillRepositoryImpl(application: Application) : BillRepository {

    private val mapper = BillMapper()

    private val db = PersonDatabase.getInstance(application).billDao()

    override fun getPersonList() : LiveData<MutableList<Person>> = Transformations.map(
        db.getPersonList()
    ){
        mapper.mapListPersonDbToEntity(it).toMutableList()
    }

    override fun addPersonItem(item: Person) {
        db.addPersonItem(mapper.mapPersonEntityToDb(item))
    }

    override fun deleteAllPersonItem() {
        db.deleteAllPersonItem()
    }

    override fun getPersonItem(id: Int): Person {
       return mapper.mapPersonDbToEntity(db.getPersonItemId(id))
    }
}