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
        get() = publicHoliday.contains(Pair(month, day))

    val isHoliday: Boolean
        get() = isPublicHoliday || dayOfWeek.isHoliday

    private val publicHoliday = listOf(
        Pair(Month.JANUARY, 1),
        Pair(Month.MARCH, 1),
        Pair(Month.MAY, 5),
        Pair(Month.JUNE, 6),
        Pair(Month.AUGUST, 15),
        Pair(Month.OCTOBER, 3),
        Pair(Month.OCTOBER, 9),
        Pair(Month.DECEMBER, 25)
    )

}
