package com.example.megabill.data.room.bill

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.megabill.domain.entities.Bill

@Database(entities = [Bill::class], version = 2, exportSchema = false)
abstract class BillDatabase : RoomDatabase() {

    abstract fun billDao() : BillDao

    companion object{
        private const val DB_NAME = "bill"
        private val LOCK = Any()
        private var INSTANCE : BillDatabase? = null

        fun getInstance(application: Application) : BillDatabase{
            INSTANCE?.let{
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let{
                    return it
                }
                val db = Room.databaseBuilder(application,
                    BillDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

}
