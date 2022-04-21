package com.example.megabill.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.megabill.domain.entities.BillHistory



@Database(entities = [BillHistory::class], version = 1, exportSchema = false)
abstract class BillHistoryDatabase :RoomDatabase() {

    abstract fun billDao() : BillDao

}