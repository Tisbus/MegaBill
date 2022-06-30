package com.example.megabill.di

import android.app.Application
import com.example.megabill.data.repository.bill.BillRepositoryImpl
import com.example.megabill.data.repository.history.BillHistoryRepositoryImpl
import com.example.megabill.data.repository.person.PersonRepositoryImpl
import com.example.megabill.data.repository.total.TotalRepositoryImpl
import com.example.megabill.data.room.bill.BillDao
import com.example.megabill.data.room.bill.BillDatabase
import com.example.megabill.data.room.history.BillHistoryDao
import com.example.megabill.data.room.history.BillHistoryDatabase
import com.example.megabill.data.room.person.PersonDao
import com.example.megabill.data.room.person.PersonDatabase
import com.example.megabill.data.room.total.TotalDao
import com.example.megabill.data.room.total.TotalDatabase
import com.example.megabill.domain.repository.bill.BillRepository
import com.example.megabill.domain.repository.history.HistoryRepository
import com.example.megabill.domain.repository.person.PersonRepository
import com.example.megabill.domain.repository.total.TotalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @ApplicationScope
    @Binds
    fun bindPersonRepository(impl : PersonRepositoryImpl) : PersonRepository
    @ApplicationScope
    @Binds
    fun bindBillRepository(impl : BillRepositoryImpl) : BillRepository
    @ApplicationScope
    @Binds
    fun bindBillHistoryRepository(impl : BillHistoryRepositoryImpl) : HistoryRepository
    @ApplicationScope
    @Binds
    fun bindTotalRepository(impl : TotalRepositoryImpl) : TotalRepository

    companion object{
        @ApplicationScope
        @Provides
        fun providePersonDao(application: Application) : PersonDao{
            return PersonDatabase.getInstance(application).billDao()
        }
        @ApplicationScope
        @Provides
        fun provideBillDao(application: Application) : BillDao{
            return  BillDatabase.getInstance(application).billDao()
        }
        @ApplicationScope
        @Provides
        fun provideBillHistoryDao(application: Application) : BillHistoryDao{
            return BillHistoryDatabase.getInstance(application).billHistoryDao()
        }
        @ApplicationScope
        @Provides
        fun provideTotalDao(application: Application) : TotalDao{
            return TotalDatabase.getInstance(application).totalDao()
        }
    }
}