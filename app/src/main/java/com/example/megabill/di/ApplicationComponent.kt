package com.example.megabill.di

import android.app.Application
import com.example.megabill.presentation.fragment.*
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(frag : AddItemFragment)
    fun inject(frag : AddPersonFragment)
    fun inject(frag : BillDetailFragment)
    fun inject(frag : EditItemFragment)
    fun inject(frag : ListBillFragment)
    fun inject(frag : StartFragment)
    fun inject(frag : TotalBillFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application) : ApplicationComponent
    }
}