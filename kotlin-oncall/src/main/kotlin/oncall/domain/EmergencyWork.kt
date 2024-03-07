package oncall.domain

class EmergencyWork(
        val emergencyWorkDate: Pair<Month, Day>,
        val holidayEmployee: List<String>,
        val weekdayEmployee: List<String>,
) {

    fun generateEmergencySchedule() {
        var holidayIndex = 0
        var weekdayIndex = 0
        var currentMonth   = emergencyWorkDate.first
        var currentDays = emergencyWorkDate.second
        var currentDay = 1
        var isHoliday = false
        val schedule = mutableMapOf<Pair<Day, Boolean>, String>()

        for(i in 1 until emergencyWorkDate.first.days) {
            isHoliday = StatutoryHoliday.entries.any { it.month == currentMonth.month && it.day == currentDay }

            if (isHoliday) {
                schedule[Pair(currentDays, isHoliday)] = holidayEmployee[holidayIndex]
                holidayIndex++
            } else {
                schedule[Pair(currentDays, isHoliday)] = weekdayEmployee[weekdayIndex]
                weekdayIndex++
            }

            currentDays.next()
            currentDay++
        }


    }

}