package oncall.view

import oncall.domain.Day
import oncall.global.Component

@Component
class InputView {


}

fun getEmergencyAssignmentDate() {

}

fun getEmergencyDate(printMessage: () -> Unit, console: () -> String): Pair<Int, Day> {
    while (true) {
        try {
            val userInput = console().split(",")
            val month = userInput[0]
                    .toIntOrNull()
                    ?.takeIf { it in 1..12 }
                    ?: throw IllegalArgumentException("[ERROR] 제대로 입력해 주세용.")

            val day = Day.entries.firstOrNull{ it.day == userInput[1] }
                    ?:throw IllegalArgumentException("[ERROR] 제대로 입력해 주세용.")

            return Pair(month, day)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            printMessage()
        }
    }
}
