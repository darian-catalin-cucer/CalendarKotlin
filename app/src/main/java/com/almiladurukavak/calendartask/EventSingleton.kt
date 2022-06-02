package com.almiladurukavak.calendartask

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = arrayOf(Event::class), version = 1, exportSchema = false)
abstract class EventSingleton :RoomDatabase(){


    abstract fun eventDao():EventDao

    companion object{

        private var INSTANCE: EventSingleton? =null

        fun getInstance(context: Context):EventSingleton ?{


            if (INSTANCE==null){

                INSTANCE = Room.databaseBuilder(
                    context,
                    EventSingleton::class.java,
                    "events.db"
                ).allowMainThreadQueries()
                    .build()

            }


            return INSTANCE as EventSingleton

        }

    }
}