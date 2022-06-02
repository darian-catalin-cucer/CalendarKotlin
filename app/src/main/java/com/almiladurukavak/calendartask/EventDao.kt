package com.almiladurukavak.calendartask

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {



    @Query("SELECT *FROM events WHERE date=:eventDate")
    fun getAllEvents(eventDate:String): LiveData<List<Event>>


    //@Query("SELECT *FROM events")
    //fun getAllEvents(): LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(events: Event)




}