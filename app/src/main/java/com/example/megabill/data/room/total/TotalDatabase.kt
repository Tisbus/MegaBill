package com.example.megabill.data.room.total

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.megabill.domain.entities.Total

@Database(entities = [Total::class], version = 2, exportSchema = false)
abstract class TotalDatabase : RoomDatabase() {

    abstract fun totalDao(): TotalDao

    companion object {
        private const val DB_NAME = "total"
        private val LOCK = Any()
        private var INSTANCE: TotalDatabase? = null

        fun getInstance(application: Application): TotalDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(application, TotalDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }


    }

}