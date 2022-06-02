package com.almiladurukavak.calendartask


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almiladurukavak.calendartask.CalendarUtils.daysInMonthArray
import com.almiladurukavak.calendartask.CalendarUtils.monthYearFromDate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDate


class MainActivity : AppCompatActivity(), CalendarAdapter.OnItemListener {


    lateinit var monthYearText: AppCompatTextView
    lateinit var calendarRecyclerView: RecyclerView
    lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidThreeTen.init(this)


         selectedDate = LocalDate.now()



        val addTimeButton = findViewById<FloatingActionButton>(R.id.addTimeButton)
        addTimeButton.setOnClickListener(listener)

        initWidgets()
        setMonthView()


    }
    private fun initWidgets() {
        monthYearText = findViewById(R.id.monthYearTV)
        calendarRecyclerView = findViewById(R.id.calendarRecycler)
    }



    val listener = View.OnClickListener { view ->

        when (view.id) {

            R.id.addTimeButton -> {

                val intent = Intent(this, NewTimeActivity::class.java)
                startActivity(intent)

            }

        }


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
             R.id.weekview -> startActivity(Intent(this, WeekViewActivity::class.java))


        }

        return super.onOptionsItemSelected(item)
    }

    private fun setMonthView() {

        monthYearText.text = monthYearFromDate(CalendarUtils.selectedDate!!)
        val daysInMonth = daysInMonthArray(CalendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapter(daysInMonth, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView.layoutManager = layoutManager
        calendarRecyclerView.adapter = calendarAdapter
    }

    fun previousMonthAction(view: View?) {

            CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.minusMonths(1)

        setMonthView()
    }

    fun nextMonthAction(view: View?) {

        CalendarUtils.selectedDate = CalendarUtils.selectedDate!!.plusMonths(1)

        setMonthView()
    }


    override fun onItemClick(position: Int, dayText: LocalDate?) {

            if (dayText != null) {
                // Toast.makeText(this,"selected date"+dayText,Toast.LENGTH_SHORT).show()
                CalendarUtils.selectedDate = dayText
                setMonthView()
            }
    }

}