package com.example.megabill.di

import androidx.lifecycle.ViewModel
import com.example.megabill.presentation.viewmodel.bill.BillViewModel
import com.example.megabill.presentation.viewmodel.history.BillHistoryViewModel
import com.example.megabill.presentation.viewmodel.person.PersonViewModel
import com.example.megabill.presentation.viewmodel.total.TotalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ModuleKey(PersonViewModel::class)
    fun bindPersonViewModel(viewModel: PersonViewModel) : ViewModel

    @Binds
    @IntoMap
    @ModuleKey(BillViewModel::class)
    fun bindBillViewModel(viewModel: BillViewModel) : ViewModel

    @Binds
    @IntoMap
    @ModuleKey(BillHistoryViewModel::class)
    fun bindBillHistoryViewModel(viewModel: BillHistoryViewModel) : ViewModel

    @Binds
    @IntoMap
    @ModuleKey(TotalViewModel::class)
    fun bindTotalViewModel(viewModel: TotalViewModel) : ViewModel
}