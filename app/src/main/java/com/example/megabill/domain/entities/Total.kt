package com.example.megabill.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "total")
@Parcelize
data class Total(
    val namePerson : String,
    val listBuyProduct : String,
    val totalSumToPerson : String,
    var sumTips : String,
    var totalSumWithTips : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = UNDEFINED_ID
): Parcelable{
    companion object{
        const val UNDEFINED_ID = 0
    }
}