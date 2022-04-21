package com.example.megabill.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.megabill.data.entities.PersonData

@Database(entities = [PersonData::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun billDao() : BillDao

}