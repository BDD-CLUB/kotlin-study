package oncall.model

class OnCallSchedule(
    val month: Month,
    val scheduledEmployees: List<Employee>,
) {
    companion object {
        fun of(month: Month, onCallEmployee: OnCallEmployee, startDayOfWeek: DayOfWeek): OnCallSchedule {
            val calendar = Calendar.of(month, startDayOfWeek)
            val newOnCallSchedule = mutableListOf<Employee>()

            var weekdayIndex = 0
            var holidayIndex = 0

            calendar.dates.forEach { date ->
                val employee =
                    if (date.isHoliday) selectEmployee(onCallEmployee.holidayEmployees, holidayIndex++)
                    else selectEmployee(onCallEmployee.weekdayEmployees, weekdayIndex++)

                newOnCallSchedule.add(employee)
                preventRepeatedAssignments(newOnCallSchedule)
            }

            return OnCallSchedule(month, newOnCallSchedule)
        }

        private fun selectEmployee(employees: List<Employee>, index: Int): Employee {
            return employees[index % employees.size]
        }

        private fun preventRepeatedAssignments(schedule: MutableList<Employee>) {
            val lastIndex = schedule.size - 1
            if (lastIndex > 0 && schedule[lastIndex].nickName == schedule[lastIndex - 1].nickName) {
                schedule.swap(lastIndex, lastIndex - 1)
            }
        }
    }
}

private fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}
