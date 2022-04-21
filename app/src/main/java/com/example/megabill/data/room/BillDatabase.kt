package com.example.megabill.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.megabill.data.entities.BillData

@Database(entities = [BillData::class], version = 1, exportSchema = false)
abstract class BillDatabase : RoomDatabase() {

    abstract fun billDao() : BillDao

}