package com.almiladurukavak.calendartask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val events:List<Event>)
    :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {

        val v: View=LayoutInflater.from((parent.context)).inflate(R.layout.custom_event,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {



            holder.id.text= events[position].id.toString()
            holder.eventMeal.text=events[position].mealTime
            holder.eventMeditate.text=events[position].meditatingTime


    }

    override fun getItemCount(): Int {
       return events.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val id=itemView.findViewById<TextView>(R.id.id)
        val eventMeal=itemView.findViewById<TextView>(R.id.eventMealText)
        val eventMeditate=itemView.findViewById<TextView>(R.id.eventMediText)
    }
}