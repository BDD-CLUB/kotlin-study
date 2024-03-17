package oncall.model

class Calendar (
    val dates: List<Date>,
) {
    companion object {
        fun of(month: Month, startDayOfWeek: DayOfWeek): Calendar {
            val newDates: MutableList<Date> = mutableListOf()
            var dayOfWeek = startDayOfWeek
            for (day in 1..< month.lastDayOfMonth + 1) {
                val newDate = Date(
                    month = month,
                    day = day,
                    dayOfWeek = dayOfWeek
                )
                newDates.add(newDate)
                dayOfWeek = dayOfWeek.nextDayOfWeek()
            }
            return Calendar(newDates)
        }
    }

}

class Date(
    val month: Month,
    val day: Int,
    val dayOfWeek: DayOfWeek,
) {
    val isPublicHoliday: Boolean
        get() = publicHolidays[month]?.contains(day) == true

    val isHoliday: Boolean
        get() = isPublicHoliday || dayOfWeek.isHoliday

    private val publicHolidays = mapOf(
        Month.JANUARY to listOf(1),
        Month.MARCH to listOf(1),
        Month.MAY to listOf(5),
        Month.JUNE to listOf(6),
        Month.AUGUST to listOf(15),
        Month.OCTOBER to listOf(3, 9),
        Month.DECEMBER to listOf(25)
    )

}
