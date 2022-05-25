package com.example.megabill.data.room.history

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.megabill.data.room.converter.Converter
import com.example.megabill.domain.entities.BillHistory

@Database(entities = [BillHistory::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class BillHistoryDatabase : RoomDatabase() {

    abstract fun billHistoryDao(): BillHistoryDao

    companion object {
        private const val DB_NAME = "bill_history"
        private val LOCK = Any()
        private var INSTANCE: BillHistoryDatabase? = null

        fun getInstance(context: Context): BillHistoryDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    BillHistoryDatabase::class.java, DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

}
