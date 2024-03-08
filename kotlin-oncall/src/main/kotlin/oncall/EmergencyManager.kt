package oncall

import camp.nextstep.edu.missionutils.Console
import oncall.domain.EmergencySchedule
import oncall.domain.EmergencyWork
import oncall.global.Component
import oncall.view.InputView
import oncall.view.OutputView
import oncall.view.getEmergencyDate
import oncall.view.getEmergencyWorker

@Component
class EmergencyManager(
        private val inputView: InputView,
        private val outputView: OutputView,
) {

    fun run() {
        outputView.printEmergencyAssignment()
        val emergencyWorkDate = getEmergencyDate(
                printMessage = { outputView.printEmergencyAssignment().toString() },
                console = { Console.readLine() }
        )

        val weekdayEmployee = getEmergencyWorker(
                { outputView.printWeekdayEmergencyAssignment().toString() },
                { Console.readLine() }
        )

        val weekendEmployee = getEmergencyWorker(
                { outputView.printWeekendEmergencyAssignment().toString() },
                { Console.readLine() }
        )

        val work = EmergencyWork(emergencyWorkDate, weekdayEmployee, weekendEmployee)
        val schedule = work.generateEmergencySchedule()

        outputView.printScheduleResult(schedule)
    }

}