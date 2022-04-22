package com.example.megabill.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.megabill.data.repository.BillRepositoryImpl

class BillViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BillRepositoryImpl(application)
}