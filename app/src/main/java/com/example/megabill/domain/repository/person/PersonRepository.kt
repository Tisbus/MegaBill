package com.example.megabill.domain.repository.person

import androidx.lifecycle.LiveData
import com.example.megabill.domain.entities.Person

interface PersonRepository {
    fun getPersonList() : LiveData<MutableList<Person>>
    fun addPersonItem(item : Person)
    fun deleteAllPersonItem()
}