package oncall.view

import oncall.domain.Day
import oncall.domain.Month
import oncall.global.Component

@Component
class InputView {

    companion object {
        const val ERROR_PREFIX = "[ERROR]"
        const val INVALID_INPUT_VALUE = "$ERROR_PREFIX 유효하지 않은 입력 값입니다. 다시 입력해 주세요."
    }

}

fun getEmergencyDate(printMessage: () -> Unit, console: () -> String): Pair<Month, Day> {
    while (true) {
        try {
            val userInput = console().split(",")
            val month = userInput[0]
                    .toIntOrNull()
                    ?.takeIf { it in 1..12 }
                    ?: throw IllegalArgumentException(InputView.INVALID_INPUT_VALUE)

            val day = Day.entries.firstOrNull{ it.day == userInput[1] }
                    ?:throw IllegalArgumentException(InputView.INVALID_INPUT_VALUE)

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

            require(userInput.all { it.length < 6 }) { InputView.INVALID_INPUT_VALUE }
            require(userInput.distinct().size == userInput.size) { InputView.INVALID_INPUT_VALUE }
            require(userInput.size in 5..35) { InputView.INVALID_INPUT_VALUE }

            return userInput.toMutableList()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }

    }

}
