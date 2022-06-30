package com.example.megabill.presentation.viewmodel.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megabill.domain.entities.Person
import com.example.megabill.domain.usecase.person.AddPersonItemUseCase
import com.example.megabill.domain.usecase.person.DeleteAllPersonItemUseCase
import com.example.megabill.domain.usecase.person.GetPersonListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val addPersonItemUseCase: AddPersonItemUseCase,
    private val deleteAllPersonItemUseCase: DeleteAllPersonItemUseCase,
    private val getPersonListUseCase: GetPersonListUseCase
) : ViewModel() {

    val listPerson = getPersonListUseCase.getPersonList()

    fun addPersonItem(id: Int, name: String) {
        viewModelScope.launch {
            addPersonItemUseCase.addPersonItem(Person(id, name))
        }
    }

    fun deleteAllPersonItem() {
        viewModelScope.launch {
            deleteAllPersonItemUseCase.deleteAllPersonItem()
        }
    }
}