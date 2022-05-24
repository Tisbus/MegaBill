package com.example.megabill.data.repository.person

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.megabill.data.room.person.PersonDatabase
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository

class PersonRepositoryImpl(application: Application) : PersonRepository {

    private val db = PersonDatabase.getInstance(application).billDao()

    override fun getPersonList(): LiveData<MutableList<Person>> = db.getPersonList()

    override suspend fun addPersonItem(item: Person) {
        db.addPersonItem(item)
    }

    override suspend fun deleteAllPersonItem() {
        db.deleteAllPersonItem()
    }
}