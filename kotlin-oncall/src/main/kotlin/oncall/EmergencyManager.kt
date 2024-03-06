package oncall

import camp.nextstep.edu.missionutils.Console
import oncall.domain.EmergencySchedule
import oncall.domain.EmergencyWork
import oncall.view.InputView
import oncall.view.OutputView
import oncall.view.getIntOrThrow

class EmergencyManager(
        private val inputView: InputView,
        private val outputView: OutputView,
        private val emergencyWork: EmergencyWork,
) {

    fun run() {

        // STEP1 > 비상 근무 배정 일
        outputView.printEmergencyAssignment()
        val readLine = Console.readLine()
        val emergencyWorkDate = getIntOrThrow { readLine.split(",")[0] }
        val emergencyWorkDay = readLine.split(",")[1]

        println("$emergencyWorkDate $emergencyWorkDay")

        // STEP2 > 평일 비상 근무자 배정
        outputView.printWeekdayEmergencyAssignment()
        val weekdayEmployee = Console.readLine().split(",")

        // STEP3 > 주말 비상 근무자 배정
        outputView.printWeekendEmergencyAssignment()
        val weekendEmployee = Console.readLine().split(",")


        val work = EmergencyWork(emergencyWorkDate, emergencyWorkDay, weekdayEmployee, weekendEmployee)
        val emergencySchedule = EmergencySchedule(work)

        println(emergencySchedule.schedule)


    }

}