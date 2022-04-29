package com.example.megabill.data.mapper.person

import com.example.megabill.data.entities.PersonData
import com.example.megabill.domain.entities.Person

class BillMapper {
    fun mapPersonEntityToDb(item : Person) = PersonData(
        id = item.id,
        name = item.name
    )

    fun mapPersonDbToEntity(item : PersonData) = Person(
        id = item.id,
        name = item.name
    )

    fun mapListPersonEntityToDb(list : MutableList<Person>) = list.map {
        mapPersonEntityToDb(it)
    }

    fun mapListPersonDbToEntity(list : MutableList<PersonData>) = list.map {
        mapPersonDbToEntity(it)
    }
}