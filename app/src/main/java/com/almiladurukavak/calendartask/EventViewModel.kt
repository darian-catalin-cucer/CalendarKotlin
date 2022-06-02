package com.almiladurukavak.calendartask

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EventViewModel(application: Application):AndroidViewModel(application) {

    private val db:EventSingleton= EventSingleton.getInstance(application)!!

   // internal val allEvents: LiveData<List<Event>> = db.eventDao().getAllEvents()

    fun insert(event: Event){
        db.eventDao().insert(event)

    }
    @WorkerThread
    fun search(date : String) : LiveData<List<Event>>{
        return db.eventDao().getAllEvents(date)
    }
}