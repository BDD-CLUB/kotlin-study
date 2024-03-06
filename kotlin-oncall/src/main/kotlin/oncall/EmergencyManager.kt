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

        // STEP1 > 비상 근무 배정 일
        outputView.printEmergencyAssignment()
        val emergencyWorkDate = getEmergencyDate(
                printMessage = { outputView.printEmergencyAssignment().toString() },
                console = { Console.readLine() }
        )

        println("$emergencyWorkDate")

        // STEP2 > 평일 비상 근무자 배정
        val weekdayEmployee = getEmergencyWorker(
                { outputView.printWeekdayEmergencyAssignment().toString() },
                { Console.readLine() }
        )

        // STEP3 > 주말 비상 근무자 배정
        val weekendEmployee = getEmergencyWorker(
                { outputView.printWeekendEmergencyAssignment().toString() },
                { Console.readLine() }
        )

        println("$weekdayEmployee, $weekendEmployee")

        val work = EmergencyWork(emergencyWorkDate, weekdayEmployee, weekendEmployee)
        val emergencySchedule = EmergencySchedule(work)
    }

}