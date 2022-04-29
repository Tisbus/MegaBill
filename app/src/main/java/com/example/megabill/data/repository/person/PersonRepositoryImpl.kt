package com.example.megabill.data.repository.person

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.megabill.data.mapper.person.BillMapper
import com.example.megabill.data.room.person.PersonDatabase
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository

class PersonRepositoryImpl(application: Application) : PersonRepository {

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