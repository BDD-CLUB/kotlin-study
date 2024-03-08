package oncall.view

import oncall.domain.Day
import oncall.domain.Month
import oncall.global.Component

@Component
class InputView {

}

fun getEmergencyDate(printMessage: () -> Unit, console: () -> String): Pair<Month, Day> {
    while (true) {
        try {
            val userInput = console().split(",")
            val month = userInput[0]
                    .toIntOrNull()
                    ?.takeIf { it in 1..12 }
                    ?: throw IllegalArgumentException("[ERROR] 제대로 입력해 주세용.")

            val day = Day.entries.firstOrNull{ it.day == userInput[1] }
                    ?:throw IllegalArgumentException("[ERROR] 제대로 입력해 주세용.")

            return Pair(Month.entries.first { it.month == month }, day)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            printMessage()
        }
    }
}

fun getEmergencyWorker(printMessage: () -> Unit, console: () -> String): MutableList<String> {
    while (true) {
        try {
            printMessage()
            val userInput = console().split(",")

            require(userInput.all { it.length < 6 }) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            require(userInput.distinct().size == userInput.size) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }
            require(userInput.size in 5..35) { "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." }

            return userInput.toMutableList()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }

    }

}
