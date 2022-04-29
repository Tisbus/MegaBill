package com.example.megabill.presentation.viewmodel.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.megabill.data.repository.person.PersonRepositoryImpl
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.usecase.person.AddPersonItemUseCase
import com.example.megabill.domain.usecase.person.DeleteAllPersonItemUseCase
import com.example.megabill.domain.usecase.person.GetPersonListUseCase

class PersonViewModel(application: Application ) : AndroidViewModel(application) {
    private val repository = PersonRepositoryImpl(application)

    private val addPersonItemUseCase = AddPersonItemUseCase(repository)
    private val deleteAllPersonItemUseCase = DeleteAllPersonItemUseCase(repository)
    private val getPersonListUseCase = GetPersonListUseCase(repository)

    val listPerson = getPersonListUseCase.getPersonList()

    fun addPersonItem(id : Int, name : String){
        addPersonItemUseCase.addPersonItem(Person(id, name))
    }
    fun deleteAllPersonItem(){
        deleteAllPersonItemUseCase.deleteAllPersonItem()
    }
}