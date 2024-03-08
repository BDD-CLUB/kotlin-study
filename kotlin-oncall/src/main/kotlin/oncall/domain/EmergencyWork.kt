package oncall.domain

class EmergencyWork(
        val emergencyWorkDate: Pair<Month, Day>,
        var weekdayEmployee: MutableList<String>,
        var holidayEmployee: MutableList<String>,
) {

    fun generateEmergencySchedule(): List<EmergencySchedule> {
        var holidayIndex = 0
        var weekdayIndex = 0
        var currentMonth   = emergencyWorkDate.first
        var currentDays = emergencyWorkDate.second
        var currentDay = 1
        var isHoliday = false
        val schedule = mutableListOf<EmergencySchedule>()

        val copyWeekdayEmployee: List<String> = weekdayEmployee.toList()
        val copyHolidayEmployee: List<String> = holidayEmployee.toList()

        for(i in 0 until emergencyWorkDate.first.days) {
            isHoliday = StatutoryHoliday.entries.any { it.month == currentMonth.month && it.day == currentDay }

            if (isHoliday || currentDays.isWeekend) {
                if (weekdayEmployee[weekdayIndex] == holidayEmployee[holidayIndex]) {
                    val temp = weekdayEmployee[weekdayIndex + 1]
                    weekdayEmployee[weekdayIndex + 1] = weekdayEmployee[weekdayIndex]
                    weekdayEmployee[weekdayIndex] = temp
                }

                schedule.add(EmergencySchedule(currentDays, isHoliday, holidayEmployee[holidayIndex]))

                if (holidayIndex == holidayEmployee.size - 1) {
                    holidayIndex = 0
                    holidayEmployee = copyHolidayEmployee.toMutableList()
                    currentDays = currentDays.next()
                    currentDay++
                    continue
                }
                holidayIndex++
            } else {
                if (weekdayEmployee[weekdayIndex] == holidayEmployee[holidayIndex]) {
                    val temp = holidayEmployee[holidayIndex + 1]
                    holidayEmployee[holidayIndex + 1] = holidayEmployee[holidayIndex]
                    holidayEmployee[holidayIndex] = temp
                }
                schedule.add(EmergencySchedule(currentDays, isHoliday, weekdayEmployee[weekdayIndex]))

                if (weekdayIndex == weekdayEmployee.size - 1) {
                    weekdayIndex = 0
                    weekdayEmployee = copyWeekdayEmployee.toMutableList()
                    currentDays = currentDays.next()
                    currentDay++
                    continue
                }
                weekdayIndex++
            }

            println(schedule.size)
            currentDays = currentDays.next()
            println(currentDays)
            currentDay++
        }

        return schedule
    }

}