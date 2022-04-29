package com.example.megabill.data.room.person

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.megabill.domain.entities.Person

@Database(entities = [Person::class], version = 3, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun billDao(): BillDao

    companion object {
        private const val DB_NAME = "persons"
        private val LOCK = Any()
        private var INSTANCE: PersonDatabase? = null

        fun getInstance(application: Application): PersonDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let{
                    return it
                }
                val  db = Room.databaseBuilder(application, PersonDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}