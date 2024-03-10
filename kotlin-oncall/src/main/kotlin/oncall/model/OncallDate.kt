package oncall.model

import java.time.DayOfWeek
import java.time.DayOfWeek.*
import java.time.Month

class OncallDate(val month: Month, private val startDayOfWeek: DayOfWeek) {
    val monthDay = month.minLength() // 2월은 항상 28일

    private val holidayMap = getHolidayMap(startDayOfWeek)

    private fun getHolidayMap(startDayOfWeek: DayOfWeek): Map<Int, Pair<DayOfWeek, DayType>> {
        val holidayMap = mutableMapOf<Int, Pair<DayOfWeek, DayType>>()
        for (day in 1..this.monthDay) {
            val dayOfWeek = startDayOfWeek.plus((day - 1).toLong())
            holidayMap[day] = Pair(dayOfWeek, isHoliday(dayOfWeek, day))
        }
        return holidayMap.toMap()
    }

    private fun isHoliday(dayOfWeek: DayOfWeek, day: Int) = when (dayOfWeek) {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY ->
            if (SPECIAL_HOLIDAY.contains(Pair(month.value, day))) {
                DayType.WEEKDAY_HOLIDAY
            } else {
                DayType.WEEKDAY
            }

        SATURDAY, SUNDAY -> DayType.HOLIDAY
    }

    operator fun get(day: Int) = holidayMap[day]

    companion object {
        val SPECIAL_HOLIDAY = listOf(
            Pair(1, 1),
            Pair(3, 1),
            Pair(5, 5),
            Pair(6, 6),
            Pair(8, 15),
            Pair(10, 3),
            Pair(10, 9),
            Pair(12, 25)
        )
    }
}

enum class DayType {
    WEEKDAY,
    WEEKDAY_HOLIDAY,
    HOLIDAY,
    ;
}
