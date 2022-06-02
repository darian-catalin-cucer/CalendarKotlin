package com.almiladurukavak.calendartask

import android.os.Build
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.YearMonth
import java.util.*
import kotlin.collections.ArrayList


object CalendarUtils {
    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val hour = c.get(Calendar.HOUR_OF_DAY)
    val minute = c.get(Calendar.MINUTE)



    var selectedDate= org.threeten.bp.LocalDate.now()


    fun formattedDate(date: LocalDate): String {
        val formatter = org.threeten.bp.format.DateTimeFormatter.ofPattern("dd MMMM yyyy")

        return date.format(formatter)
    }

    fun formattedTime(time: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")

        return time.format(formatter)
    }

    fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")

        return date.format(formatter)
    }

    fun daysInMonthArray(date: LocalDate?): ArrayList<LocalDate?> {
        val daysInMonthArray = ArrayList<LocalDate?>()
        val yearMonth= org.threeten.bp.YearMonth.from(date)

        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) daysInMonthArray.add(null) else daysInMonthArray.add(
                LocalDate.of(
                    selectedDate!!.year, selectedDate!!.month, i - dayOfWeek
                )
            )
        }
        return daysInMonthArray
    }

    fun daysInWeekArray(selectedDate: LocalDate): ArrayList<LocalDate?> {
        val days = ArrayList<LocalDate?>()
        var current = sundayForDate(selectedDate)
        val endDate = current!!.plusWeeks(1)

        while (current!!.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    private fun sundayForDate(current: LocalDate): LocalDate? {
        var current = current
        val oneWeekAgo = current.minusWeeks(1)

        while (current.isAfter(oneWeekAgo)) {
            if (current.dayOfWeek == org.threeten.bp.DayOfWeek.SUNDAY) return current
            current = current.minusDays(1)
        }
        return null
    }
}