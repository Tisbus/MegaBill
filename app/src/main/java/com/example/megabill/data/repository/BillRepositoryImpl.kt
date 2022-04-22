package com.example.megabill.data.repository

import android.app.Application
import com.example.megabill.data.mapper.Mapper
import com.example.megabill.domain.repository.BillRepository

class BillRepositoryImpl(application: Application) : BillRepository {

    val mapper = Mapper()
}