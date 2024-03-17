package oncall.controller

import oncall.model.Calendar
import oncall.model.OnCallSchedule
import oncall.view.OnCallConsoleIO

class OnCallRotationAssigner {
    private val onCallConsoleIO = OnCallConsoleIO()
    fun run() {
        val (onCallStartMonth, onCallStartDayOfTheWeek) = onCallConsoleIO.getOnCallMonthAndDayOfTheWeek()
        val onCallEmployee = onCallConsoleIO.getOnCallEmployee()
        val calendar = Calendar.of(onCallStartMonth, onCallStartDayOfTheWeek)
        val onCallSchedule = OnCallSchedule.of(
            month = onCallStartMonth,
            onCallEmployee = onCallEmployee,
            startDayOfWeek = onCallStartDayOfTheWeek
        )
        onCallConsoleIO.printOnCallSchedule(calendar = calendar, onCallSchedule = onCallSchedule)
    }
}
