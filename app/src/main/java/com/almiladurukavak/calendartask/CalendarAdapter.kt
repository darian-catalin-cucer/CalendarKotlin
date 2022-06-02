package com.almiladurukavak.calendartask

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.threeten.bp.LocalDate


class CalendarAdapter(
    private val days: ArrayList<LocalDate?>,
    private val onItemListener: OnItemListener
) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams

        layoutParams.height = (parent.height * 0.166666666).toInt()
        return CalendarViewHolder(view, onItemListener,days)
    }


    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date : LocalDate? = days[position]
        if (date==null){

            holder.dayOfMonth.text=""

        }else{


                holder.dayOfMonth.text= date.dayOfMonth.toString()
                if (date.equals(CalendarUtils.selectedDate)){
                    holder.parentView.setBackgroundColor(Color.LTGRAY)

                }

        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: LocalDate?)
    }
}