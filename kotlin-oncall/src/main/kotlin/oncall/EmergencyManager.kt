package oncall

import camp.nextstep.edu.missionutils.Console
import oncall.domain.EmergencySchedule
import oncall.domain.EmergencyWork
import oncall.global.Component
import oncall.view.InputView
import oncall.view.OutputView
import oncall.view.getEmergencyDate

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
        outputView.printWeekdayEmergencyAssignment()
        val weekdayEmployee = Console.readLine().split(",")

        require(weekdayEmployee.all { it.length < 6 }) { "[ERROR] 5글자 초과" }
        require(weekdayEmployee.distinct().size == weekdayEmployee.size) { "[ERROR] 중복 닉네임 발생" }
        require(weekdayEmployee.size in 5..35) { "[ERROR] 최소 5명의 근무자가 유지되어야 합니다." }

        // STEP3 > 주말 비상 근무자 배정
        outputView.printWeekendEmergencyAssignment()
        val weekendEmployee = Console.readLine().split(",")

        require(weekendEmployee.all { it.length < 6 }) { "[ERROR] 5글자 초과" } // 글자수 예외
        require(weekendEmployee.distinct().size == weekdayEmployee.size) {"[ERROR] 중복 닉네임 발생" } // 중복 닉네임
        require(weekendEmployee.size in 5..35) { "[ERROR] 최소 5명의 근무자가 유지되어야 합니다." } // 근무자 수 예외

        val work = EmergencyWork(emergencyWorkDate, weekdayEmployee, weekendEmployee)
        val emergencySchedule = EmergencySchedule(work)


    }

}