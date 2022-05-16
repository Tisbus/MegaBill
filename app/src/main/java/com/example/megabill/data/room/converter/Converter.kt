package com.example.megabill.data.room.converter

import androidx.room.TypeConverter
import com.example.megabill.domain.entities.Total
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun subjectToJson(subject: List<Total>?): String? {
        return gson.toJson(subject)?.let {
            return it
        }
    }

    @TypeConverter
    fun jsonToSubject(json: String?): List<Total> {
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<Total>>() {}.type
            gson.fromJson(json, type)
        }
    }
}