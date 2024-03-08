package oncall.domain

class EmergencyWork(
        val emergencyWorkDate: Pair<Month, Day>,
        var weekdayEmployee: MutableList<String>,
        var holidayEmployee: MutableList<String>
) {

    fun generateEmergencySchedule(): List<EmergencySchedule> {
        var holidayIndex = 0
        var weekdayIndex = 0
        val currentMonth = emergencyWorkDate.first
        var currentDayOfMonth = emergencyWorkDate.second
        var dayCounter = 1
        val schedule = mutableListOf<EmergencySchedule>()

        val originalWeekdayEmployee = weekdayEmployee.toList()
        val originalHolidayEmployee = holidayEmployee.toList()

        while (dayCounter <= currentMonth.days) {
            val isHoliday = isHoliday(currentMonth, dayCounter)

            if (isHoliday || currentDayOfMonth.isWeekend) {
                swapIfSameEmployee(weekdayEmployee, holidayEmployee, weekdayIndex, holidayIndex)
                schedule.add(EmergencySchedule(currentMonth, dayCounter, currentDayOfMonth, isHoliday, holidayEmployee[holidayIndex]))
                holidayIndex = incrementIndex(holidayIndex, holidayEmployee, originalHolidayEmployee)
            } else {
                swapIfSameEmployee(holidayEmployee, weekdayEmployee, holidayIndex, weekdayIndex)
                schedule.add(EmergencySchedule(currentMonth, dayCounter, currentDayOfMonth, isHoliday, weekdayEmployee[weekdayIndex]))
                weekdayIndex = incrementIndex(weekdayIndex, weekdayEmployee, originalWeekdayEmployee)
            }

            currentDayOfMonth = currentDayOfMonth.next()
            dayCounter++
        }

        return schedule
    }

    private fun isHoliday(month: Month, day: Int): Boolean =
            StatutoryHoliday.entries.any { it.month == month.month && it.day == day }

    private fun swapIfSameEmployee(primaryList: MutableList<String>, secondaryList: MutableList<String>, primaryIndex: Int, secondaryIndex: Int) {
        if (primaryList[primaryIndex] == secondaryList[secondaryIndex]) {
            val temp = primaryList[primaryIndex + 1]
            primaryList[primaryIndex + 1] = primaryList[primaryIndex]
            primaryList[primaryIndex] = temp
        }
    }

    private fun incrementIndex(index: Int, currentList: MutableList<String>, originalList: List<String>): Int {
        return if (index == currentList.size - 1) {
            currentList.clear()
            currentList.addAll(originalList)
            0
        } else {
            index + 1
        }
    }
}