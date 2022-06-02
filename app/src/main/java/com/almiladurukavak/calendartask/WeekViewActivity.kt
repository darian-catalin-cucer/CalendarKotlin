package com.almiladurukavak.calendartask
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almiladurukavak.calendartask.CalendarAdapter.OnItemListener
import com.almiladurukavak.calendartask.CalendarUtils.daysInWeekArray
import com.almiladurukavak.calendartask.CalendarUtils.monthYearFromDate
import org.threeten.bp.LocalDate


class WeekViewActivity : AppCompatActivity(), OnItemListener {
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var eventRecyclerView: RecyclerView? = null
    private lateinit var eventModel:EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_view)

        eventModel= ViewModelProviders.of(this).get(EventViewModel::class.java)


        initWidgets()
        setWeekView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecycler)
        monthYearText = findViewById(R.id.monthYearTV)
        eventRecyclerView = findViewById(R.id.eventListView)
    }

    private fun setWeekView() {
        monthYearText!!.text=(monthYearFromDate(CalendarUtils.selectedDate))
        val days: ArrayList<LocalDate?> = daysInWeekArray(CalendarUtils.selectedDate)
        val calendarAdapter = CalendarAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
        setEventAdpater(CalendarUtils.selectedDate.toString())
    }

    fun previousWeekAction(view: View?) {

            CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1)

        setWeekView()
    }

    fun nextWeekAction(view: View?) {

            CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)

        setWeekView()
    }

    override fun onItemClick(position: Int, date: LocalDate?) {
        CalendarUtils.selectedDate = date!!
        setWeekView()
        setEventAdpater(CalendarUtils.selectedDate.toString())
    }

    override fun onResume() {
        super.onResume()
        setEventAdpater(CalendarUtils.selectedDate.toString())
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.monthview -> finish()

        }

        return super.onOptionsItemSelected(item)
    }



    private fun setEventAdpater(date:String) {

        val linearLayoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        eventRecyclerView?.layoutManager=linearLayoutManager

        getEventsFromDb(date)

    }

    private fun getEventsFromDb(date: String) {




        eventModel.search(date = date).observe(this, Observer { list ->
            list?.let {
                eventRecyclerView?.adapter =RecyclerViewAdapter(list)
                Log.e("List = ", list.toString())
            }

        })
    }

    fun newEventAction(view: View?) {
        startActivity(Intent(this, NewTimeActivity::class.java))
    }
}