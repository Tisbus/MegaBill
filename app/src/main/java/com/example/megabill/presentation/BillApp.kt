package com.example.megabill.presentation

import android.app.Application
import com.example.megabill.di.DaggerApplicationComponent

class BillApp : Application() {

    val component by lazy{
        DaggerApplicationComponent.factory().create(this)
    }
}