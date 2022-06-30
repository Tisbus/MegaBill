package com.example.megabill.data.repository.person

import androidx.lifecycle.LiveData
import com.example.megabill.data.room.person.PersonDao
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.repository.person.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val db : PersonDao
) : PersonRepository {

    override fun getPersonList(): LiveData<MutableList<Person>> = db.getPersonList()

    override suspend fun addPersonItem(item: Person) {
        db.addPersonItem(item)
    }

    override suspend fun deleteAllPersonItem() {
        db.deleteAllPersonItem()
    }
}