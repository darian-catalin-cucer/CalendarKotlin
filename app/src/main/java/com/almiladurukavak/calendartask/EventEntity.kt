package com.almiladurukavak.calendartask

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDate


@Entity(tableName = "events")
data class Event (


    @PrimaryKey
    @ColumnInfo(name="id")
    var id:Long?,

    @ColumnInfo(name="date")
    var date:String,

    @ColumnInfo(name="mealTime")
    var mealTime:String,

    @ColumnInfo(name="meditatingTime")
    var meditatingTime:String


)
