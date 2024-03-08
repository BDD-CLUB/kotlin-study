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

//        val weekdayEmployee = getEmergencyWorker(
//                { outputView.printWeekdayEmergencyAssignment().toString() },
//                { Console.readLine() }
//        )
//
//        val weekendEmployee = getEmergencyWorker(
//                { outputView.printWeekendEmergencyAssignment().toString() },
//                { Console.readLine() }
//        )

        val ex1 = "준팍,도밥,고니,수아,루루,글로,솔로스타,우코,슬링키,참새,도리".split(",").toMutableList()
        val ex2 = "수아,루루,글로,솔로스타,우코,슬링키,참새,도리,준팍,도밥,고니".split(",").toMutableList()

        val work = EmergencyWork(emergencyWorkDate, ex1, ex2)
        val schedule = work.generateEmergencySchedule()

        schedule.forEach { println(it.toString()) }
    }

}