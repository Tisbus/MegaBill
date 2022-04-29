package com.example.megabill.presentation.viewmodel.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.megabill.data.repository.person.PersonRepositoryImpl
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.usecase.person.AddPersonItemUseCase
import com.example.megabill.domain.usecase.person.DeleteAllPersonItemUseCase
import com.example.megabill.domain.usecase.person.GetPersonItemUseCase
import com.example.megabill.domain.usecase.person.GetPersonListUseCase

class BillViewModel(application: Application ) : AndroidViewModel(application) {
    private val repository = PersonRepositoryImpl(application)

    private val addPersonItemUseCase = AddPersonItemUseCase(repository)
    private val deleteAllPersonItemUseCase = DeleteAllPersonItemUseCase(repository)
    private val getPersonItemUseCase = GetPersonItemUseCase(repository)
    private val getPersonListUseCase = GetPersonListUseCase(repository)

    val listPerson = getPersonListUseCase.getPersonList()

    fun addPersonItem(name : String, count : Int){
        addPersonItemUseCase.addPersonItem(Person(name, count))
    }
    fun deleteAllPersonItem(){
        deleteAllPersonItemUseCase.deleteAllPersonItem()
    }
    fun getPersonItem(item : Person) {
        getPersonItemUseCase.getPersonItem(item.id)
    }
}